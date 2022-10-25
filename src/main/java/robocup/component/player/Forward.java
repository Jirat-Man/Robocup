package robocup.component.player;

import robocup.Region;
import robocup.component.Ball;
import robocup.component.Team.Team;
import robocup.component.behaviour.*;
import robocup.component.tool.SupportCalculator;

// starred for now, will change

import java.awt.geom.Point2D;
import java.time.temporal.ValueRange;
import java.util.Random;

public class Forward extends Player{


    BehaviourShoot m_shoot;
    BehaviourDribble m_dribble;
    BehaviourPass m_pass;
    BehaviourReceive m_receive;
    BehaviourBlock m_block;
    BehaviourIntercept m_intercept;
    BehaviourSendOff m_sendOff;


    public Forward(Point2D center, int radiusA, int radiusB, int kit, int side, Team mate, Ball ball) {
        super(center, radiusA, radiusB, kit, side, mate, ball);
        this.m_shoot = new BehaviourShoot(this,this.m_ball);
        this.m_dribble = new BehaviourDribble(this, this.m_ball);
        this.m_pass = new BehaviourPass(this, this.m_ball);
        this.m_block = new BehaviourBlock(this, this.m_ball);
        this.m_receive = new BehaviourReceive(this,this.m_ball);
        this.m_sendOff = new BehaviourSendOff(this,this.m_ball);
        this.m_intercept = new BehaviourIntercept(this,this.m_ball);
    }

    public void execute(){
        if(this.getM_teammate().GOAL == 1){
            setPosition(m_homeRegionX,m_homeRegionY);
            return;
        }
/*        if(this.getM_red() == 0){*/
            if (this.m_teammate.getM_possession() == false){
                this.defence();
            }
            else if (this.m_teammate.getM_possession() == true){
                this.attack();
            }
/*        }
        else{
            this.m_sendOff.execute();
        }*/

    }

    private void attack() {
         /*

        in a given game state, a player on the controlling team is checked to see if it is the closest or second closest player to the ball
        if it is the closest player (with ball), it may do one of three things:

            pass (highest priority)
            shoot (mid priority)
            dribble (if cannot pass or shoot)
         */
        if(this.m_receivePlayer){

            System.out.println(this.side +" forward attack receive occur");
            m_receive.execute();

        }
        else if (this.getM_is_ball()){

                //check to see if teammate is in a support spot
                //behaviour pass if yes
                //otherwise, behaviour dribble until support spot reached\
            double bou = 1101 + (-getM_teammate().TEAM_SIDE)*(1101-48);
            double delX = Math.pow(getPositionX()-bou,2);
            double delY = Math.pow(getPositionY()-359.5,2);
            double dis = Math.pow(delX+delY,0.5);
            System.out.println(dis);
            if(dis < 200){
                System.out.println(this.side +" forward attack shoot occur");
                m_shoot.execute();
                //behaviour shoot
            }
            else if(new Random().nextDouble() < 0.99){
                System.out.println(this.side +" forward attack dribble occur");
                m_dribble.execute();
                //behaviour dribble
            }
            else  {
                System.out.println(this.side +" forward attack pass occur");
                m_pass.execute();
                //behaviour pass

            }

        }
        else if(this.m_teammate.getM_PlayerSecondClosestToBall() == this){
            Region correctMove = SupportCalculator.getBestCurrentRegion();
            double correctY = (correctMove.getM_bot() + correctMove.getM_top())/2;
            double correctX = (correctMove.getM_left() + correctMove.getM_right())/2;
            setBufferSpeed(50);
            this.move(correctX,correctY);

        }

        else {
            setBufferSpeed(50);
            if (getPositionX() < getM_teammate().getM_PlayerClosestToBall().getPositionX())
                setM_speedX(30);
            else
                setM_speedX(-30);
            move();
        }
    }


    private void defence() {
        if (this.m_teammate.getM_PlayerClosestToBall() == this) {
            if (distanceToBall(this.m_ball) < 80) {
                //intercept
                System.out.println(this.side +" forward defence intercept occur");
                m_block.execute();
                if (distanceToP(m_homeRegionX,m_homeRegionY) > 80){
                    setBufferSpeed(50);
                    move(this.m_homeRegionX, this.m_homeRegionY);
                }
            } else {
                //behaviour block
                System.out.println(this.side +" forward defence block1 occur");
                m_block.execute();
            }
            this.move();

        } else if (this.m_teammate.getM_PlayerSecondClosestToBall() == this) {
            System.out.println(this.side +" forward defence block2 occur");
            m_block.execute();
            this.move();
        } else {
            //block set target
            //add a variable defence target
            setBufferSpeed(20);
            move(this.m_homeRegionX, this.m_homeRegionY);
        }
    }



    private boolean m_canSafeShoot(Team m_teammate, Team m_enemy) {
        //method for if shot on target can be made
        // need add something
        return true;
    }

    public boolean m_canSafePass(Team m_teammate, Team m_enemy){

        boolean isPassSafe = true;
        //boolean to return - true until proven false

        //this function draws a line between the passing player and their target using y = mx + c
        //checks if the nearest enemy player is close to this line - if so, pass is unsafe
        //there is a chance an unsafe pass is performed (30%) and the safe pass is not (10%)

        Player ballowner = m_teammate.getM_PlayerClosestToBall();
        Player target = m_teammate.getM_PlayerSecondClosestToBall();
        //establish target and owner
        //will need to edit owner once it has its own team.getBallOwner() function
        //will need to edit target once can establish a dedicated target player

        double ownerX = ballowner.getPositionX();
        double ownerY = ballowner.getPositionY();

        double targetX = target.getPositionX();
        double targetY = target.getPositionY();

        double enemyY = m_enemy.getM_PlayerClosestToBall().getPositionY();
        double enemyX = m_enemy.getM_PlayerClosestToBall().getPositionX();

        //get x and y co-ordinates of all necessary players


        //now need to calculate y = mx + c
        double gradient = ((targetY - ownerY)/(targetX - ownerX)); //m

        double minX;
        double maxX;

        if(targetX < ownerX){
            minX = targetX;
            maxX = ownerX;
        }
        else{
            minX = ownerX;
            maxX = targetX;
        }


        //loop along a line with some interval (for optimisation)
        for(double i = minX; i < maxX; i+=5){

            double y = (gradient*i) + ownerY;
            int finalY = (int) y;
            //y = mx + c final

            //if the closest enemy is within some range of the pass line, the pass is unsafe
            if (ValueRange.of((finalY - 30), (finalY + 30)).isValidIntValue((int) enemyY) && ValueRange.of((int) i - 30, (int) i + 30).isValidIntValue((int) enemyX)) {
                isPassSafe = false;
                break;
            }
        }

        //the conditions for attempting a pass:
        //15% chance if proven unsafe
        //90% chance if proven safe
        //chance given by pseudorandom number

        int chance = (int)(Math.random() * 100) + 1;

        if(chance > 85 && !isPassSafe){
            return true;
        }
        else if(chance > 10 && isPassSafe){
            return true;
        }

        //otherwise, no pass is attempted
        return false;
    }
}

package robocup.component.player;

import robocup.component.Ball;
import robocup.component.Team.Team;
import robocup.component.behaviour.*;

import java.awt.geom.Point2D;
import java.time.temporal.ValueRange;
import java.util.Random;

public class Defence extends Player{


    BehaviourBlock m_block;
    BehaviourIntercept m_intercept;
    BehaviourSendOff m_sendOff;
    BehaviourPass m_pass;
    BehaviourReceive m_receive;
    BehaviourDribble m_dribble;

    public Defence(Point2D center, int radiusA, int radiusB, int kit, int side, Team mate, Ball ball) {
        super(center, radiusA, radiusB, kit, side, mate, ball);
        this.m_block = new BehaviourBlock(this, this.m_ball);
        this.m_intercept = new BehaviourIntercept(this,this.m_ball);
        this.m_sendOff = new BehaviourSendOff(this,this.m_ball);
        this.m_pass = new BehaviourPass(this, this.m_ball);
        this.m_receive = new BehaviourReceive(this,this.m_ball);
        this.m_dribble = new BehaviourDribble(this,this.m_ball);
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
            else if (this.m_teammate.getM_possession() == true) {
                this.attack();
            }
/*        }
        else{
            this.m_sendOff.execute();
        }*/



    }

    public void attack(){
        //if has the ball, pass to midfield
        //stay at home region
        //behaviour position
        if (this.m_receivePlayer){
            System.out.println(this.side +" guard attack receive");
            this.m_receive.execute();
        }
        else{
            if(this.m_is_ball == false){

                System.out.println(this.side +" guard attack no ball");
                if (Math.abs(getPositionX()-m_ball.getPositionX()) > 280){
                    //move to home region +1
                    if (this.m_team == 0){
                        this.setM_speedX(25);
                    }

                    else if (this.m_team == 1){
                        this.setM_speedX(-25);//maybe change
                    }
                    move();
                }
                else if (Math.abs(getPositionX()-m_ball.getPositionX()) <= 280){
                    setBufferSpeed(30);
                    move(this.m_homeRegionX, this.m_homeRegionY);
                }
            }
            else if(m_is_ball){
                if(new Random().nextDouble() < 0.99) {
                    System.out.println(this.side + " guard attack dribble occur");
                    m_dribble.execute();
                    //behaviour dribble

                }
                else {
                    System.out.println(this.side +" guard attack pass");
                    m_pass.execute();
                    //behaviour pass

                }
            }
        }



    }

    public void defence(){
        if (this.m_teammate.getM_PlayerClosestToBall() == this){
            if (distanceToBall(this.m_ball) < 100){
                //intercept
                System.out.println(this.side +" guard defence intercept");
                m_block.execute();
                if (distanceToP(m_homeRegionX,m_homeRegionY) > 80){
                    setBufferSpeed(50);
                    move(this.m_homeRegionX, this.m_homeRegionY);
                }
            }
            else{
                //behaviour block
                System.out.println(this.side +" guard defence block");
                m_block.execute();
            }
            this.move();

        }
        else if(this.m_teammate.getM_PlayerSecondClosestToBall() == this){
            System.out.println(this.side +" guard defence block");
            m_block.execute();
            this.move();
        }

        else {
            //block set target
            //add an variable defence target
            System.out.println(this.side +" guard defence back home");
            setBufferSpeed(50);
            move(this.m_homeRegionX, this.m_homeRegionY);
        }
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

package robocup.component.player;

import robocup.PitchFrame;
import robocup.component.Ball;
import robocup.component.Team.Team;
import robocup.component.behaviour.BehaviourBlock;
import robocup.component.behaviour.BehaviourPass;
import robocup.component.behaviour.BehaviourReceive;

import java.awt.geom.Point2D;
import java.util.Random;

public class GoalKeeper extends Player {

    Random rnd = new Random();

    static boolean KeeperHasBall = false;

    static double HEIGHT_OF_SECTION = (683 - 18) / 7;
    double SECTION_DIVIDED = HEIGHT_OF_SECTION / 7;

    static boolean ballFar = true;
    static boolean ballNear = false;

    // 7 positions for the goalkeeper to move to
    public static double pos1 = PitchFrame.GOAL_LEFT_CENTER.getY() - 35;
    public static double pos2 = PitchFrame.GOAL_LEFT_CENTER.getY() - 25;
    public static double pos3 = PitchFrame.GOAL_LEFT_CENTER.getY() - 12;
    public static double pos4 = PitchFrame.GOAL_LEFT_CENTER.getY();
    public static double pos5 = PitchFrame.GOAL_LEFT_CENTER.getY() + 12;
    public static double pos6 = PitchFrame.GOAL_LEFT_CENTER.getY() + 25;
    public static double pos7 = PitchFrame.GOAL_LEFT_CENTER.getY() + 35;


    private final double top = 18;    //defining the pitch bounds
    private final double bot = 18 + 683;

    BehaviourPass m_pass;
    BehaviourReceive m_receive;
    BehaviourBlock m_block;


    public GoalKeeper (Point2D center, int radiusA, int radiusB, int kit, int side, Team mate, Ball ball) {
        super(center, radiusA, radiusB, kit, side, mate, ball);
        this.m_block = new BehaviourBlock(this, this.m_ball);
        this.m_pass = new BehaviourPass(this, this.m_ball);

    }

    public void execute(){
        if (!this.m_teammate.getM_possession()){
            this.defence();
        }
        else if (this.m_teammate.getM_possession()){
            this.attack();
        }
    }

    public void defence(){
        block(this.m_ball); ////blocks or deflect ball when in contact with ball

        if (this.m_ball.getPositionY() < HEIGHT_OF_SECTION) {
            this.move(this.m_homeRegionX,pos1);
        }
        if (this.m_ball.getPositionY() < HEIGHT_OF_SECTION * 2 && this.m_ball.getPositionY() > HEIGHT_OF_SECTION) {
            this.move(this.m_homeRegionX,pos2);
        }

        if (this.m_ball.getPositionY() < HEIGHT_OF_SECTION * 3 && this.m_ball.getPositionY() > HEIGHT_OF_SECTION * 2) {
            this.move(this.m_homeRegionX,pos3);
        }

        if (this.m_ball.getPositionY() < HEIGHT_OF_SECTION * 4 && this.m_ball.getPositionY() > HEIGHT_OF_SECTION * 3) {
            this.move(this.m_homeRegionX,pos4);
        }

        if (this.m_ball.getPositionY() < HEIGHT_OF_SECTION * 5 && this.m_ball.getPositionY() > HEIGHT_OF_SECTION * 4) {
            this.move(this.m_homeRegionX,pos5);
        }

        if (this.m_ball.getPositionY() < HEIGHT_OF_SECTION * 6 && this.m_ball.getPositionY() > HEIGHT_OF_SECTION * 5) {
            this.move(this.m_homeRegionX,pos6);
        }
        if (this.m_ball.getPositionY() > HEIGHT_OF_SECTION * 6) {
            this.move(this.m_homeRegionX,pos7);
        }

        if(distanceToBall(this.m_ball)< 20){
            this.move(this.m_ball);
        }

        if(KeeperHasBall){
            m_pass.execute();
            KeeperHasBall = false;
        }

    }

    public void attack(){
        if(m_team == 0){
            this.move(m_homeRegionX+40,m_homeRegionY);
        }
        else if(m_team == 1){
            this.move(m_homeRegionX-40,m_homeRegionY);
        }
        block(this.m_ball);
    }

    public void block (Ball ball){

            if (this.getPlayerFace().getBounds().contains(ball.getPosition())) {

                double chance = Math.random();

                if (chance < 0.5) {
                    ball.setSpeedX(ball.getSpeedX() * -0.3);
                    ball.setSpeedY(ball.getSpeedY() * -0.3);
                }

                else if (chance >0.5 && chance <= 0.80 )
                {
                    ball.setSpeed(0, 0);
                    KeeperHasBall = true;
                }
                else{
                    System.out.println("goal");
                }
            }
        }
    }







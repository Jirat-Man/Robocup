package robocup.component.Team;

import robocup.PitchFrame;
import robocup.Region;
import robocup.TeamSelectFrame;
import robocup.component.Ball;
import robocup.component.player.Player;
import robocup.component.tool.SupportCalculator;

public abstract class Team {

    private boolean m_possession;
    public static final int TEAM_SIZE = 10;
    public  int TEAM_SIDE;
    private int m_sendOffLoc = 30;
    public int TEAM_BOUND;
    protected Ball m_ball;
    public Player[] m_player = new Player[TEAM_SIZE];
    private Team m_enemy;
    public int GOAL=0;

    //need add m_strategy
    //default 4-4-2

    public Team(int kit, boolean possession, Ball ball){
        this.m_possession = possession;
        this.m_ball = ball;
        initialize();

    }

    public void execute(){
        for (int i = 0; i < TEAM_SIZE; i++){
            this.m_player[i].execute();
            System.out.println(i);
        }
    }

    public Player[] getM_player() {
        return m_player;
    }

    private boolean is_possession(){
        for (int i = 0; i < TEAM_SIZE; i++){
            if (m_player[i].getM_is_ball()){
                return true;
            }
        }
        return false;
    }

    protected abstract void initialize();
    public int getM_sendOffLoc(){return this.m_sendOffLoc;}
    public void setM_sendOffLoc(int loc){this.m_sendOffLoc = loc;}

    public boolean getM_possession(){
        return m_possession;
    }

    public void setM_possession(boolean possession){
        this.m_possession = possession;
    }

    public void changeM_possession(){
        this.m_possession = !m_possession;
    }

    //how to implement or judge, possession change

    protected Player m_ReceivingPlayer;
    protected Player m_PlayerClosestToBall;
    protected Player m_PlayerSecondClosestToBall;
    protected Player m_PlayerWithBall;
    protected Player m_SupportingPlayer;
    protected Region[][] m_regionInfo;

    public void setNoBall(){
        for (int i = 0; i < TEAM_SIZE;i++){
            this.m_player[i].setM_is_ball(false);
        }
    }
    public void setM_ReceivingPlayer(Player player){this.m_ReceivingPlayer = player;}
    public Player getM_ReceivingPlayer(){
        return this.m_ReceivingPlayer;
    }
    public void setM_enemy(Team enemy){
        this.m_enemy = enemy;
    }
    public Team getM_enemy() {return this.m_enemy;}

    public Player getM_PlayerClosestToBall(){
        return m_PlayerClosestToBall;
    }

    public Player getM_PlayerSecondClosestToBall(){
        return m_PlayerSecondClosestToBall;
    }
    // "pointers" to players of high value in any given game state

    public void reset(int side) {
        if(side == 1)
        {
            this.m_player[0].setPosition(PitchFrame.FORWARD_L1.getX(), PitchFrame.FORWARD_L1.getY());
            this.m_player[1].setPosition(PitchFrame.FORWARD_L2.getX(), PitchFrame.FORWARD_L2.getY());
            this.m_player[2].setPosition(PitchFrame.MID_FIELD_L1.getX(), PitchFrame.MID_FIELD_L1.getY());
            this.m_player[3].setPosition(PitchFrame.MID_FIELD_L2.getX(), PitchFrame.MID_FIELD_L2.getY());
            this.m_player[4].setPosition(PitchFrame.MID_FIELD_L3.getX(), PitchFrame.MID_FIELD_L3.getY());
            this.m_player[5].setPosition(PitchFrame.MID_FIELD_L4.getX(), PitchFrame.MID_FIELD_L4.getY());

            this.m_player[6].setPosition(PitchFrame.DEFENCE_L1.getX(), PitchFrame.DEFENCE_L1.getY());
            this.m_player[7].setPosition(PitchFrame.DEFENCE_L2.getX(), PitchFrame.DEFENCE_L2.getY());
            this.m_player[8].setPosition(PitchFrame.DEFENCE_L3.getX(), PitchFrame.DEFENCE_L3.getY());
            this.m_player[9].setPosition(PitchFrame.DEFENCE_L4.getX(), PitchFrame.DEFENCE_L4.getY());
/*
            this.m_player[10].setPosition(PitchFrame.GOAL_LEFT_CENTER.getX(), PitchFrame.GOAL_LEFT_CENTER.getY());
*/

        }
        else
        {
            this.m_player[0].setPosition(PitchFrame.FORWARD_R1.getX(), PitchFrame.FORWARD_R1.getY());
            this.m_player[1].setPosition(PitchFrame.FORWARD_R2.getX(), PitchFrame.FORWARD_R2.getY());
            this.m_player[2].setPosition(PitchFrame.MID_FIELD_R1.getX(), PitchFrame.MID_FIELD_R1.getY());
            this.m_player[3].setPosition(PitchFrame.MID_FIELD_R2.getX(), PitchFrame.MID_FIELD_R2.getY());
            this.m_player[4].setPosition(PitchFrame.MID_FIELD_R3.getX(), PitchFrame.MID_FIELD_R3.getY());
            this.m_player[5].setPosition(PitchFrame.MID_FIELD_R4.getX(), PitchFrame.MID_FIELD_R4.getY());

            this.m_player[6].setPosition(PitchFrame.DEFENCE_R1.getX(), PitchFrame.DEFENCE_R1.getY());
            this.m_player[7].setPosition(PitchFrame.DEFENCE_R2.getX(), PitchFrame.DEFENCE_R2.getY());
            this.m_player[8].setPosition(PitchFrame.DEFENCE_R3.getX(), PitchFrame.DEFENCE_R3.getY());
            this.m_player[9].setPosition(PitchFrame.DEFENCE_R4.getX(), PitchFrame.DEFENCE_R4.getY());
/*
            this.m_player[10].setPosition(PitchFrame.GOAL_RIGHT_CENTER.getX(), PitchFrame.GOAL_RIGHT_CENTER.getY());
*/
        }

    }

    public Player getM_PlayerWithBall(){return m_PlayerWithBall;}

    public void setKeyPosition(){
        setPlayerClosestToBall(this,this.m_enemy,this.m_ball);
    }

    public void setRegion_info(Region[][] regions){this.m_regionInfo = regions; }

    public Region[][] getRegionInfo(){
        return this.m_regionInfo; }

    public void setPlayerClosestToBall(Team teammate, Team enemy, Ball ball) {
        for (int i = 0; i < TEAM_SIZE; i++) {
            double closestDistance = teammate.m_PlayerClosestToBall.distanceToBall(ball);
            double secondClosestDistance = teammate.m_PlayerSecondClosestToBall.distanceToBall(ball);
            double tempDistance = teammate.m_player[i].distanceToBall(ball);

            if (tempDistance < closestDistance) {
                teammate.m_PlayerClosestToBall = teammate.m_player[i];
            } else if (tempDistance >= closestDistance && tempDistance < secondClosestDistance) {
                teammate.m_PlayerSecondClosestToBall = teammate.m_player[i];
            }
        }

    }

    public double getM_DistanceClosestToRegion(Team enemy, Region cRegion){

        return 0.0;
    }

}

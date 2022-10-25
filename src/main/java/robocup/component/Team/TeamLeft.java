package robocup.component.Team;

import robocup.PitchFrame;
import robocup.TeamSelectFrame;
import robocup.component.Ball;
import robocup.component.player.Defence;
import robocup.component.player.Forward;
import robocup.component.player.GoalKeeper;
import robocup.component.player.Player;

public class TeamLeft extends Team{

    public TeamLeft(int kit, boolean possession, Ball ball) {
        super(kit, possession, ball);
    }

    @Override
    protected void initialize() {
        this.m_player[0] = new Forward(PitchFrame.FORWARD_L1,18,18, TeamSelectFrame.CounterLeft,0,this, this.m_ball);
        this.m_player[1] = new Forward(PitchFrame.FORWARD_L2,18,18,TeamSelectFrame.CounterLeft,0,this, this.m_ball);
        this.m_player[2] = new Defence(PitchFrame.MID_FIELD_L1,18,18,TeamSelectFrame.CounterLeft,0,this, this.m_ball);
        this.m_player[3] = new Defence(PitchFrame.MID_FIELD_L2,18,18,TeamSelectFrame.CounterLeft,0,this, this.m_ball);
        this.m_player[4] = new Defence(PitchFrame.MID_FIELD_L3,18,18,TeamSelectFrame.CounterLeft,0,this, this.m_ball);
        this.m_player[5] = new Defence(PitchFrame.MID_FIELD_L4,18,18,TeamSelectFrame.CounterLeft,0,this, this.m_ball);
        this.m_player[6] = new Defence(PitchFrame.DEFENCE_L1,18,18,TeamSelectFrame.CounterLeft,0,this, this.m_ball);
        this.m_player[7] = new Defence(PitchFrame.DEFENCE_L2,18,18,TeamSelectFrame.CounterLeft,0,this, this.m_ball);
        this.m_player[8] = new Defence(PitchFrame.DEFENCE_L3,18,18,TeamSelectFrame.CounterLeft,0,this, this.m_ball);
        this.m_player[9] = new Defence(PitchFrame.DEFENCE_L4,18,18,TeamSelectFrame.CounterLeft,0,this, this.m_ball);
        this.m_player[10] = new GoalKeeper(PitchFrame.GOAL_LEFT_CENTER, 18, 18, TeamSelectFrame.CounterLeft, 0, this,  this.m_ball);

        this.m_PlayerClosestToBall = this.m_player[1];
        this.m_PlayerSecondClosestToBall = this.m_player[0];
        this.TEAM_BOUND = 25;
        TEAM_SIDE = 0;
    }

}

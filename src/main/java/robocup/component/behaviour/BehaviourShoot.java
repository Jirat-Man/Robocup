package robocup.component.behaviour;

import robocup.PitchFrame;
import robocup.component.Ball;
import robocup.component.player.Player;

public class BehaviourShoot extends BehaviourBase{

    public BehaviourShoot(Player player, Ball ball) {
        super(player, ball);
    }

    public void execute(){
        //change and specify
        if (m_player.getM_team() == 0){
            m_player.turnToPoint(PitchFrame.GOAL_RIGHT_CENTER);
        }
        else{
            m_player.turnToPoint(PitchFrame.GOAL_LEFT_CENTER);
        }
        m_player.kick(this.m_ball, 200);

    }

}


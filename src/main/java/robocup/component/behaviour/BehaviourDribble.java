package robocup.component.behaviour;

import robocup.PitchFrame;
import robocup.component.Ball;
import robocup.component.player.Player;
import robocup.component.tool.SupportCalculator;

import java.awt.geom.Point2D;

public class BehaviourDribble extends BehaviourBase{

    public BehaviourDribble(Player player, Ball ball) {
        super(player, ball);
    }

    @Override
    public void execute() {
        Point2D des = SupportCalculator.bestReceiveRegion(this.m_player.getM_teammate(),this.m_player.getM_teammate().getM_enemy()).getRandomPosition();
        if (m_player.getPosition().distance(m_ball.getPosition()) > (m_ball.getM_radius()+m_player.getM_radius())){
            this.m_player.setBufferSpeed(50);
            this.m_player.move(this.m_ball);
        }
        else{
            this.m_player.setBufferSpeed(50);
            if (m_player.getSide() == 0){
                this.m_player.turnWithBall(m_ball, PitchFrame.GOAL_RIGHT_CENTER);
                this.m_player.move(m_ball,PitchFrame.GOAL_RIGHT_CENTER);
            }
            else{
                this.m_player.turnWithBall(m_ball, PitchFrame.GOAL_LEFT_CENTER);
                this.m_player.move(m_ball,PitchFrame.GOAL_LEFT_CENTER);

            }

/*            m_player.kick(this.m_ball, 23);*/
        }


        //dribble ball in direction player is facing (player will constantly update position)
        //kick is low power so player with ball moves slower

    }
}



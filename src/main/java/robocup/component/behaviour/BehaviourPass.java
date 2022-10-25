package robocup.component.behaviour;

import robocup.Region;
import robocup.component.Ball;
import robocup.component.player.Player;
import robocup.component.tool.SupportCalculator;

import java.awt.geom.Point2D;

public class BehaviourPass extends BehaviourBase{
    public BehaviourPass(Player player, Ball ball) {
        super(player, ball);
    }

    @Override
    public void execute() {
        //need change 2nd parameter
        //maybe some thread problem
        //just set the receive player possession the ball, if be intercepted then change again
        //change to best region's random position
        if (m_player.getPosition().distance(m_ball.getPosition()) <= m_player.getM_radius()+m_ball.getM_radius()){
            Point2D Position = SupportCalculator.bestReceiveRegion(this.m_player.getM_teammate(),this.m_player.getM_teammate()).getRandomPosition();
            Player tmp_player = SupportCalculator.bestReceivePlayer(m_player.getM_teammate());
            System.out.println(tmp_player.getPositionX());
            System.out.println(tmp_player.getPositionY());
  /*          if(m_player.turnWithBall(m_ball,tmp_player.getPosition())){*/
            m_player.turn(m_player.degreeToPoint(tmp_player.getPosition()));
                m_player.kick(m_ball, tmp_player.getPosition());
                m_player.setM_is_ball(false);
                Player receivePlayer = SupportCalculator.bestReceivePlayer(m_player.getM_teammate());
                this.m_player.getM_teammate().setM_ReceivingPlayer(receivePlayer);
                receivePlayer.changeM_receivePlayer();
                receivePlayer.setM_is_ball(true);
/*            }*/

        }
        else {
            m_player.setBufferSpeed(50);
            m_player.move(m_ball);
        }


    }



}


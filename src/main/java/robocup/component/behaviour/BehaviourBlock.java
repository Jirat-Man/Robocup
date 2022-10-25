package robocup.component.behaviour;

import robocup.component.Ball;
import robocup.component.player.Player;

public class BehaviourBlock extends BehaviourBase{
    public BehaviourBlock(Player player, Ball ball) {
        super(player, ball);
    }

    public void execute(){
        m_player.turnToBall(m_ball);
        this.m_player.turnToBall(this.m_ball);
        if (m_player.distanceToBall(this.m_ball) < 40){
            m_player.setM_speedX(m_ball.getSpeedX());
            m_player.setM_speedY(m_ball.getSpeedY());
        }
        else{
            m_player.dash();
        }
    }

}


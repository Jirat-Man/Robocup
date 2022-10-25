package robocup.component.behaviour;

import robocup.Region;
import robocup.component.Ball;
import robocup.component.player.Player;
import robocup.component.tool.SupportCalculator;

public class BehaviourReceive extends BehaviourBase{
    final static double RECEIVE_DISTANCE = 80;

    public BehaviourReceive(Player player, Ball ball) {
        super(player, ball);
    }

    @Override
    public void execute() {
        if(this.m_player.getM_is_ball()){
            if (this.m_player.getPosition().distance(this.m_ball.getPosition()) < RECEIVE_DISTANCE
                    || this.m_player.getM_is_ball()){
                this.m_player.move(this.m_ball);
            }
            if (this.m_player.getPosition().distance(this.m_ball.getPosition()) < 30
                    || this.m_player.getM_is_ball()){
                this.m_player.move(this.m_ball);
                this.m_player.changeM_receivePlayer();
                this.m_player.getM_teammate().setM_ReceivingPlayer(null);
            }
            //be chased

            if (this.m_player.getPosition().distance(SupportCalculator.bestReceiveRegion(this.m_player.getM_teammate(),this.m_player.getM_teammate().getM_enemy()).getRandomPosition()) < RECEIVE_DISTANCE){
                this.m_player.turnToBall(this.m_ball);
                this.m_player.stop();
            }
            else{
                this.m_player.move(SupportCalculator.bestReceiveRegion(this.m_player.getM_teammate(),this.m_player.getM_teammate().getM_enemy()).getRandomPosition());
            }
        }
        else {
            this.m_player.changeM_receivePlayer();
            this.m_player.getM_teammate().setM_ReceivingPlayer(null);
        }



    }
}

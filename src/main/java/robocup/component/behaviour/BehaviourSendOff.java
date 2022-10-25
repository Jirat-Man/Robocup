package robocup.component.behaviour;

import robocup.component.Ball;
import robocup.component.player.Player;

public class BehaviourSendOff extends BehaviourBase{
    public BehaviourSendOff(Player player, Ball ball) {
        super(player, ball);
    }

    @Override
    public void execute() {
        if (!this.m_player.moved){
            this.m_player.stop();
            int sendOffLoc = this.m_player.getM_teammate().getM_sendOffLoc();
            m_player.setPosition(this.m_player.getM_teammate().TEAM_BOUND,sendOffLoc);
            this.m_player.getM_teammate().setM_sendOffLoc(sendOffLoc+30);
            m_player.moved = true;
            if (m_player.getM_teammate().getM_possession() == false){

            }
            else{
                if (m_player.getM_is_ball()){
                    m_player.setM_is_ball(false);
                    m_player.getM_teammate().setM_possession(false);
                    m_player.getM_teammate().getM_enemy().getM_PlayerClosestToBall().setM_is_ball(true);
                    m_player.getM_teammate().getM_enemy().setM_possession(true);
                }
                else{
                    m_player.getM_teammate().getM_PlayerClosestToBall().setM_is_ball(false);
                    m_player.getM_teammate().setM_possession(false);
                    m_player.getM_teammate().getM_enemy().getM_PlayerClosestToBall().setM_is_ball(true);
                    m_player.getM_teammate().getM_enemy().setM_possession(true);
                }
            }
        }
        else {
            this.m_player.stop();
        }
    }

}

package robocup.component.behaviour;

import robocup.component.Ball;
import robocup.component.player.Player;

import java.util.Random;

public class BehaviourIntercept extends BehaviourBase{
    Random rng;
    public BehaviourIntercept(Player player, Ball ball) {
        super(player, ball);
        rng = new Random();
    }

    @Override
    public void execute() {
        final double DISTANCE_JUDGE = 70;
        if (m_player.getM_teammate().getM_enemy().getM_PlayerClosestToBall().distanceToBall(this.m_ball) > DISTANCE_JUDGE){
            if (m_player.getPosition().distance(this.m_ball.getPosition()) < (m_player.getM_radius()+ m_ball.getM_radius()+4)&& rng.nextDouble()<0.5){
                System.out.println("successful");
                this.m_player.setM_is_ball(true);
                this.m_player.getM_teammate().setM_possession(true);
                this.m_player.getM_teammate().getM_enemy().setM_possession(false);
                this.m_player.getM_teammate().setNoBall();

            }
            this.m_player.turnToBall(m_ball);
            this.m_player.setBufferSpeed(80);
            this.m_player.move(m_ball);
        }
        if (m_player.getPosition().distance(this.m_ball.getPosition()) < (m_player.getM_radius()+ m_ball.getM_radius()+4)&& rng.nextDouble()<0.03){
            System.out.println("successful");
            this.m_player.setM_is_ball(true);
            this.m_player.getM_teammate().setM_possession(true);
            this.m_player.getM_teammate().getM_enemy().setM_possession(false);
            this.m_player.getM_teammate().setNoBall();

        }
        this.m_player.turnToBall(m_ball);
        this.m_player.setBufferSpeed(40);
        this.m_player.move(m_ball);


        //turn towards ball then chase ball to intercept a kick
        //if player was in an advantageous position, it will gain possession
        //otherwise, it will chase the ball
    }
}

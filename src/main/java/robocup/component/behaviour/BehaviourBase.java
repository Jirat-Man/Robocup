package robocup.component.behaviour;

import robocup.component.Ball;
import robocup.component.player.Player;

public abstract class BehaviourBase {
    Player m_player;
    Ball m_ball;
   public BehaviourBase(Player player, Ball ball){
       this.m_player = player;
       this.m_ball = ball;
   }

    public abstract void execute();
}



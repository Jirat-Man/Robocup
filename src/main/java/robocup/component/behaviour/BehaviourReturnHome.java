package robocup.component.behaviour;

import robocup.Region;
import robocup.component.Ball;
import robocup.component.player.Player;
import robocup.component.tool.SupportCalculator;

import java.awt.geom.Point2D;

public class BehaviourReturnHome extends BehaviourBase{
    public BehaviourReturnHome(Player player, Ball ball) {
        super(player, ball);
    }

    //behaviour to make player return to its starting position
    //aka "wait" state of player

    @Override
    public void execute() {

        //first, establish where the player's home region is
        Point2D homePoint = new Point2D.Double();
        homePoint.setLocation(this.m_player.getM_homeRegionX(), this.m_player.getM_homeRegionY());

        //next, make the player turn towards their home point
        this.m_player.turnToPoint(homePoint);

        //next, make the player move to its home point
        this.m_player.move(homePoint);

        //when the player is back at its home point, face the correct position (default position)
        if(this.m_player.getPosition() == homePoint){
            if(this.m_player.getSide() == 0){
                //if left team, face a position to the right
                Point2D pointToFace = new Point2D.Double();
                pointToFace.setLocation((this.m_player.getM_homeRegionX()+30),this.m_player.getM_homeRegionY());
                this.m_player.turnToPoint(pointToFace);
            }
            else if(this.m_player.getSide() == 1){
                //if right team, face a position to the left
                Point2D pointToFace = new Point2D.Double();
                pointToFace.setLocation((this.m_player.getM_homeRegionX()-30),this.m_player.getM_homeRegionY());
                this.m_player.turnToPoint(pointToFace);
            }
        }
    }



}
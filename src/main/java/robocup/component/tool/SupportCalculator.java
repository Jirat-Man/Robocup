package robocup.component.tool;

import robocup.PitchFrame;
import robocup.Region;
import robocup.component.Ball;
import robocup.component.Team.Team;
import robocup.component.player.Player;

import java.awt.geom.Point2D;
import java.time.temporal.ValueRange;

public class SupportCalculator {

    private static Region bestCurrentRegion;

/*    public SupportCalculator(Team teammate, Team enemy, Ball ball){
        this.m_teammate = teammate;
        this.m_enemy = enemy;
        this.m_ball = ball;
    }*/

/*
    public void setSupportSpot(Point2D position, double score){
        m_SupportPosition = position;
        m_Score = score;
    }
*/
    public static void setBestCurrentRegion(Region region){
        bestCurrentRegion = region;
    }

    public static Region getBestCurrentRegion(){ return bestCurrentRegion; }


    // p 150
    public static Region bestReceiveRegion(Team teammate, Team enemy){

        // need something to make sure this only ticks every so often
        // link to in-game timer? (every x in-game seconds)
        // then reset at half-time?
        // need function that returns current value if not enough time has passed since last check?

        Region m_BestReceiveRegion = null;

        double BestCurrentScore = 0.0;

    /*
    how calculating best support spot will work:
    three tests which will add points to each position.
    iterates over array of positions to calculate best position at a given point
    will happen per x ticks so load is negligible
     */

        Region[][] regions = teammate.getRegionInfo();
        for (Region[] region : regions) {
            for (Region value : region) {

                double cScore = 0.0;


                // test 1: possible to safely make pass to position

                if (m_canSafePass(teammate, enemy, value)) {

                    cScore += 10.0;

                }

                // test 2: possible to shoot on target from position

                if (m_canSafeShoot(teammate, enemy, value)) {

                    cScore += 5.0;

                }


                //test 3: which side is the region in?

                double regionDistanceFromBall = getDistanceFromBall(teammate,value);

                if(teammate.TEAM_SIDE == 0){
                    if(value.getM_right() >= (PitchFrame.WIDTH/2)){
                        if((PitchFrame.WIDTH/2 - regionDistanceFromBall) < 0){
                            cScore += 0;
                        }
                        else{
                            cScore += (((PitchFrame.WIDTH/2) - regionDistanceFromBall)*0.04);
                        }
                    }
                }
                else{
                    if(value.getM_left() <= (PitchFrame.WIDTH/2)){
                        if((PitchFrame.WIDTH/2 - regionDistanceFromBall) < 0){
                            cScore += 0;
                        }
                        else{
                            cScore += (((PitchFrame.WIDTH/2) - regionDistanceFromBall)*0.04);
                        }
                    }
                }

/*                // test 4: distance from nearest enemy player

                final double MAX_DISTANCE = 400;

                double distance = closestPlayerDistance(enemy, value);
                // use pythagoras in a separate function

                double temp = MAX_DISTANCE - distance;
                //temp variable to calculate region validity

                if (temp < 0) {

                    cScore = 0.0;
                    //play is too far away from that region, thus it is invalidated

                } else {

                    cScore += ((MAX_DISTANCE - temp) * 0.015);
                    //normalised score based on difference

                }

                //test 5: is the ball in this region?

                double ballDistanceToCentre = closestPlayerDistance(teammate,value);

                if(ballDistanceToCentre < 15){
                    cScore = 0.0;
                }*/



                if (cScore > BestCurrentScore) {

                    BestCurrentScore = cScore;

                    m_BestReceiveRegion = value;

                }
            }
        }
        setBestCurrentRegion(m_BestReceiveRegion);
        return m_BestReceiveRegion;
    }

    private static double closestPlayerDistance(Team team, Region cRegion) {

        double cDistance = 1500;

        //set some distance any player will be closer than at a given time


        //loop through list of players and compare their position to the region
        //gather distance using pythagoras
        //when a new closest value is found, replace current
        //return after all players looped through
        for(int i = 0; i < team.TEAM_SIZE; i++){

            double targetX = (cRegion.getM_left() + cRegion.getM_right())/2;
            double targetY = (cRegion.getM_top() + cRegion.getM_bot())/2;

            double enemyY = team.m_player[i].getPositionY();
            double enemyX = team.m_player[i].getPositionX();

            double across = Math.abs(targetX - enemyX);
            double up = Math.abs(targetY - enemyY);

            double distance = Math.sqrt(across*across + up*up);

            if(distance < cDistance){

                cDistance = distance;

            }
        }

        return cDistance;
    }

    public static double getDistanceFromBall(Team team, Region region){

        double withBallX;
        double withBallY;
        if (team.getM_possession() == true){
            withBallX = team.getM_PlayerClosestToBall().getPositionX();
            withBallY = team.getM_PlayerClosestToBall().getPositionY();
        }
        else{
            withBallX = team.getM_enemy().getM_PlayerClosestToBall().getPositionX();
            withBallY = team.getM_enemy().getM_PlayerClosestToBall().getPositionY();
        }


        double targetX = (region.getM_left() + region.getM_right())/2;
        double targetY = (region.getM_top() + region.getM_bot())/2;

        double across = Math.abs(targetX - withBallX);
        double up = Math.abs(targetY - withBallY);

        double distance = Math.sqrt(across*across + up*up);

        return distance;
    }

    public static Player bestReceivePlayer(Team team) {

        double cDistance = 1500;
        Player cPlayer = null;
        int index = -1;

        for (int i = 0; i < team.TEAM_SIZE; i++) {
            if (team.getM_PlayerClosestToBall() != team.getM_player()[i]){
                double targetX = (bestCurrentRegion.getM_left() + bestCurrentRegion.getM_right()) / 2;
                double targetY = (bestCurrentRegion.getM_top() + bestCurrentRegion.getM_bot()) / 2;

                double enemyY = team.m_player[i].getPositionY();
                double enemyX = team.m_player[i].getPositionX();

                double across = Math.abs(targetX - enemyX);
                double up = Math.abs(targetY - enemyY);

                double distance = Math.sqrt(across * across + up * up);

                if (distance < cDistance) {

                    cDistance = distance;
                    index = i;

                }
            }

        }
        System.out.println(index);
        return team.getM_player()[index];
    }

    public static boolean m_canSafePass(Team m_teammate, Team m_enemy, Region region){

        boolean isPassSafe = true;
        //boolean to return - true until proven false

        //this function draws a line between the passing player and their target using y = mx + c
        //checks if the nearest enemy player is close to this line - if so, pass is unsafe
        //there is a chance an unsafe pass is performed (30%) and the safe pass is not (10%)

        Player ballowner = m_teammate.getM_PlayerClosestToBall();
        //establish target and owner

        double ownerX = ballowner.getPositionX();
        double ownerY = ballowner.getPositionY();

        double targetX = (region.getM_left() + region.getM_right())/2;
        double targetY = (region.getM_top() + region.getM_bot())/2;

        double enemyY = m_enemy.getM_PlayerClosestToBall().getPositionY();
        double enemyX = m_enemy.getM_PlayerClosestToBall().getPositionX();

        //get x and y co-ordinates of all necessary players and region support spot


        //now need to calculate y = mx + c
        double gradient = ((targetY - ownerY)/(targetX - ownerX)); //m

        double minX;
        double maxX;

        if(targetX < ownerX){
            minX = targetX;
            maxX = ownerX;
        }
        else{
            minX = ownerX;
            maxX = targetX;
        }


        //loop along a line with some interval (for optimisation)
        for(double i = minX; i < maxX; i+=5){

            double y = (gradient*i) + ownerY;
            int finalY = (int) y;
            //y = mx + c final

            //if the closest enemy is within some range of the pass line, the pass is unsafe
            if (ValueRange.of((finalY - 30), (finalY + 30)).isValidIntValue((int) enemyY) && ValueRange.of((int) i - 30, (int) i + 30).isValidIntValue((int) enemyX)) {
                isPassSafe = false;
                break;
            }
        }

        //otherwise, no pass is attempted
        return isPassSafe;
    }

    private static boolean m_canSafeShoot(Team m_teammate, Team m_enemy, Region m_region) {
        //method for if shot on target can be made

        return true;
    }

}


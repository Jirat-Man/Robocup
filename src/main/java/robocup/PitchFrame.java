package robocup;

import java.awt.geom.Point2D;

/**
 * @auther Yihang Chen
 * the class to store the para of the pitch
 */
public final class PitchFrame {

    //the size of the pitch
    public final static double WIDTH = 1053;
    public final static double HEIGHT = 683;

    //the bounds of the pitch
    public final static double LEFT = 48;
    public final static double RIGHT = 1101;
    public final static double TOP = 18;
    public final static double BOT = 701;


    //the center of the pitch
    public final static double CENTER_X = 574.5;
    public final static double CENTER_Y = 359.5;
    public final static Point2D CENTER = new Point2D.Double(CENTER_X,CENTER_Y);

    //the y coordinate of the goal
    public final static double GOAL_TOP = 323.5;
    public final static double GOAL_BOT = 395.5;
    public final static double GOAL_CENTER = 359.5;

    //the corner of the pitch
    public final static Point2D LEFT_TOP = new Point2D.Double(LEFT,TOP);
    public final static Point2D LEFT_BOT = new Point2D.Double(LEFT,BOT);
    public final static Point2D RIGHT_TOP = new Point2D.Double(RIGHT,TOP);
    public final static Point2D RIGHT_BOT = new Point2D.Double(RIGHT,BOT);


    //the point of the bound of the goal
    public final static Point2D GOAL_LEFT_TOP = new Point2D.Double(LEFT,GOAL_TOP);
    public final static Point2D GOAL_LEFT_BOT = new Point2D.Double(LEFT,GOAL_BOT);
    public final static Point2D GOAL_LEFT_CENTER = new Point2D.Double(LEFT,GOAL_CENTER);
    public final static Point2D GOAL_RIGHT_TOP = new Point2D.Double(RIGHT,GOAL_TOP);
    public final static Point2D GOAL_RIGHT_BOT = new Point2D.Double(RIGHT,GOAL_BOT);
    public final static Point2D GOAL_RIGHT_CENTER = new Point2D.Double(RIGHT,GOAL_CENTER);

    //forbidden area

    //initial place of the ball
    public final static Point2D BALL_INITIAL_POSITION = CENTER;

    //initial place of the player(default 4-4-2)
    //left team
    public final static Point2D FORWARD_L1 = new Point2D.Double( LEFT + 3*WIDTH/8.0,TOP + 1*HEIGHT/3.0-10);
    public final static Point2D FORWARD_L2 = new Point2D.Double(LEFT + 3*WIDTH/8.0, TOP + 2*HEIGHT/3.0);
    public final static Point2D MID_FIELD_L1 = new Point2D.Double(LEFT + 2*WIDTH/8.0,TOP + 1*HEIGHT/5.0);
    public final static Point2D MID_FIELD_L2 = new Point2D.Double(LEFT + 2*WIDTH/8.0,TOP + 2*HEIGHT/5.0);
    public final static Point2D MID_FIELD_L3 = new Point2D.Double(LEFT + 2*WIDTH/8.0,TOP + 3*HEIGHT/5.0);
    public final static Point2D MID_FIELD_L4 = new Point2D.Double(LEFT + 2*WIDTH/8.0,TOP + 4*HEIGHT/5.0);
    public final static Point2D DEFENCE_L1 = new Point2D.Double(LEFT + 1*WIDTH/8.0,TOP + 1*HEIGHT/5.0);
    public final static Point2D DEFENCE_L2 = new Point2D.Double(LEFT + 1*WIDTH/8.0,TOP + 2*HEIGHT/5.0);
    public final static Point2D DEFENCE_L3 = new Point2D.Double(LEFT + 1*WIDTH/8.0,TOP + 3*HEIGHT/5.0);
    public final static Point2D DEFENCE_L4 = new Point2D.Double(LEFT + 1*WIDTH/8.0,TOP + 4*HEIGHT/5.0);
    public final static Point2D GOAL_KEEPER_L = GOAL_LEFT_CENTER;

    //right team
    public final static Point2D FORWARD_R1 = new Point2D.Double( RIGHT - 3*WIDTH/8.0,TOP + 1*HEIGHT/3.0-10);
    public final static Point2D FORWARD_R2 = new Point2D.Double(RIGHT - 3*WIDTH/8.0, TOP + 2*HEIGHT/3.0);
    public final static Point2D MID_FIELD_R1 = new Point2D.Double(RIGHT - 2*WIDTH/8.0,TOP + 1*HEIGHT/5.0);
    public final static Point2D MID_FIELD_R2 = new Point2D.Double(RIGHT - 2*WIDTH/8.0,TOP + 2*HEIGHT/5.0);
    public final static Point2D MID_FIELD_R3 = new Point2D.Double(RIGHT - 2*WIDTH/8.0,TOP + 3*HEIGHT/5.0);
    public final static Point2D MID_FIELD_R4 = new Point2D.Double(RIGHT - 2*WIDTH/8.0,TOP + 4*HEIGHT/5.0);
    public final static Point2D DEFENCE_R1 = new Point2D.Double(RIGHT - 1*WIDTH/8.0,TOP + 1*HEIGHT/5.0);
    public final static Point2D DEFENCE_R2 = new Point2D.Double(RIGHT - 1*WIDTH/8.0,TOP + 2*HEIGHT/5.0);
    public final static Point2D DEFENCE_R3 = new Point2D.Double(RIGHT - 1*WIDTH/8.0,TOP + 3*HEIGHT/5.0);
    public final static Point2D DEFENCE_R4 = new Point2D.Double(RIGHT - 1*WIDTH/8.0,TOP + 4*HEIGHT/5.0);
    public final static Point2D GOAL_KEEPER_R = GOAL_RIGHT_CENTER;

    public PitchFrame(){

    }

}

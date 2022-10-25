package robocup;

import java.awt.geom.Point2D;
import java.util.Random;

public class Region {
    private final double m_left;
    private final double m_right;
    private final double m_top;
    private final double m_bot;
    private final double m_width;
    private final double m_height;
    private final Point2D m_center;

    private final int m_ID;

    public Region(double left, double right, double top, double bot, int ID){
        this.m_left = left;
        this.m_right = right;
        this.m_top = top;
        this.m_bot = bot;
        this.m_center = new Point2D.Double((m_left + m_right) * 0.5, (m_top + m_bot) * 0.5);
        this.m_width = this.m_right - this.m_left;
        this.m_height = this.m_bot - this.m_top;
        this.m_ID = ID;
    }

    public Point2D getRandomPosition(){
        Random random = new Random();
        return (new Point2D.Double(random.nextDouble(m_left,m_right),
                random.nextDouble(m_top,m_bot)));
    }

    public boolean isInSide(Point2D pos){
        return ((pos.getX()>this.m_left)&&(pos.getX()<this.m_right)&&
                (pos.getY()>this.m_top)&&(pos.getY()<this.m_bot));
    }

    //getter of variable
    public double getM_left() {
        return m_left;
    }

    public double getM_right() {
        return m_right;
    }

    public double getM_top() {
        return m_top;
    }

    public double getM_bot() {
        return m_bot;
    }

    public double getM_width() {
        return m_width;
    }

    public double getM_height() {
        return m_height;
    }

    public Point2D getM_center() {
        return m_center;
    }

    public int getM_ID() {
        return m_ID;
    }
}

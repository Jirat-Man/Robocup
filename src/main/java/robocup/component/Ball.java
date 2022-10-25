package robocup.component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Ball {

    private Shape m_ball_face;

    private final Point2D m_center;

    private BufferedImage m_ball_type;
    public final static double ACCELERATE =1;
    public final static double SPEED_RATE =7.5;
    private final int m_radius;
    private double m_speedX;
    private double m_speedY;

    private BufferedImage ball_type1 = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);
    private BufferedImage ball_type2 = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);
    private BufferedImage ball_type3 = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);

    {
        try {
            ball_type1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Ball/ReducedFootball.png")));
            ball_type2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Ball/ReducedConfusedBall.png")));
            ball_type3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Ball/Reduced8Ball.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ball(Point2D center, int radiusA, int radiusB, int ballType){  //constructor of the ball class
        this.m_center = center;
        m_ball_face = makeBall(center,radiusA,radiusB);
        getBallImage(ballType);
        m_radius = radiusA;
        m_speedX = 0;
        m_speedY = 0;
    }

    private void getBallImage ( int ballType) {
        if(ballType == 0){
            this.m_ball_type = ball_type1;
        }
        if(ballType == 1){
            this.m_ball_type = ball_type2;
        }
        if(ballType == 2){
            this.m_ball_type = ball_type3;
        }
    }

    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {
        double x = center.getX() - (radiusA / 2);
        double y = center.getY() - (radiusB / 2);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }

    //the speed should be decrease(Timer)
    public void move(){
        double rate = 15;
        RectangularShape tmp = (RectangularShape) m_ball_face;
        m_center.setLocation((m_center.getX() + this.m_speedX/rate),(m_center.getY() + this.m_speedY/rate));

        double w = tmp.getWidth();
        double h = tmp.getHeight();
        decreaseSpeed();
        tmp.setFrame(m_center.getX(), m_center.getY(),w,h);

        m_ball_face = tmp;
    }
    //the ball move with uniform motion
    public void move(double speedX, double speedY){
        double rate = 15;
        RectangularShape tmp = (RectangularShape) m_ball_face;
        this.m_speedX = speedX;
        this.m_speedY = speedY;
        m_center.setLocation((m_center.getX() + this.m_speedX/rate),(m_center.getY() + this.m_speedY/rate));

        double w = tmp.getWidth();
        double h = tmp.getHeight();
        tmp.setFrame(m_center.getX(), m_center.getY(),w,h);

        m_ball_face = tmp;
    }

    public void decreaseSpeed() {  //when the ball move the speed will decrease

        if (m_speedX > 0){
            m_speedX = m_speedX * (1-(ACCELERATE/(Math.sqrt(Math.pow(m_speedX,2)+Math.pow(m_speedY,2)))));
            if (m_speedX < 0){
                m_speedX = 0;
            }
        }
        else if(m_speedX < 0 ){
            m_speedX = -(Math.abs(m_speedX) * (1-(ACCELERATE/(Math.sqrt(Math.pow(m_speedX,2)+Math.pow(m_speedY,2))))));
            if(m_speedX > 0){
                m_speedX = 0;
            }
        }
        if (m_speedY > 0){
            m_speedY = m_speedY * (1-(ACCELERATE/(Math.sqrt(Math.pow(m_speedX,2)+Math.pow(m_speedY,2)))));
            if (m_speedY < 0){
                m_speedY = 0;
            }
        }
        else if(m_speedY < 0 ){
            m_speedY = -(Math.abs(m_speedY) * (1-(ACCELERATE/(Math.sqrt(Math.pow(m_speedX,2)+Math.pow(m_speedY,2))))));
            if(m_speedY > 0){
                m_speedY = 0;
            }
        }

    }

    public void setSpeedX(double s){
        m_speedX = s;
    }

    public void setSpeedY(double s){
        m_speedY = s;
    }

    public void setSpeed(double x,double y){
        m_speedX = x;
        m_speedY = y;
    }


    public double getSpeedX(){
        return m_speedX;
    }

    public double getSpeedY(){
        return m_speedY;

    }

    public void setPosition(double width,double height){
        RectangularShape tmp = (RectangularShape) m_ball_face;
        m_center.setLocation(width,height);

        double w = tmp.getWidth();
        double h = tmp.getHeight();
        tmp.setFrame(m_center.getX(), m_center.getY(),w,h);

        m_ball_face = tmp;

    }

    public void ResetPosition(){
        RectangularShape tmp = (RectangularShape) m_ball_face;
        m_center.setLocation(1101/2,701/2);

        double w = tmp.getWidth();
        double h = tmp.getHeight();
        tmp.setFrame(m_center.getX(), m_center.getY(),w,h);

        m_ball_face = tmp;

    }

    public void stop(){
        m_speedX = 0;
        m_speedY = 0;
    }

    public void stopX(){
        m_speedX = 0;
    }

    public void stopY(){
        m_speedY = 0;
    }

    public Point2D getPosition(){
        return m_center;
    }

    public double getPositionX(){
        return m_center.getX();
    }

    public double getPositionY(){
        return m_center.getY();
    }

    public int getM_radius(){return m_radius;}

    public Shape getM_ball_face(){
        return m_ball_face;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(m_ball_type, (int) m_center.getX()-m_radius,(int) m_center.getY()-m_radius,2*m_radius,2*m_radius,null);
        g2d.setColor(new Color(255,255,255));

    }

    public BufferedImage rotate_ball(BufferedImage image, double degreesAngle) {
        AffineTransform transform = new AffineTransform();

        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage result = new BufferedImage(w+100, h+100, image.getType());
        Graphics2D g2 = result.createGraphics();
        g2.rotate(Math.toRadians(degreesAngle), w / 2, h / 2);
        g2.drawImage(image, null, 0, 0);
        return result;
    }
    public void turn(double degree){
        //can be changed

        m_ball_type = rotate_ball(m_ball_type, degree);
    }

}

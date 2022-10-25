package robocup.component.player;

import robocup.component.Ball;
import robocup.component.Team.Team;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Player {

    protected final int m_team;
    final static double MAX_SPEED = 20;
    private Shape playerFace;
    protected Point2D m_center;
    protected double m_homeRegionX;
    protected double m_homeRegionY;
    private BufferedImage shirt;
    private int m_radius;
    private String m_name;
    protected double rate = 15;//maybe final to control speed
    protected int side;

    protected boolean m_receivePlayer = false;


    Team m_teammate;

    private int m_yellow;
    private int m_red;
    protected double m_speedX;
    protected double m_speedY;
    protected boolean m_is_ball;
    private int m_energy;
    private double m_direction;//angle of face
    protected Ball m_ball;
    public boolean moved;
    public boolean injured;
    public int InjCount;
    private Point2D oldcenter;
    private double bufferSpeed;

    private AffineTransform transform = new AffineTransform();

    private BufferedImage kit1 = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);
    private BufferedImage kit2 = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);
    private BufferedImage kit3 = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);
    private BufferedImage kit4 = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);
    private BufferedImage kit5 = new BufferedImage(60,60,BufferedImage.TYPE_4BYTE_ABGR);

    {
        try {
            kit1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Kit/ReducedArgentina.png"))); //getting all the images into the buffered images
            kit2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Kit/ReducedBrazil.png")));
            kit3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Kit/ReducedEngland.png")));
            kit4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Kit/ReducedFrance.png")));
            kit5 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Kit/ReducedGermany.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player(Point2D center, int radiusA, int radiusB, int kit, int side,Team mate, Ball ball){


        this.m_center = center;
        this.oldcenter = center; //initialising the player
        playerFace = makePlayer(center,radiusA,radiusB);
        getKit(kit);
        this.m_team = side;
        this.m_radius = radiusA;
        this.m_is_ball= false;
        this.m_speedX = 0;
        this.m_speedY = 0;
        this.m_ball = ball;
        this.shirt = rotatePlayer(shirt, 90);
        this.m_red = 0;
        this.m_yellow = 0;
        this.m_homeRegionX = center.getX();
        this.m_homeRegionY = center.getY();
        this.side = side;
        this.m_teammate = mate;
        /*m_possession = true;*/
        if (side == 1){
            this.turn(180);
        }
    }

    public int getSide() {
        return side;
    }

    public double getM_homeRegionX(){ return this.m_homeRegionX;}
    public double getM_homeRegionY(){ return this.m_homeRegionY;}

    public Team getM_teammate(){
        return this.m_teammate;
    }
    public int getM_yellow() {
        return m_yellow;
    }

    public void setM_yellow(int m_yellow) {
        this.m_yellow = m_yellow;
    }

    public int getM_red() {
        return m_red;
    }

    public void setM_red(int m_red) {
        this.m_red = m_red;
    }

    private void getKit (int kit ) {
        if(kit == 0){
            this.shirt = kit1;
        }
        else if(kit == 1){
            this.shirt = kit2;
        }
        else if(kit == 2){
            this.shirt = kit3;
        }
        else if(kit == 3){
            this.shirt = kit4;
        }
        else if(kit == 4){
            this.shirt = kit5;
        }
    }


    public int getM_team(){return m_team;}          //getters and setters regarding the player
    public boolean getM_is_ball ( ) {
        return m_is_ball;
    }

    public void setM_is_ball (boolean m_is_ball){
        this.m_is_ball = m_is_ball;
    }

    public void changeM_is_ball(){
        this.m_is_ball = !this.m_is_ball;
    }

    public int getM_energy ( ) {
        return m_energy;
    }

    public void setM_energy (int m_energy) {
        this.m_energy = m_energy;
    }

    public double getM_direction ( ) {
        return m_direction;
    }

    public void setM_direction (int m_direction) {
        this.m_direction = m_direction;
    }

    public void setM_speedX (double s){
        m_speedX = s;
    }

    public void setM_speedY (double s){
        m_speedY = s;
    }

    public void setM_speed (double x, double y) {
        m_speedX = x;
        m_speedY = y;

    }

    public double getM_speedX (){
        return m_speedX;
    }

    public double getM_speedY (){
        return m_speedY;
    }


    public void setPosition(double width,double height){
        RectangularShape tmp = (RectangularShape) playerFace;
        m_center.setLocation(width,height);

        double w = tmp.getWidth();
        double h = tmp.getHeight();
        tmp.setFrame(m_center.getX(), m_center.getY(),w,h);
        playerFace = tmp;
    }

    public double getM_radius(){return m_radius;}
    public double getPositionX(){return m_center.getX();}

    public double getPositionY(){return m_center.getY();}

    public Point2D getPosition(){
        return m_center;
    }

    public Shape getPlayerFace(){
        return playerFace;
    }



    protected Shape makePlayer(Point2D center, int radiusA, int radiusB) { //method that creates the shape of the player
        double x = center.getX() - (radiusA / 2);
        double y = center.getY() - (radiusB / 2);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }

    public void move() {        //method that moves the player
        RectangularShape tmp = (RectangularShape) playerFace;
        m_center.setLocation((m_center.getX() + this.m_speedX / rate), (m_center.getY() + this.m_speedY / rate));

        double w = tmp.getWidth();
        double h = tmp.getHeight();
        tmp.setFrame(m_center.getX(), m_center.getY(), w, h);

        playerFace = tmp;
    }

    public void move(double X,double Y){//method that moves the player
        double distance = this.m_center.distance(X,Y);
        if(distance - bufferSpeed/rate <= 0){
            this.stop();
        }
        else{
            m_speedX = bufferSpeed*((X- this.m_center.getX())/distance);
            m_speedY = bufferSpeed*((Y -this.m_center.getY())/distance);
            this.move();
        }
    }

    public void move(Point2D p){ //method that moves the player
        double distance = this.m_center.distance(p);
        if(distance - bufferSpeed/rate <= 0){
            this.stop();
        }
        else{
            this.m_speedX = bufferSpeed*((p.getX()- this.m_center.getX())/distance);
            this.m_speedY = bufferSpeed*((p.getY() -this.m_center.getY())/distance);
            this.move();
        }
    }

    public void move(Ball ball, double X, double Y){
        this.move(X,Y);
        ball.setSpeed(this.m_speedX,this.m_speedY);

    }

    public void move(Ball ball,Point2D p){
        this.move(p);
        ball.setSpeed(this.m_speedX,this.m_speedY);

    }
    //maybe accept a speed para
    //overload move method move to ball
    //add a boolean para when
    public void move(Ball ball) {
        if (turnToBall(ball)){
            double distance = distanceToBall(ball);
            if (distance <= (ball.getM_radius() + this.m_radius)) {
                this.stop();

            } else if (distance > (ball.getM_radius() + this.m_radius)) {

                m_speedX = bufferSpeed * (distanceXToBall(ball) / distance);
                m_speedY = bufferSpeed * (distanceYToBall(ball) / distance);
                this.move();
            }
        }
    }

    public double distanceToP (double x, double y){
        return Math.pow(Math.pow(m_center.getX()-x,2)+Math.pow(m_center.getY()-y,2),0.5);
    }
    //should add an accelerate method

    //turn around(turn clockwise)
    public void turn(double degree){    //method that turns the player
        //can be changed
        m_direction = m_direction+degree;
        while(m_direction>360){
            m_direction = m_direction -360;
        }
        while(m_direction<0){
            m_direction = m_direction +360;
        }
        shirt = rotatePlayer(shirt, degree);
    }

    public boolean turnToPoint(Point2D p){ //method that turns to a point
        double delta = degreeToPoint(p);
        if (Math.abs(delta) < 5){
            turn(delta);
            return true;
        }
        else{
            if (delta > 0){
                turn(5);
            }
            else{
                turn(-5);
            }
            return false;
        }
    }

    public boolean turnToBall(Ball ball){ //method that turns to a ball
        return turnToPoint(ball.getPosition());
    }



    public boolean turnWithBall(Ball ball, Point2D p){  //method turns the player with the ball
        System.out.println(m_direction);
        double ball_X;
        double ball_Y;
        boolean result = turnToPoint(p);
        ball_X = this.m_center.getX() + distanceToBall(ball) * Math.cos(Math.toRadians(m_direction));
        ball_Y = this.m_center.getY() + distanceToBall(ball) * Math.sin(Math.toRadians(m_direction));
        ball.stop();
        ball.setPosition(ball_X,ball_Y);
        System.out.println(m_direction);
        return result;
    }



    //based on rule
    public void kick(Ball ball, double force){
        //method that kicks the ball with set force

        if(m_is_ball && this.getPosition().distance(ball.getPosition())< (ball.getM_radius() + this.m_radius) +200){

            double speedX = 0;
            double speedY = 0;

            double direction = m_direction;
            System.out.println(direction);
            speedX = force*Math.cos(Math.toRadians(direction));
            speedY = force*Math.sin(Math.toRadians(direction));
            ball.setSpeed(speedX,speedY);
        }
    }

    public void kick(Ball ball, Point2D target){
            double speed = Math.pow(2*ball.ACCELERATE*target.distance(this.m_center),0.5);
            kick(ball,speed*8);

    }

    /*public double rndkicking(){
      double lowerdirection = m_direction - 15;
      double higherdirection = m_direction + 15;

      Random rand = new Random();
      Double newDirection = lowerdirection + rand.nextDouble((higherdirection - lowerdirection) + 1);
      return newDirection;
    }*/


    public void dash(){
        double speedX = 0;
        double speedY = 0;
        speedX = MAX_SPEED*Math.cos(Math.toRadians(m_direction));
        speedY = MAX_SPEED*Math.sin(Math.toRadians(m_direction));
        if (m_speedX < speedX && m_speedY < speedY){
            setM_speedX(m_speedX + 3); // accelerate
            setM_speedY(m_speedY + 3);
        }
        else if(m_speedX >= speedX && m_speedY < speedY){
            setM_speedX(speedX);
            setM_speedY(m_speedY + 3);
        }
        else if(m_speedX < speedX && m_speedY >= speedY) {
            setM_speedX(m_speedX + 3);
            setM_speedY(speedY);
        }
        else{
            setM_speedX(speedX);
            setM_speedY(speedY);
        }

    }

    public void changeM_receivePlayer() {
        this.m_receivePlayer = !m_receivePlayer;
    }

    //need develop
    public void energyDown(){
        m_energy = m_energy - 1;
    }
    public void energyUp(){
        if (m_energy < 100){
            m_energy = m_energy + 1;
        }
    }

    public void stopX(){
        m_speedX = 0;
    }

    public void stopY(){
        m_speedY = 0;
    }

    public void stop() {
        m_speedX = 0;
        m_speedY = 0;
    }
    //use point2D
    //need improve
    public double distanceXToBall(Ball ball){
        return ball.getPositionX()-this.getPositionX();
    }

    public double distanceYToBall(Ball ball){
        return ball.getPositionY()-this.getPositionY();
    }

    public double distanceToBall(Ball ball){
        return (Math.sqrt(Math.pow(distanceXToBall(ball),2)+Math.pow(distanceYToBall(ball),2)));
    }

    public double degreeToPoint(Point2D p) {     //calculate the degree between player and a point

        double theta;
        double delta;
        if (p.getY() >= this.getPositionY()) {
            theta = Math.toDegrees(Math.acos((p.getX()-this.getPositionX()) / this.m_center.distance(p)));
            if (m_direction >= theta && m_direction <= theta + 180) {
                delta = theta - m_direction;
            } else {
                if (m_direction < theta) {
                    delta = theta - m_direction;
                } else {
                    delta = theta - m_direction + 360;
                }
            }
        } else {
            theta = 360 - Math.toDegrees(Math.acos((p.getX()-this.getPositionX()) / this.m_center.distance(p)));
            if (m_direction <= theta && m_direction >= theta - 180) {
                delta = theta - m_direction;
            } else {
                if (m_direction > theta) {
                    delta = theta - m_direction;
                } else {
                    delta = theta - m_direction - 360;
                }
            }
        }
        return delta;
    }

    public double degreeToPoint(double X, double Y){
        double delta;
        Point2D p = new Point2D.Double(X, Y);
        delta = degreeToPoint(p);
        return delta;
    }

    public double degreeToBall(Ball ball){
        double delta;
        delta = degreeToPoint(ball.getPosition());
        return delta;

    }

    public void draw(Graphics2D g2d) {  //print the player on the pitch
        g2d.drawImage(shirt, (int) m_center.getX()-m_radius,(int) m_center.getY()-m_radius,m_radius*2,m_radius*2,null);
        g2d.setColor(new Color(255,255,255));
    }


    public BufferedImage rotatePlayer(BufferedImage image, double degreesAngle) {  //rotate the image of the player
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage result = new BufferedImage(w, h, image.getType());
        Graphics2D g2 = result.createGraphics();
        g2.rotate(Math.toRadians(degreesAngle), w / 2, h / 2);
        g2.drawImage(image, null, 0, 0);
        return result;
    }

    public void execute(){

    }

    public void setBufferSpeed(double bufferSpeed) {
        this.bufferSpeed = bufferSpeed;
    }
}


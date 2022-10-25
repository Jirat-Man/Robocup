package robocup;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @auther Jirat Manpadungkit
 * Players kit can be changed
 */
public class TeamSelectFrame extends JFrame implements KeyListener,
        MouseListener, MouseMotionListener {

    private final Rectangle BackButton = new Rectangle(new Dimension(130,50));
    private final Rectangle Change1 = new Rectangle(new Dimension(100,75));
    private final Rectangle Change2 = new Rectangle(new Dimension(100,75));
    private final Rectangle Change3 = new Rectangle(new Dimension(100,75));
    private final Rectangle Change4 = new Rectangle(new Dimension(100,75));
    private final Rectangle changeBall = new Rectangle(new Dimension(180,180));

    private final Rectangle Start = new Rectangle(new Dimension(250,125));

    public static int CounterLeft = 0;
    public static int CounterRight = 1;
    public static int BallCounter = 0;

    BufferedImage backgroundImage,teamSelectImage,arrowLeft,
            arrowRight,StartImage,BackImage, ArgentinaImage,
            BrazilImage, EnglandImage, FranceImage,
            GermanyImage, footballImage, ConfusedImage,
            ArgText, BrzText, EngText, FraText, GerText
        ,ball8Image, boxFrame;


    {
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/start.jpeg")));
            arrowLeft= ImageIO.read(Objects.requireNonNull(getClass().getResource("/Arrow/LeftArrow.png")));
            arrowRight= ImageIO.read(Objects.requireNonNull(getClass().getResource("/Arrow/RightArrow.png")));
            StartImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/StartButton.png")));
            teamSelectImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Team Select.png")));
            ArgentinaImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Kit/ArgentinaKit.png")));
            BrazilImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Kit/BrazilKit.png")));
            EnglandImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Kit/EnglandKit.png")));
            FranceImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Kit/FranceKit.png")));
            GermanyImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Kit/GermanyKit.png")));
            footballImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Ball/Football.png")));
            ConfusedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Ball/ConfusedBall.png")));
            ball8Image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Ball/8ball.png")));
            BackImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Back.png")));
            ArgText = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Argentina.png")));
            BrzText = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Brazil.png")));
            EngText = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/England.png")));
            FraText = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/France.png")));
            GerText = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Germany.png")));
            boxFrame = ImageIO.read(Objects.requireNonNull(getClass().getResource("/BoxFrame.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TeamSelectFrame ( ) {
        this.initialize();
        setRectLocation();
        Timer timer = new Timer(1000, null);
        timer.start();
        repaint();
    }

    public void initialize(){
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight()));
        this.pack();
        this.setTitle("Team Select");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    private void setRectLocation ( ) {
        Change1.setLocation(75,120);
        Change2.setLocation(225,120);
        Change3.setLocation(635,120);
        Change4.setLocation(785,120);
        changeBall.setLocation(400,300);
        Start.setLocation(375, 500);
        BackButton.setLocation(0,10);
    }
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0,0,null);
        g2d.drawImage(boxFrame,40, 95, 330,450,null);
        g2d.drawImage(boxFrame, 605, 95,330,450,null);
        g2d.drawImage(teamSelectImage, 190,0,null);
        g2d.drawImage(arrowLeft, 50, 100, 150,100,null);
        g2d.drawImage(arrowRight, 200, 100, 150,100,null);
        g2d.drawImage(arrowLeft, 610, 100,150,100, null);
        g2d.drawImage(arrowRight, 770, 100, 150,100,null);
        g2d.drawImage(StartImage, 375, 500, 250,125,null);
        g2d.drawImage(BackImage, 0,10, 130,70,null);


        //Choose kit on left
        if(CounterLeft == 0) {
            g2d.drawImage(ArgentinaImage,105, 200, 200, 200,null);
            g2d.drawImage(ArgText, 70,340,280,130,null);
        }
        else if(CounterLeft == 1) {
            g2d.drawImage(BrazilImage, 105, 200, 200, 200, null);
            g2d.drawImage(BrzText, 90,360,230,100,null);
        }
        else if (CounterLeft == 2) {
            g2d.drawImage(EnglandImage, 105, 200, 200, 200, null);
            g2d.drawImage(EngText, 70,340,280,130,null);
        }
        else if (CounterLeft == 3) {
            g2d.drawImage(FranceImage, 105, 200, 200, 200, null);
            g2d.drawImage(FraText, 80,350,260,120,null);
        }
        else if (CounterLeft == 4) {
            g2d.drawImage(GermanyImage, 105, 200, 200, 200, null);
            g2d.drawImage(GerText, 70,340,280,130,null);
        }
        //Choose kit  on right
        if(CounterRight == 0) {
            g2d.drawImage(ArgentinaImage, 670, 200, 200,200,null);
            g2d.drawImage(ArgText, 635,340,280,130,null);
        }
        else if(CounterRight == 1) {
            g2d.drawImage(BrazilImage, 670, 200, 200, 200, null);
            g2d.drawImage(BrzText, 655,360,230,100,null);
        }
        else if (CounterRight == 2) {
            g2d.drawImage(EnglandImage, 670, 200, 200, 200, null);
            g2d.drawImage(EngText, 630,340,280,130,null);
        }
        else if (CounterRight == 3) {
            g2d.drawImage(FranceImage, 670, 200, 200, 200, null);
            g2d.drawImage(FraText, 640,350,260,120,null);
        }
        else if (CounterRight == 4) {
            g2d.drawImage(GermanyImage, 670, 200, 200, 200, null);
            g2d.drawImage(GerText, 630,340,280,130,null);
        }
        //Choose ball
        if(BallCounter == 0){
            g2d.drawImage(footballImage, 385, 300, 200,200,null);
        }
        if(BallCounter == 1){
            g2d.drawImage(ConfusedImage, 385, 300, 200,200,null);
        }
        if(BallCounter == 2){
            g2d.drawImage(ball8Image, 385, 300, 200,200,null);
        }

    }

    @Override
    public void keyTyped (KeyEvent e) {

    }

    @Override
    public void keyPressed (KeyEvent e) {

    }

    @Override
    public void keyReleased (KeyEvent e) {

    }

    @Override
    public void mouseClicked (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(Change1.contains(p)){
            CounterLeft--;
            if(CounterLeft < 0){
                CounterLeft = 4;
            }
            repaint();
        }
        if(Change2.contains(p)){
            CounterLeft++;
            if(CounterLeft > 4){
                CounterLeft = 0;
            }
            repaint();
        }
        if(Change3.contains(p)){
            CounterRight--;
            if(CounterRight < 0){
                CounterRight = 4;
            }
            repaint();
        }
        if(Change4.contains(p)){
            CounterRight++;
            if(CounterRight > 4){
                CounterRight = 0;
            }
            repaint();
        }
        if(changeBall.contains(p)){
            BallCounter++;
            if(BallCounter > 2){
                BallCounter = 0;
            }
            repaint();
        }
        if(Start.contains(p)){

            try {
                new GameFrame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        }
        if(BackButton.contains(p)){
            new SplashScreen();
            this.setVisible(false);
            this.dispose();
        }
    }

    @Override
    public void mousePressed (MouseEvent e) {
        Point p = e.getPoint();


    }

    @Override
    public void mouseReleased (MouseEvent e) {

    }

    @Override
    public void mouseEntered (MouseEvent e) {

    }

    @Override
    public void mouseExited (MouseEvent e) {

    }

    @Override
    public void mouseDragged (MouseEvent e) {

    }

    @Override
    public void mouseMoved (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (Change1.contains(p) ||Change2.contains(p) ||Change3.contains(p) ||Change4.contains(p)
                || Start.contains(p)||changeBall.contains(p)||BackButton.contains(p)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else{
            this.setCursor(Cursor.getDefaultCursor());
    }
    }

}

package robocup;
import robocup.buttons.ExitButton;
import robocup.buttons.MusicButton;
import robocup.buttons.SettingsButton;
import robocup.buttons.StartButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SplashScreen extends JFrame implements KeyListener,
        MouseListener, MouseMotionListener {

    public static MusicPlayer music;


    private final Rectangle StartButton = new Rectangle(new Dimension(200, 100));
    private final Rectangle SettingsButton = new Rectangle(new Dimension(290, 100));
    private final Rectangle ExitButton = new Rectangle(new Dimension(200, 100));

    BufferedImage BackgroundImage, StartImage,
            MusicImage, RoboCupImage, SettingsImage,
            ExitImage, BlueImage;

    {
        try {
            BlueImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/BlueBack.png")));
            BackgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/start.jpeg")));
            StartImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/StartButton.png")));
            MusicImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Music.png")));
            RoboCupImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Robocup Reloaded.png")));
            ExitImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Exit.png")));
            SettingsImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Settings.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SplashScreen ( ) {
        initialize();
        setRectLocation();
        if (MusicPlayer.musicCounter < 1) {
            music = new MusicPlayer("src/main/resources/Music/jazzy-abstract-beat-11254.wav");
        }

    }

    private void setRectLocation ( ) {
        StartButton.setLocation(60,180);
        SettingsButton.setLocation(50,250);
        ExitButton.setLocation(60,340);
    }
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(BackgroundImage,0,0,null);
        g2d.drawImage(BlueImage, 0,0,290, BackgroundImage.getHeight(), null);
        g2d.drawImage(RoboCupImage, 30,0,null);
        g2d.drawImage(StartImage, 50, 180,200,100,null);
        g2d.drawImage(SettingsImage, 35,250,300,100, null);
        g2d.drawImage(ExitImage, 50,340,200,90,null);
    }

    public void initialize ( ) {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(BackgroundImage.getWidth(), BackgroundImage.getHeight()));
        this.pack();
        this.setTitle("RoboCup Reloaded");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
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
    public void mouseClicked (MouseEvent e) {
        Point p = e.getPoint();
        if (StartButton.contains(p)) {
            new TeamSelectFrame();
            this.setVisible(false);
            this.dispose();
        }
        if (SettingsButton.contains(p)) {
            new SettingsFrame();
            
        }
        if (ExitButton.contains(p)) {
            System.exit(0);
        }
    }

    @Override
    public void mousePressed (MouseEvent e) {

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
    public void mouseMoved (MouseEvent e) {
        Point p = e.getPoint();
        if (StartButton.contains(p) || SettingsButton.contains(p) || ExitButton.contains(p)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            this.setCursor(Cursor.getDefaultCursor());

        }
    }
}

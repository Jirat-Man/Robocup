package robocup;

import robocup.buttons.BackButton;
import robocup.buttons.ExitButton;
import robocup.buttons.MainButton;
import robocup.timer.MatchTimer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class MenuFrame extends JFrame implements KeyListener,
        MouseListener, MouseMotionListener {

    private final Rectangle BackButton
            = new Rectangle(new Dimension(220, 110));
    private final Rectangle MainMenuButton
            = new Rectangle(new Dimension(385, 120));
    private final Rectangle ExitButton =
            new Rectangle(new Dimension(190, 110));

    BufferedImage BackGroundImage, BackImage,
            MainMenuImage, ExitImage;

    {
        try {
            BackGroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/start.jpeg")));
            BackImage =  ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Back.png")));
            MainMenuImage =  ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Main Menu.png")));
            ExitImage =  ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Exit.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MenuFrame ( ) {

        initialize();
        setRectLocation();

    }

    private void setRectLocation ( ) {
        BackButton.setLocation(60, 100);
        MainMenuButton.setLocation(25,183);
        ExitButton.setLocation(60,290);
    }

    private void initialize ( ) {

        this.setPreferredSize(new Dimension(BackGroundImage.getWidth(), BackGroundImage.getHeight()));
        this.pack();
        this.setTitle("Menu");
        this.setVisible(false);
        this.setLocationRelativeTo(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(BackGroundImage,0,0,null);
        g2d.drawImage(BackImage,60,100,220,110,null);
        g2d.drawImage(MainMenuImage,35,183,385,120,null);
        g2d.drawImage(ExitImage,60,290,190,110,null);
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
        if(BackButton.contains(p)){
            this.dispose();

            MatchTimer.timer.start();

            Pitch.gameTimer.start();
            Pitch.gamePlaying = true;
        }
        if (MainMenuButton.contains(p)){
            //remove(GameFrame.playerFrame);
            GameFrame.playerFrame.dispose();
            this.dispose();
            MatchTimer.resetTimer();
            Pitch.gameTimer.restart();
            Pitch.gameTimer.stop();
            Pitch.gamePlaying = false;
            SwingUtilities.updateComponentTreeUI(GameFrame.playerFrame);
            RoboCupApp.startFrame = new SplashScreen();
        }
        if(ExitButton.contains(p)){
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
        if (BackButton.contains(p) ||MainMenuButton.contains(p) ||ExitButton.contains(p) ) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
}


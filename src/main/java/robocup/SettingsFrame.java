package robocup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

import static robocup.SplashScreen.music;

public class SettingsFrame extends JFrame implements KeyListener,
        MouseListener, MouseMotionListener {

    private final Rectangle BackButton = new Rectangle(new Dimension(200,100));
    private final Rectangle MusicButton = new Rectangle(new Dimension(200,100));

    BufferedImage backgroundImage,BackImage,MusicImage;

    {
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/start.jpeg")));
            BackImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Back.png")));
            MusicImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Text/Music.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SettingsFrame() {
        this.initialize();
        setRectLocation();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage,0,0,null);
        g2d.drawImage(BackImage,60,100,200,100,null);
        g2d.drawImage(MusicImage,60,200,200,100,null);
    }

    private void setRectLocation ( ) {
        BackButton.setLocation(60,100);
        MusicButton.setLocation(60,200);
    }

    public void initialize(){
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight()));
        this.pack();
        this.setTitle("Setting");
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
        if(BackButton.contains(p)){
            this.dispose();
        }
        if(MusicButton.contains(p)){
            music.stop();
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
        if(BackButton.contains(p) || MusicButton.contains(p)){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
}

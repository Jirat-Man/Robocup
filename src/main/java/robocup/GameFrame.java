package robocup;

import robocup.timer.MatchTimer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {
    public static JFrame playerFrame = new JFrame();
    public GUI UI = new GUI();
    public Pitch pitch = new Pitch(UI);

    public GameFrame() throws IOException {

        playerFrame = new JFrame();
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerFrame.setLocationRelativeTo(null);
        playerFrame.setTitle("RoboCup Reloaded");
        playerFrame.getContentPane().add(pitch, BorderLayout.CENTER);
        playerFrame.getContentPane().add(UI,BorderLayout.NORTH);
        playerFrame.setPreferredSize(new Dimension(1150, 786));
        playerFrame.pack();
        playerFrame.setLocationRelativeTo(null);
        playerFrame.setVisible(true);
        MatchTimer.timer.start(); //start timer



    }

}
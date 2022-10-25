package robocup.buttons;

import robocup.MenuFrame;
import robocup.Pitch;
import robocup.timer.MatchTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButton extends JButton implements ActionListener {
    public MainButton()
    {
        this.setText("Menu");
        this.setFont(new Font("SansSerif", Font.BOLD, 14));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setForeground(Color.BLACK);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        MatchTimer.timer.stop();
        Pitch.gameTimer.stop();
        Pitch.gamePlaying = false;
        MenuFrame Menu = new MenuFrame();
        Menu.setVisible(true);
    }
}

package robocup.buttons;

import robocup.Pitch;
import robocup.timer.MatchTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PauseButton extends JButton implements ActionListener {

    public PauseButton(){
        this.setText("Pause");
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setForeground(Color.BLACK);
        this.setFont(new Font("SansSerif", Font.BOLD, 14));
        this.addActionListener(this);
    }

    public MatchTimer getMatchTimer(){
        return t;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(Pitch.gamePlaying){
            t.pauseTimer();
            Pitch.gameTimer.stop();
            Pitch.gamePlaying = false;
        }
        else {
            Pitch.gameTimer.start();
            Pitch.gamePlaying = true;
        }

    }

    MatchTimer t = new MatchTimer();
}

package robocup.timer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class MatchTimer extends JLabel implements ActionListener {
    static int min = 0, sec=0;
    public static Timer timer;
    public static boolean halftime = false;
    public static boolean fulltime = false;
    public static int IT = 0;

    public MatchTimer() {
        //the formatting of the label
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        this.setFont(new Font("SansSerif", Font.BOLD, 14));
        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        timer = new Timer(5, this); //delay variable controls how quick the timer goes
        timer.setInitialDelay(1);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!fulltime)
        sec++;
        //what controls the label going from 60 seconds to 1 minute
        if(sec == 60 && !fulltime)
        {
            sec = 0;
            min++;
        }

        //this code handles the formatting of time label so there is the appropriate 0 if the time isn't double digits


        if(min > 89 && !fulltime){
            this.setBackground(Color.RED);

            if(min > IT + 90)
                fulltime = true;

            if(min > 89 && sec > 10)
                this.setText(min + ":" + sec + "+" + IT +" ");

            if(min > 89 && sec < 10)
                this.setText( min + ":0" + sec + "+" + IT+ " ");
        }
        else if(min > 44 && !halftime){
            this.setBackground(Color.RED);

            if(min > IT + 45){
                halftime = true;
                min = 45;
            }


            if(min > 44 && sec > 10)
                this.setText(min + ":" + sec + "+" + IT +" ");

            if(min > 44 && sec < 10)
                this.setText( min + ":0" + sec + "+" + IT+ " ");
        }
        else if(min > 10 ){
            this.setBackground(Color.WHITE);
            if(min > 10 && sec > 10)
                this.setText(min + ":" + sec);

            if(min > 10 && sec < 10)
                this.setText(min + ":0" + sec);
        }
        else if(min < 10){

            if(min < 10 && sec < 10){
                this.setBackground(Color.WHITE);
                this.setText("0" + min + ":0" + sec);
            }


            if(min < 10 && sec > 10){
                this.setBackground(Color.WHITE);
                this.setText("0" + min + ":" + sec);
            }

        }

    }

    //method to pause the timer when the pause button was pressed
    public void pauseTimer ( )
    {
        if(timer.isRunning())
        timer.stop();
        else
            timer.start();
    }

    //method to reset timer
    public static void resetTimer ( ){
        timer.restart();
        sec = 0;
        min = 0;
        halftime = false;
        fulltime = false;
        timer.stop();
    }
}
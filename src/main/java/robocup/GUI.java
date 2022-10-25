package robocup;

import robocup.buttons.MainButton;
import robocup.buttons.PauseButton;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {

    //MatchTimer Time = new MatchTimer();
    JLabel Team1 = new JLabel();
    JLabel Team2 = new JLabel();
    JLabel Score = new JLabel();


    PauseButton Pause = new PauseButton();
    MainButton mainMenu = new MainButton();

    public GUI()
    {
        switch(TeamSelectFrame.CounterRight){
            case 0:
                Team1.setText("ARG");
                Team1.setBackground(Color.BLUE);
                Team1.setForeground(Color.WHITE);
                break;
            case 1:
                Team1.setText("BRA");
                Team1.setBackground(Color.YELLOW);
                Team1.setForeground(Color.GREEN);
                break;
            case 2:
                Team1.setText("ENG");
                Team1.setBackground(Color.WHITE);
                Team1.setForeground(Color.RED);
                break;
            case 3:
                Team1.setText("FRA");
                Team1.setBackground(Color.BLUE);
                Team1.setForeground(Color.RED);
                break;
            case 4:
                Team1.setText("GER");
                Team1.setBackground(Color.black);
                Team1.setForeground(Color.RED);
                break;
        }
        switch(TeamSelectFrame.CounterLeft){
            case 0:
                Team2.setText("ARG");
                Team2.setBackground(Color.BLUE);
                Team2.setForeground(Color.WHITE);
                break;
            case 1:
                Team2.setText("BRA");
                Team2.setBackground(Color.YELLOW);
                Team2.setForeground(Color.GREEN);
                break;
            case 2:
                Team2.setText("ENG");
                Team2.setBackground(Color.WHITE);
                Team2.setForeground(Color.RED);
                break;
            case 3:
                Team2.setText("FRA");
                Team2.setBackground(Color.BLUE);
                Team2.setForeground(Color.RED);
                break;
            case 4:
                Team2.setText("GER");
                Team2.setBackground(Color.black);
                Team2.setForeground(Color.RED);
                break;
        }

        Score.setText("0-0");


        Score.setBackground(Color.WHITE);

        Team1.setOpaque(true);
        Team2.setOpaque(true);
        Score.setOpaque(true);

        Score.setForeground(Color.BLACK);

        Team1.setFont(new Font("SansSerif", Font.BOLD, 14));
        Score.setFont(new Font("SansSerif", Font.BOLD, 14));
        Team2.setFont(new Font("SansSerif", Font.BOLD, 14));

        this.add(mainMenu);
        this.add(Team1);
        this.add(Score);
        this.add(Team2);
        this.add(Pause.getMatchTimer());
        this.add(Pause);

        this.setBackground(Color.WHITE);
    }
}

package robocup;

import robocup.component.Ball;
import robocup.component.player.Player;
import robocup.component.Team.Team;
import robocup.component.Team.TeamLeft;
import robocup.component.Team.TeamRight;
import robocup.timer.MatchTimer;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Pitch extends JPanel {

    private BufferedImage PitchImage;
    private BufferedImage CrossImage;

    {
        try {
            PitchImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/pitch.png")));     //getting all the images into the buffered images
            CrossImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/injury.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int teamsize = 11;


    private final static int NUMBER_OF_HORIZONTAL = 30;
    private final static int NUMBER_OF_VERTICAL = 15;

    private final static Region[][] m_region = new Region[NUMBER_OF_VERTICAL][NUMBER_OF_HORIZONTAL];


    private final double top = 18;    //defining the pitch bounds
    private final double bot = 18 + 683;
    private final double left = 48;
    private final double right = 48+1053;
    private final double goal_top =323.5;
    private final double goal_bot =395.5;

    private final double WIDTH = 1053;
    private final double HEIGHT = 683;

    private RND chance = new RND();
    private AudioHandler audio = new AudioHandler();
    private AudioHandler audio2 = new AudioHandler();

    private final Point2D left_top = new Point2D.Double(left,top);        //setting the 2dPoints for it
    private final Point2D left_bot = new Point2D.Double(left,bot);
    private final Point2D right_top = new Point2D.Double(right,top);
    private final Point2D right_bot = new Point2D.Double(right,bot);

    private final Point2D goal_left_top = new Point2D.Double(left,goal_top);
    private final Point2D goal_left_bot = new Point2D.Double(left,goal_bot);
    private final Point2D goal_right_top = new Point2D.Double(right,goal_top);
    private final Point2D goal_right_bot = new Point2D.Double(right,goal_bot);

    private final Point2D goal_left_center = new Point2D.Double(left,(goal_bot+goal_top)/2);
    private final Point2D goal_right_center = new Point2D.Double(right,(goal_bot+goal_top)/2);

    int goalteam1 = 0;
    int goalteam2 = 0;
    int oldgoal1 = 0;
    int oldgoal2 = 0;
    private int goal_team = 0;

    int goaldelay = 0;

    int sendoffloc = 30;

    boolean red = false;
    boolean yellow = false;
    boolean injury = false;

    boolean check = true;

    int InjuryTime = 0;

    int count = 0;

    Player[] PSet1;
    Player[] PSet0;

    Team team_0;
    Team team_1;

    Point2D ini_ball = new Point2D.Double((left+right)/2,(top+bot)/2);  //starting point for ball and players

    Point2D pos_player0 = new Point2D.Double(((left+right)/2 - 50),(top+bot)/2);
    Point2D pos_player1 = new Point2D.Double(((left+right)/2 + 60),(top+bot)/2);
    Point2D KeeperPos0 = new Point2D.Double(70,(top+bot)/2);
    Point2D KeeperPos1 = new Point2D.Double(1080,(top+bot)/2);

    Ball ball = new Ball(ini_ball,18,18, TeamSelectFrame.BallCounter);     //creating ball and players

    /*Player player1 = new Player(pos_player0, 18, 18, TeamSelectFrame.CounterLeft, 0,team_0, ball);
    Player player2 = new Player(pos_player1, 18, 18, TeamSelectFrame.CounterRight, 1, team_1, ball);*/

    //GoalKeeper keeper1 = new GoalKeeper(KeeperPos0, 18, 18, TeamSelectFrame.CounterLeft, 0, team_0, ball);
    //GoalKeeper keeper2 = new GoalKeeper(KeeperPos1, 18, 18, TeamSelectFrame.CounterRight, 1, team_1, ball);

        //players for prototype

    //create players for match
    // SettingsFrame temp = new SettingsFrame(); //instance of SettingsFrame to allow number of players per team to be accessed
    //int teamsize = temp.getNumberOfPlayers();

   /* Player[] team1 = new Player[teamsize];
    Player[] team2 = new Player[teamsize];*/ //2 empty arrays waiting for their teams
    //2 empty arrays waiting for their teams


    public static Timer gameTimer;    //label timer
    public static Boolean gamePlaying = true;

    public int getGoalteam1() {
        return goalteam1;
    }

    public int getGoalteam2() {
        return goalteam2;
    }

    public Pitch(GUI UI) {


        Random random = new Random();
        boolean ini_possession = random.nextBoolean();
        createRegions();
        team_0 = new TeamLeft(1, ini_possession, this.ball);
        team_1 = new TeamRight(2, !ini_possession, this.ball);
        if (ini_possession){
            team_0.getM_PlayerClosestToBall().setM_is_ball(true);
        }
        else{
            team_1.getM_PlayerClosestToBall().setM_is_ball(true);

        }
        team_0.setM_enemy(team_1);
        team_1.setM_enemy(team_0);
        team_0.setRegion_info(this.m_region);
        team_1.setRegion_info(this.m_region);
        AtomicInteger Ycount = new AtomicInteger();
        AtomicInteger Rcount = new AtomicInteger();

        /*createTeams();*/  //actually creates the teams
        MusicPlayer music = new MusicPlayer("src/main/resources/Music/crowd.wav");

        int delay=30;
        ActionListener taskPerformer= e -> {
            if (goalteam1 >= 0 && !MatchTimer.fulltime){                     //the basic set up for the prototype

                if(MatchTimer.halftime && check){
                    try {
                        audio.StartMusic("src/main/resources/Music/whistle.wav");
                        new MusicPlayer("src/main/resources/Music/whistle.wav");
                    } catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    }

                    //reset players position
                    team_1.reset(0);
                    team_0.reset(1);
/*

                    for(int i = 0; i < 7; i++) {

                        Player tempP = PSet1[i];
                        Player tempP0 = PSet0[i];


                    if(tempP.getM_red() == 0)
                        tempP.setPosition(((left+right)/2 - 50),(top+bot)/2);

                    if(tempP0.getM_red() == 0)
                        tempP0.setPosition(((left+right)/2 + 50),(top+bot)/2);
                    ball.ResetPosition();
                        tempP.injured = false;
                        tempP0.injured = false;
*/
                    goaldelay = 0;
                    MatchTimer.IT = 0;
                    check = false;
              //  }
                }
                /*

                if(goaldelay > 20)
                behaviour(player1,player2);
                keeperBehavior(keeper1, keeper2);*/

                goaldelay++;
                PSet1 = team_1.getM_player();
                PSet0 = team_0.getM_player();

                for(int q = 0; q < teamsize; q++){
                    for(int i = 0; i < teamsize; i++){

                        Player tempP = PSet1[i];
                        Player tempP0 = PSet0[q];

                        if(tempP.getPosition().distance(tempP0.getPosition()) < 18*2){
                            try {
                                onCollision(tempP0);
                            } catch (UnsupportedAudioFileException ex) {
                                ex.printStackTrace();
                            } catch (LineUnavailableException ex) {
                                ex.printStackTrace();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                onCollision(tempP);
                            } catch (UnsupportedAudioFileException ex) {
                                ex.printStackTrace();
                            } catch (LineUnavailableException ex) {
                                ex.printStackTrace();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }

                team_0.setKeyPosition();
                team_1.setKeyPosition();
                team_0.execute();
                team_1.execute();
                ball.move();
                team_0.GOAL = 0;
                team_1.GOAL = 0;
                try {
                    isOutBound();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                UI.Score.setText(getGoalteam1()+"-"+getGoalteam2());

            }

            if(MatchTimer.fulltime) {
                try {
                    audio.StartMusic("src/main/resources/Music/fulltime.wav");
                    new MusicPlayer("src/main/resources/Music/fulltime.wav");
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }

            repaint();

            if(yellow){
                Ycount.getAndIncrement();
            }
            if(red){
                Rcount.getAndIncrement();
            }

            if(Rcount.get() > 20){
                red = false;
                Rcount.set(0);
            }

            if(Ycount.get() > 20){
                yellow = false;
                Ycount.set(0);
            }



            for(int q = 0; q < teamsize; q++){
                for(int i = 0; i < teamsize; i++) {

                    Player tempP = PSet1[i];
                    Player tempP0 = PSet0[q];

            if(tempP.injured){
                tempP.InjCount = tempP.InjCount + 1;
            }

            if(tempP.InjCount > 50){
                injury = false;
                PlayerReturn(tempP);
                tempP.InjCount = 0;
                tempP.injured = false;
            }

            if(tempP0.injured){
                tempP0.InjCount = tempP0.InjCount + 1;
            }

            if(tempP0.InjCount > 50){
                injury = false;
                PlayerReturn(tempP0);
                tempP0.InjCount = 0;
                tempP0.injured = false;
            }
                }}
            //add team attribute in play
            //initial play and team in other class
            //add boolean to player so if their team has possession of the ball it is true
            //then behaviour changes based on the boolean
            //so if in possession attack, if not defend

            //defend behaviour will be to move back towards the goal and try intercept the player
            //attack behaviour will be to move towards the opposition goal and shoot
            //add this part to another class

            //tackle

            MatchTimer.IT = InjuryTime;
        };


        gameTimer = new Timer(delay,taskPerformer);
        gameTimer.start();  //starting the timer

    }

    public void createRegions(){
        int index = 0;
        double width = this.WIDTH/NUMBER_OF_HORIZONTAL;
        double height = this.HEIGHT/NUMBER_OF_VERTICAL;
        for(int i = 0; i < NUMBER_OF_VERTICAL; i++){
            for (int j = 0 ; j < NUMBER_OF_HORIZONTAL; j++){
                m_region[i][j] = new Region(this.left+j*width,
                                            this.left+(j+1)*width,
                                            this.top+i*height,
                                            this.top+(i+1)*height,
                                            i*NUMBER_OF_VERTICAL+j);
            }
        }


    }

    public void onCollision(Player player) throws UnsupportedAudioFileException, LineUnavailableException, IOException {


        if (chance.IsFoul()){
            System.out.println("foul");
            //free kick
            new MusicPlayer("src/main/resources/Music/whistle.wav");
            new MusicPlayer("src/main/resources/Music/booing.wav");

            /*ball.stop();*/
            goaldelay = 0;
            InjuryTime = InjuryTime + 1;
        }
        else if(chance.IsRed()){
            System.out.println("test");
            player.setM_red(player.getM_red() + 1);
            //sending off
            //free kick
            red = true;
            audio.StartMusic("src/main/resources/Music/whistle.wav");
            audio2.StartMusic("src/main/resources/Music/booing.wav");

            /*ball.stop();*/
            goaldelay = 0;
            InjuryTime = InjuryTime + 1;
            /*behaviourSentOff(player);*/
        }
        else if (chance.IsYellow()){
            System.out.println("test");
            player.setM_yellow(player.getM_yellow() + 1);
            if(player.getM_yellow() > 1){
                if(player.getM_red() > 0){
                    return;
                }
            //sending off
            //free kick
                yellow = true;
                audio.StartMusic("src/main/resources/Music/whistle.wav");
                audio2.StartMusic("src/main/resources/Music/booing.wav");
                /*ball.stop();*/
        }
            goaldelay = 0;
            InjuryTime = InjuryTime + 1;
        }

/*        if(chance.IsInjured()){
            behaviourSentOff(player);
            player.injured = true;
            injury = true;

            goaldelay = 0;
            InjuryTime = InjuryTime + 1;
        }*/

    }

/*    public void behaviour(Player player1,Player player2){
        //behaviour decisions

        if(player1.getM_red() > 0 && !player1.moved)
        {
            behaviourSentOff(player1);
            behaviourChase(player2);
        }

        if(player2.getM_red() > 0 && !player2.moved)
        {
            behaviourSentOff(player2);
            behaviourChase(player1);
        }

        if(player1.getM_red() > 0 && player2.getM_red() == 0){
           behaviourAttack(player2);
        }
        if(player2.getM_red() > 0 && player1.getM_red() == 0){
            behaviourAttack(player1);
        }
        else if (player1.getM_is_ball() && !player2.getM_is_ball() && player2.getM_red() == 0 && player1.getM_red() == 0){
            behaviourAttack(player1);
            behaviourDefence(player2);
        }
        else if(!player1.getM_is_ball() && player2.getM_is_ball() && player2.getM_red() == 0 && player1.getM_red() == 0){
            behaviourDefence(player1);
            behaviourAttack(player2);
        }
        else if(!player1.getM_is_ball() && !player2.getM_is_ball() && player2.getM_red() == 0 && player1.getM_red() == 0){
            behaviourChase(player1);
           behaviourChase(player2);
        }
    }*/

    public void behaviourSentOff(Player player) {
        if(!player.moved){
            player.stop();
            player.setPosition(1120,sendoffloc);
            sendoffloc = sendoffloc + 30;
            player.moved = true;
            player.changeM_is_ball();
        }
        return;

    }

/*    public void GoalChange() {
        if(player1.getM_red() == 0 && !player1.injured)
        player1.setPosition(((left+right)/2 - 50),(top+bot)/2);

        if(player2.getM_red() == 0 && !player2.injured)
        player2.setPosition(((left+right)/2 + 60),(top+bot)/2);

        oldgoal1 = goalteam1;
        oldgoal2 = goalteam2;
        goaldelay = 0;

        return;
    }*/

   public void PlayerReturn(Player player) {
            player.moved = false;
            player.injured = false;
        }


/*    public void behaviourAttack(Player player){     //attacking behaviour functions
        if (player.getM_team()== 0){
            if (player.getPosition().distance(goal_right_center)<=200){
                *//*player.turnWithBall(ball,goal_right_center);*//*
                player.kick(ball,200);
                player.stop();
            }
            else{
                player.turnWithBall(ball,goal_right_center);
                player.move(ball,goal_right_center);
            }

        }
        else{
            if (player.getPosition().distance(goal_left_center)<=200){
                //should add more judgement
                *//*player.turnWithBall(ball,goal_left_center);*//*
                player.kick(ball,200);
                player.stop();
            }
            else{
                player.turnWithBall(ball,goal_left_center);
                player.move(ball,goal_left_center);

            }
        }
    }

    public void behaviourDefence(Player player){   //defending behaviour functions
        if (player.getM_team()== 0){
            if (player.getPosition().distance(goal_left_center)<=200){
                player.stop();
            }
            else{
                player.turnToPoint(goal_left_center);
                player.move(goal_left_center);
            }

        }
        else{
            if (player.getPosition().distance(goal_right_center)<=200){
                player.stop();
            }
            else{
                player.turnToPoint(goal_right_center);
                player.move(goal_right_center);

            }
        }
    }

    public void behaviourChase(Player player){   //function to chase the ball
        player.turnToBall(ball);
        player.move(ball);
    }*/


    //move to simulator
    private boolean isOutBound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {        //function that checks if the ball is out of bounds
        if (ball.getPositionY() > goal_top && ball.getPositionY() < goal_bot && ball.getPositionX() < left) {//for left border
                ball.setPosition((left + right) / 2, (top + bot) / 2);
                ball.stop();
                audio.StartMusic("src/main/resources/Music/goalcheer.wav");
            new MusicPlayer("src/main/resources/Music/goalcheer.wav");
                goalteam1 = goalteam1 + 1;
                goal_team = 1;
                team_0.reset(1);
                team_1.reset(2);
                team_0.setNoBall();
                team_1.setNoBall();
                team_0.setM_possession(true);
                team_1.setM_possession(false);
                team_0.getM_player()[1].setM_is_ball(true);
                team_0.GOAL = 1;
                team_1.GOAL = 1;
                System.out.println("left goal");
                return true;
        }


        else if (ball.getPositionY() > goal_top && ball.getPositionY() < goal_bot && ball.getPositionX() > right){//for right border
                ball.setPosition((left + right) / 2, (top + bot) / 2);
                ball.stop();
                goal_team = 2;
                team_0.setNoBall();
                team_1.setNoBall();
                team_0.setM_possession(false);
                team_1.setM_possession(true);
                team_1.getM_player()[1].setM_is_ball(true);
                team_0.GOAL = 1;
                team_1.GOAL = 1;
            new MusicPlayer("src/main/resources/Music/goalcheer.wav");
                audio.StartMusic("src/main/resources/Music/goalcheer.wav");
                goalteam2 = goalteam2 + 1;
                return true;
        }

        else if (ball.getPositionX() > right || ball.getPositionX() < left){
            ball.move(-ball.getSpeedX() , ball.getSpeedY());
            return true;
        }
        else if (ball.getPositionY() > bot || ball.getPositionY() < top){
            ball.move(ball.getSpeedX() , -ball.getSpeedY());
            return true;
        }
        return false;
    }




/*    public void createTeams(){

        int check = 0; //to iterate while creating players

        while(check < teamsize){ //run the correct amount of times for the player array

            team1[check] = new Player(new Point2D.Double(left*(check+1), 30), 18, 30, TeamSelectFrame.CounterLeft, 1, team_1, ball);
            team2[check] = new Player(new Point2D.Double(left*(check+1), 500), 18, 30, TeamSelectFrame.CounterRight, 0, team_0, ball); //creates players in their start positions

            check++; //ready for next iteration
        }
    }*/

    @Override
    public void paint(Graphics g) {     //what draws everything to the screen
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(PitchImage, 0, 0, this);
        //temp.setVisible(false);  //gets rid of the instance of settings frame used to access the correct number of players to start the match with
        //temp.dispose();

        if(red){
            g2d.setColor(Color.RED);
            g2d.fillRect(1000,50,50,100);
            g2d.setColor(Color.BLACK);
            g.drawRect(1000, 50, 50, 100);
        }
        else if (yellow){
            g2d.setColor(Color.YELLOW);
            g2d.fillRect(1060,50,50,100);
            g2d.setColor(Color.BLACK);
            g.drawRect(1060, 50, 50, 100);
        }

        if(injury){

        }


        /*try{
            createTeams();  //actually creates the teams

            for(int i = 0; i < teamsize; i++){

               team1[i].draw(g2d);  //makes each player that has been created visible
              // team2[i].draw(g2d);
            }


        }*/
/*        catch (Exception e){
            System.out.println(e);
        }*/
        for (int i = 0; i < team_1.TEAM_SIZE; i++){
            team_0.m_player[i].draw(g2d);
            team_1.m_player[i].draw(g2d);
        }

        ball.draw(g2d);
        /*player1.draw(g2d);
        player2.draw(g2d); //prototype players*/

    }

}

//just test, will move to game board soon -Chen


    

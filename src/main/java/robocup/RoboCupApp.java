package robocup;

import javax.swing.*;

public class RoboCupApp
{
    static SplashScreen startFrame = new SplashScreen();

    public RoboCupApp() {

    }

    static JFrame GetSplashScreen(){
        return startFrame;
    }

    public static void main(String[] args)
    {
        new RoboCupApp();
    }

}


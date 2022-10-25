package robocup;

import java.util.Random;

public class RND {

    private int chance;
    private int FOULCHANCE = 2;
    private int YELLOWCHANCE = 2;
    private int REDCHANCE = 1;
    private int INJCHANCE = 0;


    public int MakeRandom(int lower, int upper) {
        Random random = new Random();
        return random.nextInt((upper - lower) + 1) + lower;
    }

    public boolean IsFoul(){
        chance = MakeRandom(0, 100);
        return chance < FOULCHANCE;
    }

    public boolean IsYellow(){
        chance = MakeRandom(0, 100);
        return chance < YELLOWCHANCE;
    }

    public boolean IsRed(){
        chance = MakeRandom(0, 100);
        return chance < REDCHANCE;
    }

    public boolean IsInjured(){
        chance = MakeRandom(0, 100);
        return chance < INJCHANCE;
    }

}

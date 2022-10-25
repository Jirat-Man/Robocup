package robocup;

import java.io.*;

public class SettingsIO {

    public SettingsIO() throws IOException {

        FileReader fr = new FileReader("settings.txt");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("settings.txt");

    }
}

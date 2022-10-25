package robocup;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * Play background music
 * @author Jirat Manpadungkit
 */
public class MusicPlayer {
    private Clip clip;
    private Boolean playing = true;
    private double volume;
    public static int musicCounter = 0;

    /**
     * MusicPlayer Constructor
     * Plays a .mp3/.wav on a 10000 times loop
     * <p>if no music is found,"No music found" is printed</p>
     *
     * @param musicLocation String
     */
    public MusicPlayer (String musicLocation) {
        setVolume();
        try {
            File music = new File(musicLocation);
            if (music.exists()) {
                AudioInputStream audio =
                        AudioSystem.getAudioInputStream(music);
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.loop(10000);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(20f * (float) Math.log10(getVolume()));   //minimum vol 0.0, maximum vol 1.0
                clip.start();
                musicCounter++;
            } else {
                System.out.println("No music found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double getVolume ( ) {
        return this.volume;
    }

    private void setVolume ( ) {
        this.volume = 0.3;
    }

    public void stop ( ) {
        if (playing) {
            clip.stop();
            playing = false;
        } else {
            clip.start();
            playing = true;
        }

    }
}


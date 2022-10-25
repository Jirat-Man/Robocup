package robocup;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * this class is responsible for playing audio files
 * Music automatically starts when the game is started and can be turned on and off from a button on the start screen,
 * which ever option is chosen once the game starts it continues throughout the game.
 * Sound effects for when bricks get hit and also when the ball is lost.
 * @author Cameron Gray
 *
 */
public class AudioHandler {

    /**
     * this method is what starts audio files and plays them
     * @param musicFile this variable is the String which determines what file to play
     */
    public void StartMusic(String musicFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream;
        Clip clip;

        audioInputStream = AudioSystem.getAudioInputStream(new File(musicFile).getAbsoluteFile());

        clip = AudioSystem.getClip();

        clip.open(audioInputStream);

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(0.0));  // min volume = 0.0, max volume = 1.0

        clip.start();

    }
}

package Model;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class SoundManager {
    private Clip clip;

    public SoundManager(String path) {
        inits(path);
    }

    private void inits(String path) {
        try {
            clip = AudioSystem.getClip();
            clip.open(
                    AudioSystem.getAudioInputStream(
                            getClass().getResource(path)
                    )
            );
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop(int count) {
        clip.loop(count);
    }
    public void stop(){
        clip.stop();
    }
}

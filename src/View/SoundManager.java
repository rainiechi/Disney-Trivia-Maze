package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundManager {
    private Clip myClip;
    private URL[] mySoundURL;

    public SoundManager() {
        mySoundURL = new URL[3];
        mySoundURL[0] = getClass().getResource("res/Sound/The Little Mermaid Under the Sea 8bit 16bit Chiptune.wav");
    }

    public void setFile(int theFile) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(mySoundURL[theFile]);
            myClip = AudioSystem.getClip();
            myClip.open(ais);
        } catch(Exception e) {

        }
    }
    public void play() {
        myClip.start();
    }
    public void loop() {
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        myClip.stop();
    }
}

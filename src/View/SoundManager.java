package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    private Clip myClip;
    private File[] mySoundURL;

    public SoundManager() {
        mySoundURL = new File[3];
        mySoundURL[0] = new File ("C:\\Users\\amand\\IdeaProjects\\Disney-Trivia-Maze\\src\\res\\Sound\\The Little Mermaid Under the Sea.wav");
    }

    public void setFile(int theFile) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(mySoundURL[theFile]);
            myClip = AudioSystem.getClip();
            myClip.open(ais);
        } catch(Exception e) {
            System.out.println("Not found");
            e.printStackTrace();
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

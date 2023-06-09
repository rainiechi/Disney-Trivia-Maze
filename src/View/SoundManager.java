package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class SoundManager {
    /**
     * The sound clip to be played.
     */
    private Clip myClip;
    /**
     * The clip path.
     */
    private final String[] mySoundURL;

    /**
     * Constructs a SoundManager object.
     * Initializes the sound URL array and sets the file path for the sound.
     */
    public SoundManager() {
        mySoundURL = new String[1];
        mySoundURL[0] = "/res/Sound/The Little Mermaid Under the Sea.wav";
    }

    /**
     * Sets the file to be played based on the specified index in the sound URL array.
     *
     * @param theFile The index of the file in the sound URL array.
     */
    public void setFile(final int theFile) {
        try {
            InputStream is = getClass().getResourceAsStream(mySoundURL[theFile]);
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            myClip = AudioSystem.getClip();
            myClip.open(ais);
        } catch (Exception e) {
            System.out.println("Not found");
            e.printStackTrace();
        }
    }

    /**
     * Starts playing the audio clip.
     */
    public void play() {
        myClip.start();
    }

    /**
     * Starts playing the audio clip in a loop.
     */
    public void loop() {
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the audio clip.
     */
    public void stop() {
        myClip.stop();
    }
}

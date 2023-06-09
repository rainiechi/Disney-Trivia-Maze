package View;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

/**
 * KeyHandler contains listeners for keyboard.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class KeyHandler implements KeyListener, Serializable {
    /** Booleans for up, down, left, and right keys */
    private boolean myUpPressed, myDownPressed, myRightPressed, myLeftPressed;
    /** Mini map object */
    private MiniMap myMiniMap;

    /**
     * Constructor for KeyHandler
     * @param theMiniMap mini map object
     */

    public KeyHandler(final MiniMap theMiniMap) {
        this.myMiniMap = theMiniMap;
    }

    /**
     * Method is not needed.
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // not needed
    }

    /**
     * If key is pressed, set boolean fields to true.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            setMyUpPressed(true);
        }
        if (code == KeyEvent.VK_S) {
            setMyDownPressed(true);
        }
        if (code == KeyEvent.VK_A) {
            setMyLeftPressed(true);
        }
        if (code == KeyEvent.VK_D) {
            setMyRightPressed(true);
        }
        if (code == KeyEvent.VK_M) {
            if (myMiniMap.isMapEnabled()) {
                myMiniMap.setMapEnabled(false);
            } else {
                myMiniMap.setMapEnabled(true);
            }
        }
        }

    /**
     * If key is release, set boolean fields to false.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            setMyUpPressed(false);
        }
        if (code == KeyEvent.VK_S) {
            setMyDownPressed(false);
        }
        if (code == KeyEvent.VK_A) {
            setMyLeftPressed(false);
        }
        if (code == KeyEvent.VK_D) {
            setMyRightPressed(false);
        }
    }

    /**
     * Release all keys.
     */
    public void setAllKeys() {
        myUpPressed = false;
        myDownPressed = false;
        myRightPressed = false;
        myLeftPressed = false;
    }

    /**
     * Getter method for up key.
     * @return true if pressed, false otherwise.
     */
    public boolean isMyUpPressed() {
        return myUpPressed;
    }

    /**
     * Setter method for up key.
     * @param myUpPressed boolean passed in.
     */
    public void setMyUpPressed(boolean myUpPressed) {
        this.myUpPressed = myUpPressed;
    }
    /**
     * Getter method for down key.
     * @return true if pressed, false otherwise.
     */
    public boolean isMyDownPressed() {
        return myDownPressed;
    }
    /**
     * Setter method for down key.
     * @param myDownPressed boolean passed in.
     */
    public void setMyDownPressed(boolean myDownPressed) {
        this.myDownPressed = myDownPressed;
    }
    /**
     * Getter method for right key.
     * @return true if pressed, false otherwise.
     */
    public boolean isMyRightPressed() {
        return myRightPressed;
    }
    /**
     * Setter method for right key.
     * @param myRightPressed boolean passed in.
     */
    public void setMyRightPressed(boolean myRightPressed) {
        this.myRightPressed = myRightPressed;
    }
    /**
     * Getter method for left key.
     * @return true if pressed, false otherwise.
     */
    public boolean isMyLeftPressed() {
        return myLeftPressed;
    }
    /**
     * Setter method for left key.
     * @param myLeftPressed boolean passed in.
     */
    public void setMyLeftPressed(boolean myLeftPressed) {
        this.myLeftPressed = myLeftPressed;
    }

}
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
    private boolean upPressed, downPressed, rightPressed, leftPressed;
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
            setUpPressed(true);
        }
        if (code == KeyEvent.VK_S) {
            setDownPressed(true);
        }
        if (code == KeyEvent.VK_A) {
            setLeftPressed(true);
        }
        if (code == KeyEvent.VK_D) {
            setRightPressed(true);
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
            setUpPressed(false);
        }
        if (code == KeyEvent.VK_S) {
            setDownPressed(false);
        }
        if (code == KeyEvent.VK_A) {
            setLeftPressed(false);
        }
        if (code == KeyEvent.VK_D) {
            setRightPressed(false);
        }
    }

    /**
     * Release all keys.
     */
    public void setAllKeys() {
        upPressed = false;
        downPressed = false;
        rightPressed = false;
        leftPressed = false;
    }

    /**
     * Getter method for up key.
     * @return true if pressed, false otherwise.
     */
    public boolean isUpPressed() {
        return upPressed;
    }

    /**
     * Setter method for up key.
     * @param upPressed boolean passed in.
     */
    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }
    /**
     * Getter method for down key.
     * @return true if pressed, false otherwise.
     */
    public boolean isDownPressed() {
        return downPressed;
    }
    /**
     * Setter method for down key.
     * @param downPressed boolean passed in.
     */
    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }
    /**
     * Getter method for right key.
     * @return true if pressed, false otherwise.
     */
    public boolean isRightPressed() {
        return rightPressed;
    }
    /**
     * Setter method for right key.
     * @param rightPressed boolean passed in.
     */
    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }
    /**
     * Getter method for left key.
     * @return true if pressed, false otherwise.
     */
    public boolean isLeftPressed() {
        return leftPressed;
    }
    /**
     * Setter method for left key.
     * @param leftPressed boolean passed in.
     */
    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

}
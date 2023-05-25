package View;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean upPressed, downPressed, rightPressed, leftPressed, myMapPressed, myExitMapPressed;
    private MiniMap myMiniMap;

    public KeyHandler(MiniMap theMiniMap) {
        this.myMiniMap = theMiniMap;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // not needed
    }

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
    public void setAllKeys() {
        upPressed = false;
        downPressed = false;
        rightPressed = false;
        leftPressed = false;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isMapPressed() {
        return myMapPressed;
    }
    public void setMapPressed(boolean mapPressed) {
        this.myMapPressed = mapPressed;
    }

    public boolean isMyExitMapPressed() {
        return myExitMapPressed;
    }

    public void setMyExitMapPressed(boolean myExitMapPressed) {
        this.myExitMapPressed = myExitMapPressed;
    }
}
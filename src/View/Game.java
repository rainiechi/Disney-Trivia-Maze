package View;

import Model.Backpack;
import Model.Player;
import Model.QuestionRecord;
import Model.StoneManager;
import View.*;

import java.io.Serializable;

/**
 * Game class contains instances that are necessary for the game
 * to function properly.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class Game implements Serializable {
    /** Max number of objects */
    private static final int MAX_OBJ = 70;
    /** Player object */
    private Player myPlayer;
    /** PlayerManager object */
    private PlayerManager myPlayerManager;
    /** QuestionRecord object */
    private QuestionRecord myQuestionRecord;
    /** StoneManager object */
    private StoneManager myStoneManager;
    /** Array of ObjectManager */
    private ObjectManager[] myObjManagers;
    /** KeyHandler object */
    private KeyHandler myKeyHandler;
    /** Mini map object */
    private MiniMap myMiniMap;
    /** CollisionChecker object */
    private transient CollisionChecker myCollisionChecker;

    /**
     * Constructor initializes the fields.
     * @param theGP the GamePanel
     */
    public Game(final GamePanel theGP) {
        myQuestionRecord = new QuestionRecord();
        myStoneManager = new StoneManager();
        myObjManagers = new ObjectManager[MAX_OBJ];
        myMiniMap = new MiniMap(theGP);
        myKeyHandler = new KeyHandler(myMiniMap);
        myPlayer = new Player();
        myPlayerManager = new PlayerManager(theGP, myKeyHandler, myPlayer);
        myCollisionChecker = new CollisionChecker(theGP, myQuestionRecord);
    }

    /**
     * Setter method for CollisionChecker
     * @param theGamePanel game panel
     */
    public void setMyCollisionChecker(GamePanel theGamePanel) {
        myCollisionChecker = new CollisionChecker(theGamePanel, myQuestionRecord);
    }

    /**
     * Getter method for CollisionChecker
     * @return
     */
    public CollisionChecker getMyCollisionChecker() {
        return myCollisionChecker;
    }

    /**
     * Getter method for time limit.
     * @return player's time limit per trivia.
     */
    public int getTime() {
        return myPlayer.getTimeLimit();
    }

    /**
     * Getter method for StoneManager.
     * @return StoneManager object
     */
    public StoneManager getMyStoneManager() {
        return myStoneManager;
    }

    /**
     * Getter method for array of ObjectManager
     * @return the array of ObjectManager
     */
    public ObjectManager[] getMyObjManagers() {
        return myObjManagers;
    }

    /**
     * Getter method for KeyHandler
     * @return myKeyHandler object
     */
    public KeyHandler getMyKeyHandler() {
        return myKeyHandler;
    }

    /**
     * Getter method for Mini map.
     * @return the mini map
     */
    public MiniMap getMyMiniMap() {
        return myMiniMap;
    }

    /**
     * Getter method for player.
     * @return the player
     */
    public Player getMyPlayer() {
        return myPlayer;
    }

    /**
     * Getter method for PlayerManager
     * @return PlayerManager object
     */
    public PlayerManager getMyPlayerManager() {
        return myPlayerManager;
    }

    /**
     * Getter method for object at index of array of ObjectManager.
     * @param theIndex index in array
     * @return the object at index
     */
    public ObjectManager getObjManager(final int theIndex) {
        return myObjManagers[theIndex];
    }

    /**
     * Deletes the object at index.
     * @param theIndex index in array
     */
    public void deleteObjManager(final int theIndex) {
        myObjManagers[theIndex] = null;
    }

}
package View;

import Model.Backpack;
import Model.Player;
import Model.QuestionRecord;
import Model.StoneManager;
import View.*;

import java.io.Serializable;

public class Game implements Serializable {
    //private static final long serialVersionUID = 20001222L;
    private static final int MAX_OBJ = 70;
    private Player myPlayer;
    private PlayerManager myPlayerManager;
    private QuestionRecord myQuestionRecord;
    private StoneManager myStoneManager;
    private ObjectManager[] myObjManagers;
    private KeyHandler myKeyHandler;
    private MiniMap myMiniMap;
    private transient CollisionChecker myCollisionChecker;

    /**
     * Game object contains all necessary logic for the game.
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

    public void setMyCollisionChecker(GamePanel theGamePanel) {
        myCollisionChecker = new CollisionChecker(theGamePanel, myQuestionRecord);
    }

    public CollisionChecker getMyCollisionChecker() {
        return myCollisionChecker;
    }

    public int getTime() {
        return myPlayer.getTimeLimit();
    }

    public StoneManager getMyStoneManager() {
        return myStoneManager;
    }

    public ObjectManager[] getMyObjManagers() {
        return myObjManagers;
    }

    public KeyHandler getMyKeyHandler() {
        return myKeyHandler;
    }

    public MiniMap getMyMiniMap() {
        return myMiniMap;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public PlayerManager getMyPlayerManager() {
        return myPlayerManager;
    }

    public ObjectManager getObjManager(final int theIndex) {
        return myObjManagers[theIndex];
    }

    public void deleteObjManager(final int theIndex) {
        myObjManagers[theIndex] = null;
    }

}
package Model;

import View.*;

import java.io.Serializable;

public class Game implements Serializable {
    private static final long serialVersionUID = 20001222L;
    private static final int MAX_OBJ = 60;
    private Player myPlayer;
    private PlayerManager myPlayerManager;
    private QuestionRecord myQuestionRecord;
    private StoneManager myStoneManager;
    private ObjectManager[] myObjManagers;
    private KeyHandler myKeyHandler;
    private MiniMap myMiniMap;

    public Game(final GamePanel theGP) {
        myQuestionRecord = new QuestionRecord();
        myStoneManager = new StoneManager();
        myObjManagers = new ObjectManager[MAX_OBJ];
        myMiniMap = new MiniMap(theGP);
        myKeyHandler = new KeyHandler(myMiniMap);
        myPlayer = new Player();
        myPlayerManager = new PlayerManager(theGP, myKeyHandler, myPlayer);
    }

    public QuestionRecord getMyQuestionRecord() {
        return myQuestionRecord;
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

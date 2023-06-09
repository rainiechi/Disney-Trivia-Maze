package View;

import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * CollisionChecker contains methods to ensure Player does not
 * walk through certian tiles. It will also check if Player is touching
 * an object such as chest, door, or exit.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class CollisionChecker {
    /** Tile size in game */
    private static final int TILE_SIZE = GameSettings.TILE_SIZE;
    /** Maze object */
    private final static Maze MAZE = new Maze();
    /** Game panel object */
    private GamePanel myGp;
    /** QuestionRecord object */
    private QuestionRecord myQuestionRecord;
    /** Pop up for trivia dialog */
    private PopUp myPopUp;
    /** Counter for a cheat */
    private int myCheatCount;
    /** Chest pop up for chest */
    private ChestPopUp myChestPopUp;

    /**
     * Constructor initializes the fields.
     * @param theGp Game panel
     * @param theQuestionRecord question record
     */
    public CollisionChecker(final GamePanel theGp, final QuestionRecord theQuestionRecord) {
        myGp = theGp;
        myQuestionRecord = theQuestionRecord;
        myCheatCount = 0;
    }

    /**
     * Method checkTile checks if player's solid area is touching the tile's solid area.
     * If player is touching a tile that has collision, player's collision will be set to true
     * and player will not be able to walk through.
     * @param thePlayer player
     */
    public void checkTile(final PlayerManager thePlayer) {
        int entityLeftWorldX = thePlayer.getMyWorldX() + thePlayer.getSolidArea().x;
        int entityRightWorldX = thePlayer.getMyWorldX() + thePlayer.getSolidArea().x + thePlayer.getSolidArea().width;
        int entityTopWorldY = thePlayer.getMyWorldY() + thePlayer.getSolidArea().y;
        int entityBottomWorldY = thePlayer.getMyWorldY() + thePlayer.getSolidArea().y + thePlayer.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX/TILE_SIZE;
        int entityRightCol = entityRightWorldX/TILE_SIZE;
        int entityTopRow = entityTopWorldY/TILE_SIZE;
        int entityBottomRow = entityBottomWorldY/TILE_SIZE;

        int tileNum1;
        int tileNum2;

        switch(thePlayer.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - thePlayer.getSpeed())/TILE_SIZE;
                tileNum1 = MAZE.getMyMapTileNum(entityLeftCol, entityTopRow);
                tileNum2 = MAZE.getMyMapTileNum(entityRightCol, entityTopRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (MAZE.getTile(tileNum1).isCollision()|| MAZE.getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + thePlayer.getSpeed())/TILE_SIZE;
                tileNum1 = MAZE.getMyMapTileNum(entityLeftCol, entityBottomRow);
                tileNum2 = MAZE.getMyMapTileNum(entityRightCol, entityBottomRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (MAZE.getTile(tileNum1).isCollision() || MAZE.getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - thePlayer.getSpeed())/TILE_SIZE;
                tileNum1 = MAZE.getMyMapTileNum(entityLeftCol, entityTopRow);
                tileNum2 = MAZE.getMyMapTileNum(entityLeftCol, entityBottomRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (MAZE.getTile(tileNum1).isCollision() || MAZE.getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + thePlayer.getSpeed())/TILE_SIZE;
                tileNum1 = MAZE.getMyMapTileNum(entityRightCol, entityTopRow);
                tileNum2 = MAZE.getMyMapTileNum(entityRightCol, entityBottomRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (MAZE.getTile(tileNum1).isCollision() ||MAZE.getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
        }
    }

    /**
     * Method checkObject checks if player is touching an object such as door, chest, or exit.
     * @param thePlayer player
     * @param theCollision boolean collision
     * @return index of array of ObjectManager
     */
    public int checkObject(final PlayerManager thePlayer, final boolean theCollision) {
        int index = 999;

        for(int i = 0; i < myGp.getObjManagers().length; i++) {
            if (myGp.getObjManager(i) != null) {
                // Get entity's solid area position
                thePlayer.getSolidArea().x = thePlayer.getMyWorldX() + thePlayer.getSolidArea().x;
                thePlayer.getSolidArea().y = thePlayer.getMyWorldY() + thePlayer.getSolidArea().y;

                // Get the object's solid position
                myGp.getObjManager(i).getSolidArea().x = myGp.getObjManager(i).getWorldX() + myGp.getObjManager(i).getSolidArea().x;
                myGp.getObjManager(i).getSolidArea().y = myGp.getObjManager(i).getWorldY() + myGp.getObjManager(i).getSolidArea().y;

                switch(thePlayer.getDirection()) {
                    case "up":
                        thePlayer.getSolidArea().y -= thePlayer.getSpeed();
                        if (thePlayer.getSolidArea().intersects(myGp.getObjManager(i).getSolidArea())) {
                            // If one of the object boolean is true and player is hitting it, collision is true
                            if (myGp.getObjManager(i).isCollision()) {
                                thePlayer.setCollision(true);
                            }
                            if (theCollision == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        thePlayer.getSolidArea().y += thePlayer.getSpeed();
                        if (thePlayer.getSolidArea().intersects(myGp.getObjManager(i).getSolidArea())) {
                            // If one of the object boolean is true and player is hitting it, collision is true
                            if (myGp.getObjManager(i).isCollision()) {
                                thePlayer.setCollision(true);
                            }
                            if (theCollision == true) {
                                index = i;
                            }
                        }

                        break;
                    case "left":
                        thePlayer.getSolidArea().x -= thePlayer.getSpeed();
                        if (thePlayer.getSolidArea().intersects(myGp.getObjManager(i).getSolidArea())) {
                            // If one of the object boolean is true and player is hitting it, collision is true
                            if (myGp.getObjManager(i).isCollision()) {
                                thePlayer.setCollision(true);
                            }
                            if (theCollision == true) {
                                index = i;
                            }
                        }

                        break;
                    case "right":
                        thePlayer.getSolidArea().x += thePlayer.getSpeed();
                        if (thePlayer.getSolidArea().intersects(myGp.getObjManager(i).getSolidArea())) {
                            // If one of the object boolean is true and player is hitting it, collision is true
                            if (myGp.getObjManager(i).isCollision()) {
                                thePlayer.setCollision(true);
                            }
                            if (theCollision == true) {
                                index = i;
                            }
                        }

                        break;
                }
                thePlayer.setSolidAreaX(thePlayer.getMySolidAreaDefaultX());
                thePlayer.setSolidAreaY(thePlayer.getMySolidAreaDefaultY());
                myGp.getObjManager(i).setSolidAreaX(myGp.getObjManager(i).getMySolidAreaDefaultX());
                myGp.getObjManager(i).setSolidAreaY(myGp.getObjManager(i).getMySolidAreaDefaultY());
            }
        }

        return index;
    }

    /**
     * Method pickUpObject reads the index of the object passed in from the parameters
     * and checks if the index is a door, chest, or exit. It will call methods based
     * on the object.
     *
     * @param theIndex index of object in array of ObjectManager
     * @param theKeyH key handler
     * @param thePlayer player
     */
    public void pickUpObject(final int theIndex, final KeyHandler theKeyH, final PlayerManager thePlayer) {
        // Any number is fine as long as its not the index
        // of an object.
        if (theIndex != 999) {
            String objectName = myGp.getObjManager(theIndex).getName();
            switch(objectName) {
                case "Door", "SideDoor":
                    doorMethod(theIndex, thePlayer);
                    break;
                case "Chest":
                    chestMethods(theIndex, thePlayer.getPlayer());
                    break;
                case "Exit":
                    GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(myGp);
                    frame.switchToEndPanel();
                    myGp.deleteSavedGame();
                    break;
            }
            theKeyH.setAllKeys();
        }
    }

    /**
     * Door method creates a new door instance if player has never touched the object
     * before. Using the new door instance, it will call the pop up dialog
     * to display an unused trivia question. If player answers correctly, the door object will
     * be deleted. Otherwise, the object is locked.
     *
     * @param theIndex index of array of ObjectManager
     * @param thePlayer player
     */
    private void doorMethod(final int theIndex, final PlayerManager thePlayer) {
        // If player has not touched/collide with object it will create
        // a new door instance.
        if (!myGp.getObjManager(theIndex).isTouched()) {
            Door door = new Door(myQuestionRecord);
            myGp.getObjManager(theIndex).setTouched(true);
            // sets Door to the Object
            myGp.getObjManager(theIndex).setDoor(door);
        }
        // If the object colliding with player is not locked and player has a space stone,
        // player is given the option to skip the door because of the space stone's ability.
        if (!myGp.getObjManager(theIndex).isLocked() && thePlayer.getPlayer().isSpaceStone()) {
            DialogForYesNoAnswer d = new DialogForYesNoAnswer("Would you like to use the Space Stone to skip this door?", myGp);
            if (d.getMyUserAnswer()) {
                thePlayer.getPlayer().useStone(new SpaceStone());
                // if player uses stone, delete the door.
                myGp.deleteObjManager(theIndex);
                if (thePlayer.getDirection().equals("left")) {
                    myGp.deleteObjManager(theIndex - 1);
                } else if (thePlayer.getDirection().equals("right")) {
                    myGp.deleteObjManager(theIndex + 1);
                }
            }
        }
        // If the object is not null and object is not locked
        // Player is asked if they would like to attempt the door
        // If yes, trivia questions will pop up.
        if (myCheatCount > 2 && myGp.getGame().getMyMiniMap().isMapEnabled()) {
            myGp.deleteObjManager(theIndex);
            if (thePlayer.getDirection().equals("left")) {
                myGp.deleteObjManager(theIndex - 1);
            } else if (thePlayer.getDirection().equals("right")) {
                myGp.deleteObjManager(theIndex + 1);
            }
            myCheatCount = 0;
        }
         if (myGp.getObjManager(theIndex) != null && !myGp.getObjManager(theIndex).isLocked()) {
            DialogForYesNoAnswer d = new DialogForYesNoAnswer("Would you like to attempt this door?", myGp);

            if (d.getMyUserAnswer()) {
                myPopUp = new PopUp(myGp.getObjManager(theIndex).getDoor(), myGp);
                // In pop up, if player gets correct answer the door object is set to unlocked.
                // If statement checks if door is unlocked and deletes object if so.
                while(myPopUp.getMyDialog().isVisible()){
                    System.out.print("");
                }
                System.out.println(myGp.getObjManager(theIndex).getDoor().isUnlocked());
                if (myGp.getObjManager(theIndex).getDoor().isUnlocked()) {
                    myGp.deleteObjManager(theIndex);
                    if (thePlayer.getDirection().equals("left")) {
                        myGp.deleteObjManager(theIndex - 1);
                    } else if (thePlayer.getDirection().equals("right")) {
                        myGp.deleteObjManager(theIndex + 1);
                    }
                } else {
                    // Id door is not unlocked, the object is locked meaning no more attempts
                    // can be made on the door.
                    myGp.getObjManager(theIndex).setLocked(true);
                }
            } else {
                myCheatCount++;
            }
        } else if (myGp.getObjManager(theIndex) != null && thePlayer.getPlayer().isSoulStone() && myGp.getObjManager(theIndex).getDoor().isAttempted()) {
             // If player has a soul stone, they can use the ability to attempt the door again.
            DialogForYesNoAnswer d = new DialogForYesNoAnswer("Would you like to use the Soul Stone to attempt this door again?", myGp);
            if (d.getMyUserAnswer()) {
                thePlayer.getPlayer().useStone(new SoulStone());
                // Object is no longer locked
                myGp.getObjManager(theIndex).setLocked(false);
            }
        }
         // With each door object, it will call this method to check if the doors specifically leading to the
        // exit room is locked for game over.
        checkExitDoors(thePlayer);
    }

    /**
     * Creates a new chest instance if player has not collide or touch the object.
     * Once chest is created and object is not locked, it will call a dialog pop up
     * to ask player is they want to open.
     *
     * @param theIndex index of the array of ObjectManager
     * @param thePlayer player
     */
    private void chestMethods(final int theIndex, final Player thePlayer) {
        if (!myGp.getObjManager(theIndex).isTouched()) {
            Chest chest = new Chest(myGp.getGame().getMyStoneManager());
            myGp.getObjManager(theIndex).setTouched(true);
            myGp.getObjManager(theIndex).setChest(chest);
        } if (!myGp.getObjManager(theIndex).isLocked()) {
            myChestPopUp = new ChestPopUp(myGp.getObjManager(theIndex).getChest(), myGp, thePlayer);
            if (myGp.getObjManager(theIndex).getChest().isLocked()) {
                myGp.getObjManager(theIndex).setLocked(true);
                // Once locked, image of chest is updated to be an open chest rather than a closed chest
                try {
                    myGp.getObjManager(theIndex).setMyImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/chest_opened.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Checks if the doors specifically leading to the exit door have been locked.
     * If the doors to exit are locked, it is game over.
     * @param thePlayer player
     */
    private void checkExitDoors(final PlayerManager thePlayer) {
        // Specific coordinates of doors surrounding exit room
        if (myGp.getObjManager(8) != null && myGp.getObjManager(8).isLocked()
                && (myGp.getObjManager(11) != null && myGp.getObjManager(11).isLocked()
                || myGp.getObjManager(24) != null && myGp.getObjManager(24).isLocked())
                && !thePlayer.getPlayer().isSoulStone()) {
            myGp.setGameOver(true);
            GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(myGp);
            frame.switchToEndPanel();
            myGp.deleteSavedGame();
        }
    }

    /**
     * Getter method for trivia question pop up.
     * @return myPopUp pop up dialog
     */
    public PopUp getMyPopUp() {
        return myPopUp;
    }

}
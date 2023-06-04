package View;

import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class CollisionChecker {
    private static final int TILE_SIZE = GameSettings.TILE_SIZE;
    private final static Maze MAZE = new Maze();
    private GamePanel myGp;
    private QuestionRecord myQuestionRecord;
    private PopUp myPopUp;

    public CollisionChecker(final GamePanel theGp, final QuestionRecord theQuestionRecord) {
        myGp = theGp;
        myQuestionRecord = theQuestionRecord;
    }

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

    public int checkObject(final PlayerManager thePlayer, final boolean theCollision) {
        int index = 999;

        for(int i = 0;  i < myGp.getObj().length; i++) {
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
                    break;
            }
            theKeyH.setAllKeys();
        }
    }

    public void doorMethod(final int theIndex, final PlayerManager thePlayer) {
        if (!myGp.getObjManager(theIndex).isTouched()) {
            Door door = new Door(myQuestionRecord);
            myGp.getObjManager(theIndex).setTouched(true);
            myGp.getObjManager(theIndex).setDoor(door);
        }
        if (!myGp.getObjManager(theIndex).isLocked() && thePlayer.getPlayer().hasSpaceStone()) {
            DialogForYesNoAnswer d = new DialogForYesNoAnswer("Would you like to use the Space Stone to skip this door?", myGp);
            if (d.getMyUserAnswer()) {
                thePlayer.getPlayer().useStone(new SpaceStone());
                myGp.deleteObjManager(theIndex);
                if (thePlayer.getDirection().equals("left")) {
                    myGp.deleteObjManager(theIndex - 1);
                } else if (thePlayer.getDirection().equals("right")) {
                    myGp.deleteObjManager(theIndex + 1);
                }
            }
        }
         if (myGp.getObjManager(theIndex) != null && !myGp.getObjManager(theIndex).isLocked()) {
            DialogForYesNoAnswer d = new DialogForYesNoAnswer("Would you like to attempt this door?", myGp);
            if (d.getMyUserAnswer()) {
                myPopUp = new PopUp(myGp.getObjManager(theIndex).getDoor(), myGp);

                while(myPopUp.getMyDialog().isVisible()){
                    System.out.print("");
                }
                System.out.println(myQuestionRecord.getQuestionRecord()); //just for testing, making ssure Record is working
                //System.out.println("Door unlock in collsion "+myGp.getObjManager(theIndex).getDoor().getMyUnlock());

                if (myGp.getObjManager(theIndex).getDoor().getMyUnlock()) {
                    myGp.deleteObjManager(theIndex);
                    if (thePlayer.getDirection().equals("left")) {
                        myGp.deleteObjManager(theIndex - 1);
                    } else if (thePlayer.getDirection().equals("right")) {
                        myGp.deleteObjManager(theIndex + 1);
                    }
                } else {
                    myGp.getObjManager(theIndex).setLocked(true);
                }
            }
        } else if (myGp.getObjManager(theIndex) != null && thePlayer.getPlayer().hasSoulStone() && myGp.getObjManager(theIndex).getDoor().getAttempt()) {
            DialogForYesNoAnswer d = new DialogForYesNoAnswer("Would you like to use the Soul Stone to attempt this door again?", myGp);
            if (d.getMyUserAnswer()) {
                thePlayer.getPlayer().useStone(new SoulStone());
                myGp.getObjManager(theIndex).setLocked(false);
            }
        }
        checkExitDoors(thePlayer);
    }
    public void chestMethods(final int theIndex, final Player thePlayer) {
        if (!myGp.getObjManager(theIndex).isTouched()) {
            Chest chest = new Chest(myGp.getGame().getMyStoneManager());
            myGp.getObjManager(theIndex).setTouched(true);
            myGp.getObjManager(theIndex).setChest(chest);
        } if (!myGp.getObjManager(theIndex).isLocked()) {
            ChestPopUp pop = new ChestPopUp(myGp.getObjManager(theIndex).getChest(), myGp, thePlayer);
            System.out.println(myGp.getObjManager(theIndex).getChest().isLocked());
            if (myGp.getObjManager(theIndex).getChest().isLocked()) {
                myGp.getObjManager(theIndex).setLocked(true);
                try {
                    myGp.getObjManager(theIndex).setMyImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/chest_opened.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void checkExitDoors(final PlayerManager thePlayer) {
        if (myGp.getObjManager(8) != null && myGp.getObjManager(8).isLocked()
                && (myGp.getObjManager(11) != null && myGp.getObjManager(11).isLocked()
                || myGp.getObjManager(24) != null && myGp.getObjManager(24).isLocked())
                && !thePlayer.getPlayer().hasSoulStone()) {
            myGp.setGameOver(true);
            GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(myGp);
            frame.switchToEndPanel();
        }
    }
    public PopUp getMyPopUp() {
        return myPopUp;
    }

}
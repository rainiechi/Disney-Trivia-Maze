package View;

import Model.*;

import java.io.Serializable;

public class CollisionChecker implements Serializable {
    private static final int TILE_SIZE = GameSettings.TILE_SIZE;
    private final static Maze MAZE = new Maze();
    private GamePanel myGp;
    private QuestionRecord myQuestionRecord;

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

    public int checkObject(final PlayerManager thePlayer, final boolean theCheck) {
        int index = 999;

        for(int i = 0;  i < myGp.getObj().length; i++) {
            if (myGp.getObj()[i] != null) {
                // Get entity's solid area position
                thePlayer.getSolidArea().x = thePlayer.getMyWorldX() + thePlayer.getSolidArea().x;
                thePlayer.getSolidArea().y = thePlayer.getMyWorldY() + thePlayer.getSolidArea().y;

                // Get the object's solid position
                myGp.getObj()[i].getSolidArea().x = myGp.getObj()[i].getWorldX() + myGp.getObj()[i].getSolidArea().x;
                myGp.getObj()[i].getSolidArea().y = myGp.getObj()[i].getWorldY() + myGp.getObj()[i].getSolidArea().y;

                switch(thePlayer.getDirection()) {
                    case "up":
                        thePlayer.getSolidArea().y -= thePlayer.getSpeed();
                        if (thePlayer.getSolidArea().intersects(myGp.getObj()[i].getSolidArea())) {
                            if (myGp.getObj()[i].isCollision()) {
                                thePlayer.setCollision(true);
                            }
                            if (theCheck == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        thePlayer.getSolidArea().y += thePlayer.getSpeed();
                        if (thePlayer.getSolidArea().intersects(myGp.getObj()[i].getSolidArea())) {
                            if (myGp.getObj()[i].isCollision()) {
                                thePlayer.setCollision(true);
                            }
                            if (theCheck == true) {
                                index = i;
                            }
                        }

                        break;
                    case "left":
                        thePlayer.getSolidArea().x -= thePlayer.getSpeed();
                        if (thePlayer.getSolidArea().intersects(myGp.getObj()[i].getSolidArea())) {
                            if (myGp.getObj()[i].isCollision()) {
                                thePlayer.setCollision(true);
                            }
                            if (theCheck == true) {
                                index = i;
                            }
                        }

                        break;
                    case "right":
                        thePlayer.getSolidArea().x += thePlayer.getSpeed();
                        if (thePlayer.getSolidArea().intersects(myGp.getObj()[i].getSolidArea())) {
                            if (myGp.getObj()[i].isCollision()) {
                                thePlayer.setCollision(true);
                            }
                            if (theCheck == true) {
                                index = i;
                            }
                        }

                        break;
                }
                thePlayer.setSolidAreaX(thePlayer.getMySolidAreaDefaultX());
                thePlayer.setSolidAreaY(thePlayer.getMySolidAreaDefaultY());
                myGp.getObj()[i].setSolidAreaX(myGp.getObj()[i].getMySolidAreaDefaultX());
                myGp.getObj()[i].setSolidAreaY(myGp.getObj()[i].getMySolidAreaDefaultY());
            }
        }

        return index;
    }
    public void pickUpObject(final int theIndex, final KeyHandler theKeyH, final PlayerManager thePlayer, final Chest theChest) {
        // Any number is fine as long as its not the index
        // of an object.
        if (theIndex != 999) {
            String objectName = myGp.getObj()[theIndex].getName();
            switch(objectName) {
                case "Door", "SideDoor":
                    doorMethod(theIndex, thePlayer);
                    theKeyH.setAllKeys();
                    break;
                case "Chest":
                    chestMethods(theIndex, thePlayer.getPlayer());
                    break;
            }
        }
    }
    public void doorMethod(final int theIndex, final PlayerManager thePlayer) {
        if (!myGp.getObjManager(theIndex).isTouched()) {
            Door door = new Door(myQuestionRecord);
            myGp.getObjManager(theIndex).setTouched(true);
            myGp.getObjManager(theIndex).setDoor(door);
        }
        if (!myGp.getObjManager(theIndex).isLocked()) {
            PopUp pop = new PopUp(myGp.getObjManager(theIndex).getDoor(), myGp);
            System.out.println(myQuestionRecord.getQuestionRecord()); //just for testing, making ssure Record is working
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
        } else {
            // if player has soul stone, pop up will ask if they want to reattempt the door
            // if player does not have soul stone, pop up will say the door is locked.
        }
    }
    public void chestMethods(final int theIndex, final Player thePlayer) {
        if (!myGp.getObjManager(theIndex).isTouched()) {
            Chest chest = new Chest();
            myGp.getObjManager(theIndex).setTouched(true);
            myGp.getObjManager(theIndex).setChest(chest);
        } if (!myGp.getObjManager(theIndex).getChest().isEmptyChest() && !myGp.getObjManager(theIndex).isLocked()) {
            thePlayer.addToBackpack(myGp.getObjManager(theIndex).getChest().getMyStone());
            System.out.println(thePlayer.getCurrItem());
        }
        myGp.getObjManager(theIndex).setLocked(true);
    }

}

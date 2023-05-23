package View;

import Model.Door;
import Model.GameSettings;
import Model.Maze;
import Model.QuestionRecord;

public class CollisionChecker {
    private static final int TILE_SIZE = GameSettings.TILE_SIZE;
    private GamePanel myGp;
    private Maze myMaze;
    private QuestionRecord myQuestionRecord;

    public CollisionChecker(final GamePanel theGp, final Maze theMaze, final QuestionRecord theQuestionRecord) {
        myGp = theGp;
        myMaze = theMaze;
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
                tileNum1 = myMaze.getMyMapTileNum(entityLeftCol, entityTopRow);
                tileNum2 = myMaze.getMyMapTileNum(entityRightCol, entityTopRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (myMaze.getTile(tileNum1).isCollision()|| myMaze.getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + thePlayer.getSpeed())/TILE_SIZE;
                tileNum1 = myMaze.getMyMapTileNum(entityLeftCol, entityBottomRow);
                tileNum2 = myMaze.getMyMapTileNum(entityRightCol, entityBottomRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (myGp.getMaze().getTile(tileNum1).isCollision() || myGp.getMaze().getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - thePlayer.getSpeed())/TILE_SIZE;
                tileNum1 = myMaze.getMyMapTileNum(entityLeftCol, entityTopRow);
                tileNum2 = myMaze.getMyMapTileNum(entityLeftCol, entityBottomRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (myMaze.getTile(tileNum1).isCollision() || myMaze.getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + thePlayer.getSpeed())/TILE_SIZE;
                tileNum1 = myMaze.getMyMapTileNum(entityRightCol, entityTopRow);
                tileNum2 = myMaze.getMyMapTileNum(entityRightCol, entityBottomRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (myMaze.getTile(tileNum1).isCollision() ||myMaze.getTile(tileNum2).isCollision()) {
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
    public void pickUpObject(final int theIndex, final KeyHandler theKeyH) {
        // Any number is fine as long as its not the index
        // of an object.
        if (theIndex != 999) {
            String objectName = myGp.getObj()[theIndex].getName();
            switch(objectName) {
                case "Door", "SideDoor":
                    //doorMethod(theIndex);
                    //theKeyH.setAllKeys();
                    break;
                case "Chest":
                    break;
            }
        }
    }
    public void doorMethod(final int theIndex) {
        if (!myGp.getObj()[theIndex].isTouched()) {
            Door door = new Door(myQuestionRecord);
            myGp.getObj()[theIndex].setTouched(true);
            myGp.getObj()[theIndex].setDoor(door);
        }
        if (!myGp.getObj()[theIndex].isLocked()) {
            PopUp pop = new PopUp(myGp.getObj()[theIndex].getDoor());//myGp.getObj()[i].getDoor() -> was in the constructor
            System.out.println(myQuestionRecord.getQuestionRecord()); //just for testing, making ssure Record is working
            if (myGp.getObj()[theIndex].getDoor().getMyUnlock()) {
                myGp.getObj()[theIndex] = null;
            } else {
                myGp.getObj()[theIndex].setLocked(true);
            }
        } else {
            // will do later
        }
    }
}

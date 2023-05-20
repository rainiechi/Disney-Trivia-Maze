package View;

import Model.Door;
import Model.GameSettings;
import Model.Maze;

public class CollisionChecker {
    private GamePanel myGp;
    private GameSettings myGs;
    private Maze myMaze;
    public CollisionChecker(GamePanel theGp, GameSettings theGs, Maze theMaze) {
        myGp = theGp;
        myGs = theGs;
        myMaze = theMaze;
    }

    public void checkTile(PlayerManager thePlayer) {
        int entityLeftWorldX = thePlayer.getMyWorldX() + thePlayer.getSolidArea().x;
        int entityRightWorldX = thePlayer.getMyWorldX() + thePlayer.getSolidArea().x + thePlayer.getSolidArea().width;
        int entityTopWorldY = thePlayer.getMyWorldY() + thePlayer.getSolidArea().y;
        int entityBottomWorldY = thePlayer.getMyWorldY() + thePlayer.getSolidArea().y + thePlayer.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX/myGs.getTileSize();
        int entityRightCol = entityRightWorldX/myGs.getTileSize();
        int entityTopRow = entityTopWorldY/myGs.getTileSize();
        int entityBottomRow = entityBottomWorldY/myGs.getTileSize();

        int tileNum1;
        int tileNum2;

        switch(thePlayer.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - thePlayer.getSpeed())/myGs.getTileSize();
                tileNum1 = myMaze.getMyMapTileNum(entityLeftCol, entityTopRow);
                tileNum2 = myMaze.getMyMapTileNum(entityRightCol, entityTopRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (myMaze.getTile(tileNum1).isCollision()|| myMaze.getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + thePlayer.getSpeed())/myGs.getTileSize();
                tileNum1 = myMaze.getMyMapTileNum(entityLeftCol, entityBottomRow);
                tileNum2 = myMaze.getMyMapTileNum(entityRightCol, entityBottomRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (myGp.getMaze().getTile(tileNum1).isCollision() || myGp.getMaze().getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - thePlayer.getSpeed())/myGs.getTileSize();
                tileNum1 = myMaze.getMyMapTileNum(entityLeftCol, entityTopRow);
                tileNum2 = myMaze.getMyMapTileNum(entityLeftCol, entityBottomRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (myMaze.getTile(tileNum1).isCollision() || myMaze.getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + thePlayer.getSpeed())/myGs.getTileSize();
                tileNum1 = myMaze.getMyMapTileNum(entityRightCol, entityTopRow);
                tileNum2 = myMaze.getMyMapTileNum(entityRightCol, entityBottomRow);

                // If one of the tiles boolean is true and player is hitting it, collision is true
                if (myMaze.getTile(tileNum1).isCollision() ||myMaze.getTile(tileNum2).isCollision()) {
                    thePlayer.setCollision(true);
                }
                break;
        }
    }

    public int checkObject(PlayerManager thePlayer, boolean theCheck) {
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
    public void pickUpObject(int i) {
        // Any number is fine as long as its not the index
        // of an object.
        if (i != 999) {
            String objectName = myGp.getObj()[i].getName();
            switch(objectName) {
                case "Door":
                    if (!myGp.getObj()[i].isTouched()) {
                        Door door = new Door();
                        myGp.getObj()[i].setTouched(true);
                        myGp.getObj()[i].setDoor(door);
                    }
                    if (!myGp.getObj()[i].isLocked()) {
                        PopUp pop = new PopUp(myGp.getObj()[i].getDoor());
                        if (!myGp.getObj()[i].getDoor().getMyUnlock()) {
                            myGp.getObj()[i] = null;
                        } else {
                            myGp.getObj()[i].setLocked(true);
                        }
                    } else {
                        // will do later
                    }
                    break;
                case "Chest":
                    break;
            }
        }
    }
}

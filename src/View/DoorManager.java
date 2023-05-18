package View;

import Model.GameSettings;

import java.awt.*;

public class DoorManager extends ObjectManager {
    public DoorManager(GameSettings theGs, String theName, int theWorldX, int theWorldY) {
        super(theGs, theName, theWorldX, theWorldY);
        setSolidArea(new Rectangle(0,0,48,48));
        setSolidAreaDefaultX(getSolidAreaX());
//        mySolidAreaDefaultY = mySolidArea.y;
//        myCollision = true;
//        myWorldX = theWorldX;
//        myWorldY = theWorldY;
    }
}

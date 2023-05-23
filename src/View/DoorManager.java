package View;

import Model.Door;
import Model.GameSettings;

import java.awt.*;

public class DoorManager extends ObjectManager {
    private Door myDoor;
    public DoorManager(String theName, int theWorldX, int theWorldY, Boolean theCheck) {
        super(theName, theWorldX, theWorldY, theCheck);
        //myDoor = new Door();
        setSolidArea(new Rectangle(0,0,48,48));
        setSolidAreaDefaultX(getSolidAreaX());
        setMySolidAreaDefaultY(getSolidAreaY());
        //setMyImage(myDoor.getImage());
       // myDoor.setAttempted(true);
    }
    public Door getDoor() {
        return myDoor;
    }
}

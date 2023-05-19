package View;

import Model.Chest;
import Model.GameSettings;

import java.awt.*;

public class ChestManager extends ObjectManager{
    private Chest myChest;
    public ChestManager(GameSettings theGs, String theName, int theWorldX, int theWorldY, Boolean theCheck) {
            super(theGs, theName, theWorldX, theWorldY, theCheck);
            myChest = new Chest();
            setSolidArea(new Rectangle(0,0,48,48));
            setSolidAreaDefaultX(getSolidAreaX());
            setMySolidAreaDefaultY(getSolidAreaY());
            setMyImage(myChest.getImage());
        }
}

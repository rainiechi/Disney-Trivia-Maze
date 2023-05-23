package View;

import Model.Chest;
import Model.GameSettings;

import java.awt.*;

public class ChestManager extends ObjectManager{
    private Chest myChest;
    public ChestManager(final String theName, final int theWorldX, final int theWorldY, final Boolean theCheck) {
            super(theName, theWorldX, theWorldY, theCheck);
            myChest = new Chest();
            setSolidArea(new Rectangle(0,0,48,48));
            setSolidAreaDefaultX(getSolidAreaX());
            setMySolidAreaDefaultY(getSolidAreaY());
            setMyImage(myChest.getImage());
        }
}

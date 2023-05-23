package View;

import Model.GameSettings;

public class AssetSetter {
    private static final int TILE_SIZE = GameSettings.TILE_SIZE;

    /** private array of ObjectManager field */
    private ObjectManager[] myObjManager;

    /**
     * Constructor initializes fields.
     * @param theObjManager Array of ObjectManager
     */
    public AssetSetter(final ObjectManager[] theObjManager) {
        myObjManager = theObjManager;
        setWhiteDoors();
        setChests();
        setSideDoors();
    }

    /**
     * Method sets the coordinates of front facing white doors on the map.
     */
    public void setWhiteDoors() {
        // Starts at (8,14) on the map
        int x = 8;
        int y = 14;
        for (int i = 0; i <= 20; i++) {
            myObjManager[i] = new ObjectManager("Door", x * TILE_SIZE, y * TILE_SIZE, false);
            y += 13;
            // Starts at a new column on the map.
            if (i == 3 || i == 7 || i == 11 || i == 15 || i == 19) {
                y = 14;
                x += 15;
            }
        }
    }
    public void setSideDoors() {
        // Starts at (12,2) on the map
        int x = 15;
        int y = 3;
        for (int i = 21; i <= 42; i++) {
            myObjManager[i] = new ObjectManager("SideDoor", x * TILE_SIZE, y * TILE_SIZE, false);
            y += 13;
            if (i == 26) {
                y = 16;
                x += 16;
            } else if (i == 31 || i == 35 || i == 39 || i == 43) {
                y = 3;
                x += 16;
            }
        }
    }
    /**
     * Method sets the coordinates of chests on the map.
     */
    public void setChests() {
        myObjManager[44] = new ObjectManager("Chest", 8 * TILE_SIZE, 2 * TILE_SIZE, true);
        myObjManager[45] = new ObjectManager("Chest", 60 * TILE_SIZE, 2 * TILE_SIZE, true);
        myObjManager[46] = new ObjectManager("Chest", 21 * TILE_SIZE, 16 * TILE_SIZE, true);
    }
}

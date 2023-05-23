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
    }

    /**
     * Method sets the coordinates of front facing white doors on the map.
     */
    public void setWhiteDoors() {
        // Starts at (8,14) on the map
        int x = 8;
        int y = 14;
        for (int i = 0; i <= 20; i++) {
            myObjManager[i] = new ObjectManager("Door", x * TILE_SIZE, y * TILE_SIZE, true);
            y += 13;
            // Starts at a new column on the map.
            if (i == 3 || i == 7 || i == 11 || i == 15 || i == 19) {
                y = 14;
                x += 13;
            }
        }
    }
    /**
     * Method sets the coordinates of chests on the map.
     */
    public void setChests() {
        myObjManager[21] = new ObjectManager("Chest", 8 * TILE_SIZE, 2 * TILE_SIZE, true);
        myObjManager[22] = new ObjectManager("Chest", 60 * TILE_SIZE, 2 * TILE_SIZE, true);
        myObjManager[23] = new ObjectManager("Chest", 21 * TILE_SIZE, 16 * TILE_SIZE, true);
    }
}

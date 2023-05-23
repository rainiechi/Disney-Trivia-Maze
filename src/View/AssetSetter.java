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
            myObjManager[i] = new ObjectManager("Door", x * TILE_SIZE, y * TILE_SIZE, true);
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
        int x = 12;
        int y = 3;
        int count = 0;
        for (int i = 21; i <= 52; i++) {
            myObjManager[i] = new ObjectManager("SideDoor", x * TILE_SIZE, y * TILE_SIZE, true);
            System.out.println(count);
            if (count == 2) {
                x += 8;
                count = 0;
            } else {
                x += 7;
            }
            if (i == 22) {
                x += 16;
                count = 0;
            } else if (i == 24 || i == 32 || i == 46) {
                count = 0;
                x = 12;
                y += 13;
            } else if (i == 40) {
                x = 27;
                y += 13;
            }
            count++;
        }
    }
    /**
     * Method sets the coordinates of chests on the map.
     */
    public void setChests() {
        myObjManager[53] = new ObjectManager("Chest", 8 * TILE_SIZE, 2 * TILE_SIZE, true);
        myObjManager[54] = new ObjectManager("Chest", 60 * TILE_SIZE, 2 * TILE_SIZE, true);
        myObjManager[55] = new ObjectManager("Chest", 21 * TILE_SIZE, 16 * TILE_SIZE, true);
    }
}
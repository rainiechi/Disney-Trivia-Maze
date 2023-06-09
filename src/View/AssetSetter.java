package View;

import Model.GameSettings;

/**
 * AssetSetter class sets object positions in game using the X and Y coordinates
 * of the world map.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class AssetSetter {
    /** Tile size */
    private static final int TILE_SIZE = GameSettings.TILE_SIZE;

    /** private array of ObjectManager field */
    private final ObjectManager[] myObjManager;

    /**
     * Constructor initializes fields.
     * @param theObjManager Array of ObjectManager
     */
    public AssetSetter(final ObjectManager[] theObjManager) {
        this.myObjManager = theObjManager;
        try {
            setWhiteDoors();
            setChests();
            setSideDoors();
            ExitLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method sets the coordinates of front facing white doors on the map.
     */
    public void setWhiteDoors() {
        // Starts at (8,14) on the map
        int x = 8;
        int y = 14;
        for (int i = 0; i <= 19; i++) {
            // sets doors by going down rows.
            myObjManager[i] = new ObjectManager("Door", x * TILE_SIZE, y * TILE_SIZE, true);
            y += 13;
            // Starts at a new column on the map.
            if (i == 3 || i == 7 || i == 10 || i == 14 || i == 18) {
                y = 14;
                x += 15;
            } if (i == 8) { // this if statement is specifically skipping a row.
                y = 40;
            }
        }
    }

    /**
     * Sets exit door location on the map.
     */
    public void ExitLocation() {
        // location of exit door is (38,1) on world map.
        myObjManager[20] = new ObjectManager("Exit", 38 * TILE_SIZE, 1 * TILE_SIZE, true);
    }

    /**
     * Sets side door locations on the map.
     */
    public void setSideDoors() {
        // Starts at (12,2) on the map
        int x = 12;
        int y = 3;
        int count = 0;
        for (int i = 21; i <= 52; i++) {
            // sets side doors by moving column to column.
            // if statements are used to move to the next row
            // or skip a column.
            myObjManager[i] = new ObjectManager("SideDoor", x * TILE_SIZE, y * TILE_SIZE, true);
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
        myObjManager[54] = new ObjectManager("Chest", 53 * TILE_SIZE, 16 * TILE_SIZE, true);
        myObjManager[55] = new ObjectManager("Chest", 23 * TILE_SIZE, 16 * TILE_SIZE, true);
        myObjManager[56] = new ObjectManager("Chest", 8 * TILE_SIZE, 29 * TILE_SIZE, true);
        myObjManager[57] = new ObjectManager("Chest", 68 * TILE_SIZE, 2 * TILE_SIZE, true);
        myObjManager[58] = new ObjectManager("Chest", 23 * TILE_SIZE, 55 * TILE_SIZE, true);
        myObjManager[59] = new ObjectManager("Chest", 68 * TILE_SIZE, 29 * TILE_SIZE, true);
        myObjManager[60] = new ObjectManager("Chest", 53 * TILE_SIZE, 42 * TILE_SIZE, true);
    }

}
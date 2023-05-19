package View;

import Model.GameSettings;

public class AssetSetter {
    /** /** private GameSettings object field */
    private GameSettings myGs;
    /** private array of ObjectManager field */
    private ObjectManager[] myObj;

    /**
     * Constructor initializes fields.
     * @param theGs GameSettings object
     * @param theObj Array of ObjectManager
     */
    public AssetSetter(GameSettings theGs, ObjectManager[] theObj) {
        myGs = theGs;
        myObj = theObj;
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
            myObj[i] = new ObjectManager(myGs, "Door", x * myGs.getTileSize(), y * myGs.getTileSize(), true);
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
        myObj[21] = new ObjectManager(myGs, "Chest", 8 * myGs.getTileSize(), 2 * myGs.getTileSize(), true);
        myObj[22] = new ObjectManager(myGs, "Chest", 60 * myGs.getTileSize(), 2 * myGs.getTileSize(), true);
        myObj[23] = new ObjectManager(myGs, "Chest", 21 * myGs.getTileSize(), 16 * myGs.getTileSize(), true);
    }
}

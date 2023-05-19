package View;

import Model.GameSettings;

public class AssetSetter {
    private GameSettings myGs;
    private Object[] myObj;

    public AssetSetter(GameSettings theGs, Object[] theObj) {
        myGs = theGs;
        myObj = theObj;
        setWhiteDoors();
        setChests();
    }

    public void setWhiteDoors() {
        int y = 14;
        int x = 8;
        for (int i = 0; i <= 20; i++) {
            myObj[i] = new DoorManager(myGs, "Door", x * myGs.getTileSize(), y * myGs.getTileSize(), true);
            y += 13;
            if (i == 3 || i == 7 || i == 11 || i == 15 || i == 19) {
                y = 14;
                x += 13;
            }
        }
    }
    public void setChests() {
        myObj[21] = new ChestManager(myGs, "Chest", 8 * myGs.getTileSize(), 2 * myGs.getTileSize(), true);
        myObj[22] = new ChestManager(myGs, "Chest", 60 * myGs.getTileSize(), 2 * myGs.getTileSize(), true);
        myObj[23] = new ChestManager(myGs, "Chest", 21 * myGs.getTileSize(), 16 * myGs.getTileSize(), true);
    }
}

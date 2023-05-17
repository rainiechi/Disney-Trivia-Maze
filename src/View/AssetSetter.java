package View;

import Model.GameSettings;

public class AssetSetter {
    private GamePanel myGp;
    private GameSettings myGs;

    public AssetSetter(GamePanel theGp, GameSettings theGs) {
        myGs = theGs;
        myGp = theGp;
        setObject();
    }

    public void setObject() {
        myGp.getObj()[0] = new ObjectManager(myGs, "Door");
        myGp.getObj()[0].setWorldX(4* myGs.getTileSize());
        myGp.getObj()[0].setWorldY(1 * myGs.getTileSize());

        myGp.getObj()[1] = new ObjectManager(myGs, "Exit");
        myGp.getObj()[1].setWorldX(17* myGs.getTileSize());
        myGp.getObj()[1].setWorldY(1 * myGs.getTileSize());
    }
}

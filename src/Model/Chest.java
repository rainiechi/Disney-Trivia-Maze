package Model;

public class Chest {
    private Stone[] myChest;
    private boolean myEmptyChest;

    public Chest() {
        myChest = new Stone[1];
    }
    public void addToChest(Stone stone) {
        myChest[0] = stone;
    }

    // Displays what is inside the chest
    public void displayChest() {
        // Will implement later on
    }
    // Will use to change chest image to an opened chest instead of closed chest.
    public void acquireObject() {
        myEmptyChest = true;
    }
}

package Model;

public class Chest {

    private Stone myStone;
    private boolean myEmptyChest;

    public Chest(final Stone theStone) {
        myStone = theStone;
        myEmptyChest = false;
    }
    public Chest() {
        myStone = null;
        myEmptyChest = true;
    }

    public void addToChest(Stone theStone) {
        myStone = theStone;
        myEmptyChest = false;
    }

    // Displays what is inside the chest
    public String displayChest() {
        if (myStone != null) {
            return myStone.getStoneName();
        } else {
            return "Chest empty";
        }
    }

    public void takeStone(final Player thePlayer) {
        if (myStone == null) {
            System.out.println("Chest is empty");
        } else {
            thePlayer.addToBackpack(myStone);
            myStone = null;
            myEmptyChest = true;
        }
    }

    public boolean getMyEmptyChest() {
        return myEmptyChest;
    }

//    // Will use to change chest image to an opened chest instead of closed chest.
//    public void acquireObject() {
//        myEmptyChest = true;
//    }


}

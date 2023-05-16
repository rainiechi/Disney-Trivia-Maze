package Model;

public class Stone {
    private String myStoneName;
    private String myAbility;
    private int myUses;

    protected Stone(final String theStoneName, final String theAbility, final int theUses) {
        myStoneName = theStoneName;
        myAbility = theAbility;
        myUses = theUses;
    }
    public void useAbility(Player thePlayer) {
    }
    public String getDescription() {
        String s = "";
        return s;
    }
    public String getStoneName() {
        return myStoneName;
    }
    public String getmyAbility() {
        return myAbility;
    }

    public void decreaseUses() {
        if (myUses == 0) {
            throw new IllegalArgumentException("Uses was already at 0");
        }
        myUses--;
    }

    public int getUses() {
        return myUses;
    }

    // used for testing
    public String toString() {
        return myStoneName;
    }
}

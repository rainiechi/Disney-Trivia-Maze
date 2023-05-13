package Model;

public class Stone {
    protected String myStoneName;
    protected String myAbility;
    protected int myUses;

    public Stone() {
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

    public int getUses() {
        return myUses;
    }

    // used for testing
    public String toString() {
        return myStoneName;
    }
}

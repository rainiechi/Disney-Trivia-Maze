package Model;

public class Stone {
    private String myStoneName;
    private String myAbility;

    public Stone() {
    }

    public void useAbility() {
    }
    public String getDescription() {
        String s = "";
        return s;
    }
    public String getStoneName() {
        return myStoneName;
    }

    public void setStoneName(String name) {
        myStoneName = name;
    }

    public String getmyAbility() {
        return myAbility;
    }
    public void setMyAbility(String s) {
        myAbility = s;
    }

    // used for testing
    public String toString() {
        return "Stone:" + myStoneName;
    }
}

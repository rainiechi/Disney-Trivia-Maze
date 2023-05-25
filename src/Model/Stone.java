package Model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Stone implements Serializable {
    private transient BufferedImage myImage;
    private String myStoneName;
    private String myAbility;
    private int myUses;

    protected Stone(final String theStoneName, final String theAbility, final int theUses) {
        myStoneName = theStoneName;
        myAbility = theAbility;
        myUses = theUses;
        myImage = null;
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
    public BufferedImage getImage() {
        return myImage;
    }
    public void setImage(BufferedImage theImage) {
        myImage = theImage;
    }
}

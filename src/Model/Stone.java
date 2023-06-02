package Model;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Stone implements Serializable {
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

    public abstract void useAbility(Player thePlayer);


    public abstract String getDescription();

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

    //these two methods are used to manually serialize myImage since BufferedImage has to be transient
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        // Serialize the image data
        if (myImage != null) {
            ImageIO.write(myImage, "png", out);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        // Deserialize the image data
        try {
            myImage = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

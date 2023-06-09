package Model;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Abstract Stone class
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public abstract class Stone implements Serializable {
    /** Image of the stone object */
    private transient BufferedImage myImage;
    /** Name of the stone object */
    private String myStoneName;

    /** Number of uses of stone object */
    private int myUses;

    /**
     * Constructor initializes the fields.
     * @param theStoneName name of the stone
     * @param theUses number of uses of stone
     */
    protected Stone(final String theStoneName, final int theUses) {
        if (theUses < 1) {
            throw new IllegalArgumentException("Stone uses must be >= 1");
        }
        myStoneName = theStoneName;
        myUses = theUses;
        myImage = null;
    }

    /**
     * Abstract method for using the stone ability.
     * @param thePlayer player
     */
    public abstract void useAbility(final Player thePlayer);

    /**
     * Method decreases the uses of stone once it has been used.
     */
    public void decreaseUses() {
        if (myUses == 0) {
            throw new IllegalArgumentException("Uses was already at 0");
        }
        myUses--;
    }

    /**
     * Methods are used to manually serialize myImage since BufferedImage has to be transient.
     * @param out ObjectOutputStream
     * @throws IOException exception if null
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        // Serialize the image data
        if (myImage != null) {
            ImageIO.write(myImage, "png", out);
        }
    }
    /**
     * Methods are used to manually deserialize myImage since BufferedImage has to be transient.
     * @param in ObjectInputStream
     * @throws IOException exception if null
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        // Deserialize the image data
        try {
            myImage = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter method for uses.
     * @return number of uses
     */
    public int getUses() {
        return myUses;
    }

    /**
     * toString method returns name of stone. Used for testing.
     * @return String name
     */
    public String toString() {
        return myStoneName;
    }

    /**
     * Getter method for image of stone.
     * @return BufferedImage of stone
     */
    public BufferedImage getImage() {
        return myImage;
    }

    /**
     * Setter method for image.
     * @param theImage BufferedImage of stone
     */
    public void setImage(final BufferedImage theImage) {
        myImage = theImage;
    }

    /**
     * Getter method for description.
     * @return String of description.
     */
    public abstract String getDescription();

    /**
     * Getter method for stone name.
     * @return name of Stone
     */

    public String getStoneName() {
        return myStoneName;
    }


}

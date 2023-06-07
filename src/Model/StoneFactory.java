package Model;

/**
 * Stone factory class creates stone objects.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class StoneFactory {
    /**
     * Creates a new stone object based on the name/type.
     * @param theType type of stone.
     * @return Stone object created from the name/type.
     */
    public Stone createStone(final String theType) {
        Stone stone = null;
        if (theType.equalsIgnoreCase("Time")) {
            stone = new TimeStone();
        }
        else if (theType.equalsIgnoreCase("Power")) {
            stone = new PowerStone();
        }
        else if (theType.equalsIgnoreCase("Space")) {
            stone = new SpaceStone();
        }
        else if (theType.equalsIgnoreCase("Soul")) {
            stone = new SoulStone();
        }
        else if (theType.equalsIgnoreCase("Reality")) {
            stone = new RealityStone();
        }
        else if (theType.equalsIgnoreCase("Mind")) {
            stone = new MindStone();
        } else {
            throw new IllegalArgumentException("invalid stone type");
        }
        return stone;
    }
}

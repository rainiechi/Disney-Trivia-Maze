package Model;

import java.sql.Time;

public class StoneFactory {
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

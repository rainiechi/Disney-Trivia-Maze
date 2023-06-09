package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;


public class TileTest {

    @Test
    public void testGetImage() {
        BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Tile tile = new Tile();
        tile.setImage(image);

        assertEquals(image, tile.getImage());
    }

    @Test
    public void testSetCollision() {
        Tile tile = new Tile();
        tile.setCollision(true);

        assertTrue(tile.isCollision());
    }

    @Test
    public void testIsCollision() {
        Tile tile = new Tile();

        assertFalse(tile.isCollision());
    }
}
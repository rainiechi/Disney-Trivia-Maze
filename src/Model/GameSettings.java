package Model;

public class GameSettings {
    private final int myOriginalTileSize;
    private final int myScale;
    private final int myTileSize;
    private final int myMaxScreenCol;
    private final int myMaxScreenRow;
    private final int myScreenWidth; // 1024 pixels
    private final int myScreenHeight; // 768 pixels
    private final int myMaxWorldCol;
    private final int myMaxWorldRow;
    private final int myFPS;
    public GameSettings() {
        myOriginalTileSize = 32; // 32x32 tile
        myScale = 2;
        myTileSize = myOriginalTileSize * myScale; // 64x64 tile
        myMaxScreenCol = 16; // ratio is 4x3
        myMaxScreenRow = 12;
        myScreenWidth = 48 * myMaxScreenCol;
        myScreenHeight = 48 * myMaxScreenRow;
        myMaxWorldCol = 52;
        myMaxWorldRow = 48;
        myFPS = 60;
    }
    public int getTileSize() {
        return myTileSize;
    }

    public int getMaxScreenCol() {
        return myMaxScreenCol;
    }

    public int getMaxScreenRow() {
        return myMaxScreenRow;
    }

    public int getScreenWidth() {
        return myScreenWidth;
    }

    public int getScreenHeight() {
        return myScreenHeight;
    }

    public int getMaxWorldRow() {
        return myMaxWorldRow;
    }

    public int getMaxWorldCol() {
        return myMaxWorldCol;
    }
}

package Model;

/**
 * Contains all the pixel setting of the game.
 */
public class GameSettings {
    public static final int ORIGINAL_TILE_SIZE = 32;
    public static final int SCALE = 2;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public static final int MAX_SCREEN_COLUMN = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = 130 * MAX_SCREEN_COLUMN; // 1024 pixels
    public static final int SCREEN_HEIGHT = 130 * MAX_SCREEN_ROW; // 768 pixels
    public static final int MAX_WORLD_COLUMN = 68;
    public static final int MAX_WORLD_ROW = 63;
    public static final int WORLD_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    public static final int WORLD_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    public static final int FPS = 60;

}

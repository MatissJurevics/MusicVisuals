package C22501743Refactor;
import processing.core.PApplet;
import ie.tudublin.ProjectVisual;
// Change name when changing folder name
import C22501743Refactor.Ray;
import C22501743Refactor.Camera;
import java.util.*;
/*
 * Changes made in refactor
 *  - changed from using radians by default to degrees
 *  - Cleaned up code and fixed bugs
 * 
 * Changes in place:
 * - I'm now using degrees until I absolutely cannot
 */

public class Raycaster {
    ProjectVisual p;

    static int TICK = 20;
    static float FOV = 90; // FOV in degrees
    static int CELL_SIZE = 64;

    static int[][] map = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public Camera cam = new Camera(FOV, CELL_SIZE);

    public Raycaster(ProjectVisual canvas) {
        p = canvas;
    }

    /**
         * Checks if point is out of bounds
         * [ ] determine what the best argument is to pass in
         * @param x The x value of the point
         * @param y The y value of the point
         * @return True if the point is out of bounds and vice versa
    */
    private boolean checkOutOfBounds(int x, int y) {
        
    }

    /**
     * A functions that clears the screen by printing a black rectangle over it
     */
    private void clearScene() {
        p.fill(0);
        p.rect(0,0,p.width,p.height);
    }

    /**
     * Converts an angle from degrees to radians
     * @param degrees The angle in degrees  
     * @return A value between 0 and 2PI
     */
    private static float toRadians(float degrees) {
        return (degrees / 180) * PApplet.PI;
    }

    /**
     * Using pythagoras theorom, determine the distance 
     * between 2 points on a coordinate plane
     * @param x1 The x value of the first point
     * @param y1 The y value of the first point
     * @param x2 The x value of the second point
     * @param y2 The y value of the second point
     * @return The distance between the 2 points as a float
     */
    private static float distance (float x1, float y1, float x2, float y2) {
        float xDif = x2 - x1;
        float yDif = y2 - y1;
        double distance = Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2)); // Not immediatly returned for readability

        return (float) distance;
    }

    private Ray getVerticalCollisions(float degAngle) {
        float radAngle = toRadians(degAngle);

    }

    private Ray getHorizontalCollisions(float degAngle) {
        float radAngle = toRadians(degAngle);
    }

    /**
     * A function that renders the map onto the screen
     * @param scale a value that represents what scale you want the minimap to display at (1 = 64px)
     */
    private void renderMinimap(float scale) {
        float cellSize = CELL_SIZE * scale;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[y][x] != 0) {
                    p.fill(255);
                    p.rect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    public void run() {
        renderMinimap(0.5f);
    }

}

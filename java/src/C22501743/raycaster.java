package C22501743;
import processing.core.PApplet;
import C22501743.Camera;
import java.util.HashMap;

public class Raycaster {

    PApplet p;
    static int TICK = 45; // Duration of 1 frame in ms
    static double FOV = toRadians(80); // The cameras field of view in radians
    static int CELL_SIZE = 64; // The size of each cell in the 2d map
    // A 2d array that represents the map that will be rendered
    // TODO: write a function that convers the n x n map into an n+2 x n+2 map with walls auto added instead of manually done here
    static int[][] map = {
        {1, 1, 1, 1, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 1, 1, 1, 1}
    };
    Camera c = new Camera(FOV); // an o
    HashMap<String,String> colors = new HashMap<String,String>();

    Raycaster(PApplet canvas) {
        this.p = canvas;
    }

    // cam.x = 2;
    // cam.y = 2;
    // cam.angle = 0;
    // cam.speed = 0.1f;
    
    colors.put("rays", "#dddd00");
    colors.put("wallDark", "#666666");
    colors.put("wallDarkSecondary", "#555555");
    colors.put("wallLight", "#888888");
    colors.put("wallLightSecondary", "#777777");
    colors.put("floor", "#f4a460");
    colors.put("ceiling", "#44f");

    public boolean outOfBounds(double x, double y) {
        return x < 0 || x > map[0].length || y < 0 || y > map.length;
    }

    public static double toRadians(int degrees) {
        return degrees * (180 / PApplet.PI);
    }

    public class ray {
        double angle;
        double distance;
        boolean vertical;
    }

    public ray getVCollision(double angle) {
        static double right = Math.abs(Math.floor((angle - Math.PI / 2) / Math.PI) % 2) // Checks if the camera is facing the right
        static double firstX;
        if (right) {
            firstX = Math.floor(c.x / CELL_SIZE) * CELL_SIZE + CELL_SIZE;
        }
    }
    
   


}

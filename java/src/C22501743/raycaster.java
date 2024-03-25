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

    public boolean outOfBounds(int x, int y) {
        return x < 0 || x > map[0].length || y < 0 || y > map.length;
    }

    public static double toRadians(int degrees) {
        return degrees * (180 / PApplet.PI);
    }
    public static double distance(double x1,double y1,double x2,double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); // Just uses pythogoras to get the distance between two points
    }

    public class Ray {
        double angle;
        double distance;
        boolean vertical;
    }

    public Ray getVCollision(double angle) {
        boolean right = Math.abs(Math.floor((angle - Math.PI / 2) / Math.PI) % 2) == 0 ? false : true; // Checks if the camera is facing the right
        double firstX, firstY;

        if (right) {
            firstX = Math.floor(c.x / CELL_SIZE) * CELL_SIZE + CELL_SIZE;
        } else {
            firstX = Math.floor(c.x / CELL_SIZE) * CELL_SIZE;
        }

        firstY = c.y + (firstX - c.x) * Math.tan(angle);

        int xA = right ? CELL_SIZE : -CELL_SIZE;
        double yA = xA * Math.tan(angle);

        boolean wall = false;
        double nextX = firstX;
        double nextY = firstY;
        int cellX, cellY;

        while (!wall) {
            cellX = right ? (int) Math.floor(nextX / CELL_SIZE) : (int) Math.floor(nextX/CELL_SIZE) - 1;
            cellY = (int) Math.floor(nextY / CELL_SIZE);
            if (outOfBounds(cellX, cellY)) {
                break;
            }
            wall = map[cellY][cellX] == 1 ? true : false;
            if (!wall) {
                nextX += xA;
                nextY += yA;
            }
        }
        
        // Creates a new ray to be returned
        Ray returnVal;
        returnVal.distance = distance(c.x, c.y, nextX, nextY);
        returnVal.vertical = true;
        returnVal.angle = angle;

        return returnVal;
    }

    public Ray getHCollision(double angle) {
        boolean up = Math.abs(Math.floor((angle / Math.PI) % 2)) != 0 ? true : false;
        
    }
    
   


}

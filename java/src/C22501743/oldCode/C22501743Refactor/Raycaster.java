package C22501743.oldCode.C22501743Refactor;
import processing.core.PApplet;
import ie.tudublin.ProjectVisual;
import java.util.*;

import C22501743.oldCode.C22501743Refactor.*;

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
        return x < 0 || x > map.length - 1 || y < 0 || y > map.length - 1;
    }

    /**
     * A functions that clears the screen by printing a black rectangle over it
     */
    private void clearScene() {
        p.fill(112,214,215);
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
        boolean right = Math.abs(Math.floor((radAngle - Math.PI / 2) / Math.PI) % 2) == 1 ? false : true;
        
        float firstX = right
            ? (float) Math.floor(cam.x / CELL_SIZE) * CELL_SIZE + CELL_SIZE
            : (float) Math.floor(cam.x / CELL_SIZE) * CELL_SIZE;
        
        // First possible point of failure (because I dont really get it)
        float firstY = cam.y + (firstX - cam.x) * (float) Math.tan(radAngle); 

        int xA = right ? CELL_SIZE : - CELL_SIZE;
        float yA = xA * (float) Math.tan(radAngle);

        boolean wall = false;
        float nextX = firstX;
        float nextY = firstY;

        while (!wall) {
            int cellX = right
                ? (int) Math.floor(nextX / CELL_SIZE)
                : (int) Math.floor(nextX / CELL_SIZE) - 1;
            int cellY = (int) Math.floor(nextY / CELL_SIZE);

            if (checkOutOfBounds(cellX, cellY)) {
                break;
            }
            wall = map[cellX][cellY] != 0 ? true : false;

            if (!wall) {
                nextX += xA;
                nextY += yA;
            }
        }

        Ray returnVal = new Ray(degAngle, distance(cam.x, cam.y, nextX, nextY), true);
        return returnVal;

    }

    private Ray getHorizontalCollisions(float degAngle) {
        float radAngle = toRadians(degAngle);

        boolean up = Math.abs(Math.floor(radAngle / PApplet.PI) % 2) == 1 ? true : false;
        float firstY = up
            ? (float) Math.floor(cam.y / CELL_SIZE) * CELL_SIZE
            : (float) Math.floor(cam.y / CELL_SIZE) * CELL_SIZE + CELL_SIZE;
        float firstX = cam.x + (firstY - cam.y) / (float) Math.tan(radAngle);

        float yA = up ? -CELL_SIZE : CELL_SIZE;
        float xA = yA / (float) Math.tan(radAngle);

        boolean wall = false;
        float nextX = firstX;
        float nextY = firstY;

        while (!wall) {
            int cellX = (int) Math.floor(nextX / CELL_SIZE);
            int cellY = up 
                ? (int) Math.floor(nextY / CELL_SIZE) - 1
                : (int) Math.floor(nextY / CELL_SIZE);
            
            if (checkOutOfBounds(cellX, cellY)) {
                break;
            }
            wall = map[cellY][cellX] != 0 ? true : false;
            if (!wall) {
                nextX += xA;
                nextY += yA;
            }

        }

        Ray returnVal = new Ray(degAngle, distance(cam.x, cam.y, nextX, nextY), false);
        return returnVal;
    }

    private Ray castRay(float degAngle) {
        Ray vCol = getVerticalCollisions(degAngle);
        Ray hCol = getHorizontalCollisions(degAngle);
        // System.out.println(vCol.distance);
        // return hCol;
        return (hCol.distance >= vCol.distance) ? vCol : hCol;
    }

    private List<Ray> getRays() {
        // might not be right because this is degrees rather than radians
        float initialAngle = cam.degAngle - cam.FOV / 2;
        int numOfRays = p.width;
        float angleStep = cam.FOV / numOfRays;
        List<Ray> returnVal = new ArrayList<Ray>();

        for (int i = 0; i < numOfRays; i++) {
            float angle = initialAngle + (i * angleStep);
            Ray ray = castRay(angle);
            returnVal.add(ray);
        }

        return returnVal;
    }

    private float fixFishEye(float distance, float angle, float playerAngle) {
        float dif = angle - playerAngle;
        // may not work because of radian conversion
        return distance * (float) Math.cos(toRadians(dif));
    }

    private void renderWall(boolean vert, int i, float wallHeight) {
        float startY = (p.height / 2) - wallHeight / 2;

        p.fill(255);
        p.rect((float) i,(float) startY,(float) 1,(float) wallHeight);
    }

    public void renderScene(List<Ray> rays) {
        int index = 0;
        for (Ray ray : rays) {
            float distance = fixFishEye(ray.distance, ray.degAngle, cam.degAngle);
            float wallHeight = ((CELL_SIZE * 5) / distance) * 277; // Arbitrary, can be adjusted
            renderWall(ray.vertical, index, wallHeight);
            index++;
        }
    }

    /**
     * A function that renders the map onto the screen
     * @param scale a value that represents what scale you want the minimap to display at (1 = 64px)
     */
    private void renderMinimap(float scale, List<Ray> rays) {
        float cellSize = CELL_SIZE * scale;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[y][x] != 0) {
                    p.fill(255);
                    p.noStroke();
                    p.rect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }

        p.fill(200, 128, 128);
        p.circle(cam.x * scale,cam.y * scale, 20);
        float destx = cam.x + ((float) Math.cos(toRadians(cam.degAngle)) * 70) * scale;
        float desty = cam.y + ((float) Math.sin(toRadians(cam.degAngle)) * 70) * scale;
        p.fill(0,0,200);
        p.stroke(0,0,200);
        p.line(cam.x * scale, cam.y * scale, destx * scale, desty * scale);

        for (Ray ray: rays) {

            p.stroke(128);
            // System.out.println(ray.distance);
            float startX = cam.x * scale;
            float startY = cam.y * scale;
            // float endX = ((c.x + ((float) Math.cos(ray.angle)) * (float) ray.distance) * cellSize;
            float endX = (startX + ((float) Math.cos(toRadians(ray.degAngle)) * ray.distance) * scale);
            float endY = (startX + ((float) Math.sin(toRadians(ray.degAngle)) * ray.distance) * scale);
            p.line(startX, startY, endX, endY);
        }
        
    }

    boolean ran = false;
    public void run() {
        
            clearScene();
            List<Ray> rays = getRays();
            renderScene(rays);
            renderMinimap(0.5f, rays);
            ran = true;
        
    }

}

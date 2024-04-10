package C22501743;
import processing.core.PApplet;
// import processing.opengl.PShader;
import ie.tudublin.ProjectVisual;
import C22501743.Camera;
import C22501743.Ray;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Raycaster {
    boolean ran = false;
    ProjectVisual p;
    static int TICK = 45; // Duration of 1 frame in ms
    static float FOV = toRadians(105); // The cameras field of view in radians
    static int CELL_SIZE = 64; // The size of each cell in the 2d map
    // A 2d array that represents the map that will be rendered
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
    public Camera c = new Camera(FOV, CELL_SIZE); // an o
    
    HashMap<String,String> colors = new HashMap<String,String>();

    public Raycaster(ProjectVisual canvas) {
        p = canvas;
    }


    // cam.x = 2;
    // cam.y = 2;
    // cam.angle = 0;
    // cam.speed = 0.1f;
    
    // colors.put("rays", "#dddd00");
    // colors.put("wallDark", "#666666");
    // colors.put("wallDarkSecondary", "#555555");
    // colors.put("wallLight", "#888888");
    // colors.put("wallLightSecondary", "#777777");
    // colors.put("floor", "#f4a460");
    // colors.put("ceiling", "#44f");

    private boolean outOfBounds(int x, int y) {
        return x < 0 || x > map.length - 1 || y < 0 || y > map.length - 1;
    }

    private void clearScene() {
        p.fill(30, 30, 200);
        p.rect(0,0,p.width, p.height);
    }

    private static float toRadians(int degrees) {
        return (degrees * PApplet.PI) / 180;
    }
    private static float distance(float x1,float y1,float x2,float y2) {
        float xDif = x2 - x1;
        float yDif = y2 - y1;
        return (float) (Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2))); // Just uses pythogoras to get the distance between two points
    }


    private Ray getVCollision(float angle) {
        boolean right = Math.abs(Math.floor((angle - Math.PI / 2) / Math.PI) % 2) == 0 ? false : true; // Checks if the camera is facing the right
        float firstX, firstY;

        if (right) {
            firstX = (float) Math.floor(c.x / CELL_SIZE) * CELL_SIZE + CELL_SIZE;
            // System.out.println("right");
        } else {
            firstX = (float) Math.floor(c.x / CELL_SIZE) * CELL_SIZE;
            // System.out.println("left");
        }

        firstY = (c.y + (firstX - c.x) * (float) Math.tan(angle));

        int xA = right ? CELL_SIZE : -CELL_SIZE;
        float yA = (xA * (float) Math.tan(angle));

        boolean wall = false;
        float nextX = firstX;
        float nextY = firstY;
        int cellX, cellY;

        while (!wall) {
            cellX = right 
                ? (int) Math.floor(nextX / CELL_SIZE)   
                : (int) Math.floor(nextX/CELL_SIZE) - 1;
            cellY = (int) Math.floor(nextY / CELL_SIZE);
            if (outOfBounds(cellX, cellY)) {
                wall = true;
                break;
            }
            wall = map[cellY][cellX] == 1 ? true : false;
            if (!wall) {
                nextX += xA;
                nextY += yA;
            }
        }
        
        // Creates a new ray to be returned
        Ray returnVal = new Ray(angle, distance(c.x, c.y, nextX, nextY), true);

        return returnVal;
    }

    private Ray getHCollision(float angle) {
        boolean up = Math.abs(Math.floor((angle / Math.PI) % 2)) != 0 ? true : false;
        float firstY = up 
            ? (float) Math.floor(c.y / CELL_SIZE) * CELL_SIZE 
            : (float) Math.floor(c.y / CELL_SIZE) * CELL_SIZE + CELL_SIZE ;
        float firstX = c.x + (firstY - c.y) / (float) Math.tan(angle);
        // System.out.println("camera position " + c.y + " " + c.x);
        // System.out.println(firstY + " " + firstX);
        int yA = up ? -CELL_SIZE : CELL_SIZE;
        float xA = yA / (float) Math.tan(angle);
        // System.out.println("yA/xA " + yA + " " + xA);

        boolean wall = false;
        float nextX = firstX;
        float nextY = firstY;
        int cellX, cellY;

        while (!wall) {
            cellY = up 
            ? (int) Math.floor(nextY / CELL_SIZE) -1
            : (int) Math.floor(nextY / CELL_SIZE) ;

            cellX =  (int) Math.floor(nextX / CELL_SIZE);

            if (outOfBounds((int) cellX,(int) cellY)) {
                break;
            }
            // System.out.println("cellx/y" + cellX + " " + cellY);
            wall = map[(int) cellY][(int) cellX] == 0 ? false : true;

            if(!wall) {
                nextX += xA;
                nextY += yA;
            } else {
                // System.out.println("found wall at " + cellY + cellX);
            }
        }
        System.out.println(nextX + " " + nextY);
        // Creates a new ray to be returned
        Ray returnVal = new Ray(angle, distance(c.x, c.y, nextX, nextY), false);
        return returnVal;
    }

    private Ray castRay(float angle) {
        Ray vCol = getVCollision(angle);
        Ray hCol = getHCollision(angle);

        // return hCol;
        return (hCol.distance >= vCol.distance) ? vCol : hCol;
    }

    private List<Ray> getRays() {
        float initialAngle = c.angle - c.FOV / 2;
        int numOfRays = p.width;                // Number of rays is equal to the width of the window
        // int numOfRays = 1; // TODO: CHANGE BACK
        float angleStep = FOV / numOfRays;         // Calculate the change in angle for each line drawn on the screen
        List<Ray> returnVal = new ArrayList<Ray>(); // Initialise dynamic array

        for (int i = 0; i < numOfRays; i++) {
            float angle = initialAngle + (i * angleStep);
            Ray ray = castRay(angle);
            returnVal.add(ray);
        }
        return returnVal;
    }

    private float fixFishEye(float distance, float angle, float playerAngle) {
        float dif = angle - playerAngle;
        return distance * (float) Math.cos(dif);
    }
    
    private void renderWall(boolean vert, int i, float wallHeight) {
        float startY = (p.height / 2) - wallHeight / 2;
        // p.fill(128,128,128);
        p.fill(255);
        if (vert) {
            // p.fill(255, 128, 128);
        } 
        p.fill(244);
        p.rect((float) i,(float) startY,(float) 1,(float) wallHeight);
        
        // p.fill(30, 30, 200);
        // p.rect(i, (p.height / 2) + wallHeight / 2, i,(p.height/2)-wallHeight/2);
    }

    public void renderScene(List<Ray> rays) {
        int index = 0;
        for (Ray ray : rays) {
            float distance = fixFishEye(ray.distance, ray.angle, c.angle);
            float wallHeight = ((CELL_SIZE * 5) / distance) * 277; // Arbitrary, can be adjusted
            renderWall(ray.vertical, index, wallHeight);
            index++;
            

        }
    }

    public void renderMinimap(List<Ray> rays) {
        float scale = 0.25f;
        float cellSize = CELL_SIZE * scale;
        float posX = 0f;
        float posY = 0f;
        p.fill(255);
        p.rect(0,0,cellSize,cellSize);

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 16; x++) {
                int cell = map[y][x];
                if (cell != 0) {
                    p.fill(255);
                    p.stroke(255);
                    p.rect(posX + x * cellSize, posY + y * cellSize, cellSize, cellSize);
                }
            }
        }

        // Render the character
        p.fill(255,0,0);
        p.stroke(255,0,0);
        p.rect(c.x * scale,c.y * scale, 10, 10);

        for (Ray ray: rays) {

            p.stroke(128);
            // System.out.println(ray.distance);
            float startX = (posX + c.x) * scale;
            float startY = (posY + c.y) * scale;
            // float endX = ((c.x + ((float) Math.cos(ray.angle)) * (float) ray.distance) * cellSize;
            float endX = (startX + ((float) Math.cos(ray.angle) * ray.distance) * scale);
            float endY = (startX + ((float) Math.sin(ray.angle) * ray.distance) * scale);
            p.line(startX, startY, endX, endY);
        }
    }
    
    public void run() {
        if (!ran) {
            // p.filter(shader);
            // p.rect(0, 0, p.width, p.height);
            clearScene();
            List<Ray> rays = getRays();
            renderScene(rays);
            renderMinimap(rays);
            ran = true;
            
        } else {

        }
        
    }

   


}

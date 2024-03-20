package C22501743;
import processing.core.PApplet;
import C22501743.camera;
import java.util.HashMap;

public class raycaster {

    static int TICK = 45;
    static float FOV = toRadians(80);
    static int[][] map = {
        {1, 1, 1, 1, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 1, 1, 1, 1}
    };
    camera cam = new camera();
    HashMap<String,String> colors = new HashMap<String,String>();

    public static float toRadians(int degrees) {
        return degrees * (180 / PApplet.PI);
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

    public boolean outOfBounds(float x, float y) {
        return x < 0 || x > map[0].length || y < 0 || y > map.length;
    }

    public class ray {
        float angle;
        float distance;
        boolean vertical;
    }

    public ray getVCollision(float angle) {

    }
    
   


}

package C22501743.oldCode;

public class Camera {
    public float FOV;
    public float x;
    public float y;
    public float angle;
    public float speed;
    
    Camera(float fov, int cellSize) {
        FOV = fov;
        x = 6 * cellSize;
        y = 6 * cellSize;
        angle = 1.8f;
    }

    public void turn(float ang) {
        angle = (angle + ang % (2 * (float) Math.PI));
    }
}

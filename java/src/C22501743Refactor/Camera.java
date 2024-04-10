package C22501743Refactor;

public class Camera {
    public float FOV;
    public float x;
    public float y;
    public float degAngle;

    Camera(float degfov, int cellsize) {
        FOV = degfov;
        x = cellsize;
        y = cellsize;
        degAngle = 0;
    }

    public void turn(float newDegAngle) {
       degAngle += newDegAngle;
    }
}

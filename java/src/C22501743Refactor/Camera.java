package C22501743Refactor;

public class Camera {
    public float FOV;
    public float x;
    public float y;
    public float degAngle;

    

    public Camera(float degfov, int cellsize) {
        this.FOV = degfov;
        this.x = cellsize;
        this.y = cellsize;
        this.degAngle = 0;
    }

    public void turn(float turnDegAngle) {
       degAngle += turnDegAngle;
    }
}

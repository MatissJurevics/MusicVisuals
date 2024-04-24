package C22386123;

public class Cube {
    public float x, y, z; // Coordinates
    public float size; // Size of the cube
    public float speed; // Speed for animation or any movement
    public int color; // Color of the cube

    // Constructor
    public Cube(float x, float y, float z, float size, float speed) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
        this.speed = speed;
        // Default color setting (can be overridden)
        this.color = (int)(Math.random() * 0xFFFFFF);
    }

    // Getter for x coordinate
    public float getX() {
        return x;
    }

    // Getter for y coordinate
    public float getY() {
        return y;
    }

    // Getter for z coordinate
    public float getZ() {
        return z;
    }

    // Method to update position, can be overridden for different behaviors
    public void update() {
        z += speed;
        if (z > 500) {
            z = -1000; // Reset position
        }
    }

    // Method to change color
    public void changeColor(int newColor) {
        this.color = newColor;
    }
}


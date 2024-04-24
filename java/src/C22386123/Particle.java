package C22386123;

import processing.core.PApplet;
import processing.core.PVector;

public class Particle {
    PApplet sketch;
    PVector position;
    PVector velocity;
    float lifespan;

     // Constructor initializing a particle with a position.
    Particle(PApplet sketch, float x, float y) {
        this.sketch = sketch;
        position = new PVector(x, y, 0); // Position in 2D space.
        velocity = new PVector(sketch.random(-1, 1), sketch.random(1, 3));
        lifespan = 255;
    }

    // Update the position and decrease the lifespan of the particle.
    void update() {
        position.add(velocity);
        lifespan -= 2;
    }

    // Check if the particle's lifespan has ended.
    boolean isDead() {
        return lifespan < 0;
    }

    // Display the particle on the canvas.
    void display() {
        sketch.noStroke();
        sketch.fill(255, lifespan);
        sketch.ellipse(position.x, position.y, 8, 8);
    }
}

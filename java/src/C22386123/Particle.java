package C22386123;

import processing.core.PApplet;
import processing.core.PVector;

public class Particle {
    PApplet sketch;
    PVector position;
    PVector velocity;
    float lifespan;

    Particle(PApplet sketch, float x, float y) {
        this.sketch = sketch;
        position = new PVector(x, y, 0);
        velocity = new PVector(sketch.random(-1, 1), sketch.random(1, 3));
        lifespan = 255;
    }

    void update() {
        position.add(velocity);
        lifespan -= 2;
    }

    boolean isDead() {
        return lifespan < 0;
    }

    void display() {
        sketch.noStroke();
        sketch.fill(255, lifespan);
        sketch.ellipse(position.x, position.y, 8, 8);
    }
}

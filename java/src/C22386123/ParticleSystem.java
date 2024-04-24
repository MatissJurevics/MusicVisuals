package C22386123;

import java.util.ArrayList;
import processing.core.PApplet;

public class ParticleSystem {
    PApplet sketch;
    ArrayList<Particle> particles;
    // Flag to check if the particle system is currently active.
    boolean active;

    // Constructor to initialize the particle system.
    ParticleSystem(PApplet sketch) {
        this.sketch = sketch;
        particles = new ArrayList<Particle>();
        active = false;
    }

    // Run the particle system, update and display each particle.
    void run() {
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.update(); // Update the particle's state.
            p.display(); 
            if (p.isDead()) { // Check if the particle is dead.
                particles.remove(i); // Remove dead particle.
            }
        }
    }

    // Start emitting particles at a given location.
    void start(float x, float y) {
        active = true;
        emit(10, x, y); // Emit a default number of particles.
    }

    // Stop the particle system and clear all particles.
    void stop() {
        active = false;
        particles.clear();
    }

    // Check if the particle system is active.
    boolean isActive() {
        return active;
    }

    // Emit a number of particles at the specified location.
    void emit(int num, float x, float y) {
        for (int i = 0; i < num; i++) {
            particles.add(new Particle(sketch, x, y)); // Create and add new particles.
        }
    }
}

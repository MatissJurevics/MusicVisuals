package C22386123;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PVector;
import C22386123.Cube;


public class KarlsVisual extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    Cube[] cubes;
    int numCubes = 20;
    float angleX = 0;
    float angleY = 0;
    float sphereRadius = 150;

    public void settings()
    {
        // size(1024, 1000, P3D);
        fullScreen(P3D, SPAN);
        //size(800,800);
    }

    public void setup()
    {
        minim = new Minim(this);
        ap = minim.loadFile("Skrillex, Missy Elliott, & Mr. Oizo - RATATA [Official Visualizer].mp3", 1024);
        ap.play();
        ab = ap.mix;
        cubes = new Cube[numCubes];
        
        for (int i = 0; i < numCubes; i++) {
            // Ensure cubes are within visible coordinates
            cubes[i] = new VisualCube(random(-width/2, width/2), random(-height/2, height/2), random(-1000, -500), random(30, 100), random(2,10));
        }

        colorMode(HSB, 255);
        noStroke();;
    }

    public void draw() {
        background(0);
        float avg = ab.level() * 100;

        // Waveform around the screen edges
        
        // Translate to center
        translate(width/2, height/2, 0);
        
        // Draw rotating sphere
        pushMatrix();
        rotateX(angleX);
        rotateY(angleY);
        fill(255 * sin(angleX), 255, 255);
        sphere(sphereRadius);
        popMatrix();

        // Update angles
        angleX += 0.01;
        angleY += 0.01;
        
        // Draw cubes
        for (int i = 0; i < cubes.length; i++) {
            cubes[i].speed = 5 + avg;  // Adjust speed based on the audio level
            cubes[i].update();
            pushMatrix();
            translate(cubes[i].x, cubes[i].y, cubes[i].z);
            rotateX((float) (frameCount * 0.01));
            rotateY((float) (frameCount * 0.01));
            noFill();
            stroke(cubes[i].color);
            box(cubes[i].size);
            popMatrix();
        }
    }

    class VisualCube extends Cube {

        VisualCube(float x, float y, float z, float size, float speed) {
            super(x,y,z,size,speed);
            this.color = color(random(255), random(255), random(255)); // Random color
        }

        public void update() {
            z += speed; // Move towards the fullscreen
            if (z > 500) {
                z = -1000; // Reset Far Away
            }
        }
    }
}



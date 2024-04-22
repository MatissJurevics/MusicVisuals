package C22386123;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PVector;


public class myVisual1 extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    Cube[] cubes;
    int numCubes = 20;

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
            cubes[i] = new Cube(random(width), random(height), random(-1000, 500), random(20, 50));
        }

        colorMode(HSB);
        strokeWeight(2);
    }

    public void draw()
    {
        
        background(0);

        translate(width/2, height/2); // Centers the view

        float audioLevel = ab.level() * 100;

        for (int i = 0; i < cubes.length; i++) {
            float hue = map(i, 0, numCubes - i, 0, 255);
            stroke(hue, 255, 255);
            cubes[i].update(audioLevel);
            cubes[i].display(this, hue); // Display Cubes
        }
        
    }

}    

class Cube {
    PVector position;
    float size;
    float angle = 0; //Rotation Angle

    Cube(float x, float y, float z, float size) {
        position = new PVector(x, y, z);
        this.size = size;
    }

    void update(float speed) {
        position.z += speed;
        angle += 0.05; // Increment angle to rotate the cube
        if (position.z > 500) {
            position.z = -1000; // Reset Cube Position
        }
    }

    void display(PApplet app, float hue) {
        app.pushMatrix();
        app.translate(position.x, position.y, position.z);
        app.rotateX(angle); // Rotate around x axis
        app.rotateY(angle); // Rotate around y axis
        app.noFill(); // Hollow Cube
        app.stroke(hue, 255, 255);
        app.box(size);
        app.popMatrix();
    }
}



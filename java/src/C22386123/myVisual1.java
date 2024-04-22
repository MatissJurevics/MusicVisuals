package C22386123;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PVector;

class Cube {
    PVector position;
    float size;

    Cube(float x, float y, float z, float size) {
        position = new PVector(x, y, z);
        this.size = size;
    }

    void update(float speed) {
        position.z += speed;
        if (position.z > 500) {
            position.z = -1000; // Reset Cube Position
        }
    }

    void display(PApplet app, float hue) {
        app.pushMatrix();
        app.translate(position.x, position.y, position.z);
        app.fill(hue, 255, 255);
        app.box(size);
        app.popMatrix();
    }
}

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
        //size(1024, 1000, P3D);
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
            cubes[i] = new Cube(random(-width/2, width/2), random(-height/2, height/2), random(-1000, 0), random(30, 70));
        }

        colorMode(HSB);
    }

    public void draw()
    {
        // First color input: black (HSB with hue=0, saturation=0, brightness=0)
        // Second color input: a bright cyan-like color (HSB with hue=120, saturation=100, brightness=100)
        // Interpolation factor: uses the audio's current amplitude level multiplied by 10
        background(lerpColor(color(0,0,0), color(120,100,100), ap.mix.level() * 10));

        float amplitude = ap.mix.level() * 10;
        float speed = map(amplitude, 0, 1, 5, 20);
        float hue = map(amplitude, 0, 1, 0, 255);

        translate(width/2, height/2);

        for (int i = 0; i < cubes.length; i++) {
            cubes[i].update(speed);
            cubes[i].display(this, hue);
        }
        
    }
}    



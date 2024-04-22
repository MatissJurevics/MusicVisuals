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
    float cubeZ = 0;

    // float[] lerpedBuffer;
    // float y = 0;
    // float smoothedY = 0;
    // float smoothedAmplitude = 0;

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
        cubeZ = width;
        
    }

    public void draw()
    {
        background(lerpColor(color(0, 0, 0), color(120, 100, 100), ap.mix.level() * 10));

        float speed = map(ap.mix.level(), 0, 1, 2, 20);
        float colorValue = map(ap.mix.level(), 0, 1, 0, 255);
        fill(colorValue, 100, 100);

        pushMatrix();
        translate(width / 2, height / 2, cubeZ);
        box(50);
        popMatrix();

        cubeZ -= speed;
        if (cubeZ < -50) {
            cubeZ = width;
        }
    }
}    


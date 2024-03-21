package C22386123;

import java.util.ArrayList;
import java.util.Random;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class KarlsVisual extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    float[] lerpedBuffer;
    float smoothedAmplitude = 0;

    public void keyPressed() {
        if (key == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    public void settings() {
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Skrillex, Missy Elliott, & Mr. Oizo - RATATA [Official Visualizer].mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        lerpedBuffer = new float[width];
    }

    public void draw() {
        /* 
        float halfH = height / 2;
        float average = 0;
        float sum = 0;

        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        background(0);
        
        for (int i = 0; i < ab.size(); i++) {
            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);
            float f = lerpedBuffer[i] * halfH;
            line(i, halfH + f, i, halfH - f);
        }*/

        // Create an instance of Cube
        Cube cube = new Cube();
        // Call the render method and pass an instance of KarlsVisual to it
        cube.render(this);
    }

    // Inner class for Cube functionality
    class Cube {
        float lerpc;
        ArrayList<CubeObject> cubes1 = new ArrayList<CubeObject>();
        ArrayList<CubeObject> cubes2 = new ArrayList<CubeObject>();

        public void render(KarlsVisual kv) {
            noFill();
            kv.lights();
            createbox(10, cubes1, 0, 100, 20);
            drawbox(cubes1, kv);
        }

        public void createbox(int num, ArrayList<CubeObject> cubes, float low, float high, float size) {
            Random r = new Random();
            for (int i = 0; i < num; i++) {
                float x = r.nextFloat() * width;
                float y = r.nextFloat() * height;
                float z = r.nextFloat() * (high - low) + low;
                float rot = r.nextFloat(0, 2);
                float color = 0;
                CubeObject c;
                if (cubes == cubes1) {
                    c = new CubeObject(size, x, y, z, rot, color);
                } else {
                    c = new CubeObject(size, x, y, z, 0, color);
                }
                cubes.add(c);
            }
        }

        public void drawbox(ArrayList<CubeObject> cubes, KarlsVisual kv) {
            kv.camera();
            float rotang = map(smoothedAmplitude, 0.04f, 0.2f, 0, 0.2f);
            float speed = map(smoothedAmplitude, 0, 0.3f, 0, 50);
            float color = map(smoothedAmplitude, 0.06f, 0.23f, 100, 255);
            lerpc = lerp(lerpc, color, 0.05f);

            for (int i = 0; i < cubes.size(); i++) {
                CubeObject c = cubes.get(i);
                c.setSpeed(speed);
                c.setColor(lerpc);
                c.setRotang(rotang);
                // Add local variable kv here
                KarlsVisual kvLocal = kv;
                c.render(kvLocal);
            }
        }
    }

    // Inner class for cube
    class CubeObject {
        float size, x, y, z, rot, color;

        CubeObject(float size, float x, float y, float z, float rot, float color) {
            this.size = size;
            this.x = x;
            this.y = y;
            this.z = z;
            this.rot = rot;
            this.color = color;
        }

        void setSpeed(float speed) {
            // Set speed logic here
            // For example, you can set the speed of rotation based on the input speed
            this.rot = speed * 0.1f; // Adjust multiplier as needed
        }

        void setColor(float color) {
            // Set color logic here
            // For example, you can adjust the color of the cube based on the input color
            this.color = color;
        }

        void setRotang(float rotang) {
            // Set rotation angle logic here
            // For example, you can directly set the rotation angle based on the input value
            this.rot = rotang;
        }

        void render(KarlsVisual kv) {
            // Render cube logic here
            kv.pushMatrix();
            kv.translate(x, y, z);
            kv.rotateY(rot);
            kv.box(size);
            kv.popMatrix();
        }
    }
}

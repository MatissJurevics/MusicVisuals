package C22386123;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
//import processing.core.PVector;

public class KarlsVisual extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void settings()
    {
        //size(1024, 1000, P3D);
        fullScreen(P3D, SPAN);
        //size(800,800);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 
        ap = minim.loadFile("Skrillex, Missy Elliott, & Mr. Oizo - RATATA [Official Visualizer].mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    float off = 0;

    public void draw()
    {
        //background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        
        float cx = width / 2;
        float cy = height / 2;

        // Cases for Different Shapes
        switch (mode) {
			case 0:
                background(0);
                    
                // Wavy Lines Visual
                for (int i = 0; i < ab.size(); i++) {
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH;
                    line(i, halfH + f, i, halfH - f);
                }
                break;
            case 1:
                background(0);

                // Wave form
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(i, halfH + f, halfH - f, i);                    
                }
                break;

            case 2:
                background(0);
                for (int i = 0; i < ab.size(); i++) {
                    float hue = map(i, 0, ab.size(), 0, 255);
                    stroke(hue, 255, 255);

                    // Top 
                    line(i, 0, i, lerpedBuffer[i] * cy);

                    // Bottom 
                    line(i, height, i, height - (lerpedBuffer[i] * cy));

                    // Left 
                    line(0, i, lerpedBuffer[i] * cx, i);

                    // Right
                    line(width, i, width - lerpedBuffer[i] * cx, i);
                }
                break;

            case 3:
                background(0);
                strokeWeight(2);
                noFill();
                float r = map(smoothedAmplitude, 0, 0.5f, 100, 2000);
                float c = map(smoothedAmplitude, 0, 0.5f, 0, 255);
                stroke(c, 255, 255);
                circle(cx, cy, r);
                break;

            case 4:
                background(0);
                strokeWeight(2);
                noFill();
                float l = map(smoothedAmplitude, 0, 0.5f, 100, 2000); 
                float hue = map(smoothedAmplitude, 0, 0.5f, 0, 255); 
                stroke(hue, 255, 255);
                square(cx - l / 2, cy - l / 2, l);
                break;

            case 5:
                background(0);

                float radius = map(smoothedAmplitude, 0, 0.5f, 200, 400); // Dynamic radius based on amplitude
            
                // Draw the outer circle
                noFill();
                stroke(255);
                ellipse(cx, cy, radius * 2, radius * 2);
            
                // Draw the waveform inside the circle
                float angleStep = (float) (360.0 / ab.size());
                beginShape();
                for (int i = 0; i < ab.size(); i++) {
                    float angle = radians(i * angleStep);
                    float amp = lerpedBuffer[i];
                    float x = (float) (cx + cos(angle) * (radius * 0.5 + amp * radius * 0.5));
                    float y = (float) (cy + sin(angle) * (radius * 0.5 + amp * radius * 0.5));

                    // Create a rainbow effect based on the angle
                    float hueValue = map(angle, 0, TWO_PI, 0, 255);
                    stroke(hueValue, 255, 255);
                    
                    vertex(x, y);
                }
                endShape(CLOSE);
                break;

        }
    }        
}
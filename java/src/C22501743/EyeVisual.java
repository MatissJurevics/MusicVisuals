package C22501743;
import ddf.minim.AudioPlayer;
import ie.tudublin.Visual;
import processing.opengl.PShader;
import processing.core.PShape;

public class EyeVisual extends Visual {
    
    int sharp = 0;
    int epilepsy = 0;
    PShape eyeShape;
    AudioPlayer ap;
    
    public void settings() {
        size(1920, 1080, P2D);
    }

    public void setup() {
        frameRate(60);
        startMinim();
        loadAudio("Skrillex, Missy Elliott, & Mr. Oizo - RATATA [Official Visualizer].mp3");
        // loadAudio("Arles Theme.mp3");
        getAudioPlayer().play();
        // calculateAverageAmplitude();
        colorMode(HSB);
        eyeShape = loadShape("eye2.svg");
        ap = getAudioPlayer();

    }

    public void keyPressed() {
        switch (key) {
            case ' ':
                getAudioPlayer().cue(0);
                getAudioPlayer().play();
                break;
            case 's':
                sharp = (sharp + 1) % 2;
                break;
            case 'e':
                epilepsy = (epilepsy + 1) % 2;
                break;
            
            default:
                break;
        }
    }

    PShader testShader;
    public void draw() {
        calculateAverageAmplitude();

        // float smoothedAmp = getSmoothedAmplitude();
        // System.out.println(smoothedAmp);
        // float circleRadius = map(smoothedAmp, 0f, 1f, 0f, 2f);
        testShader = loadShader("test.frag");

        shader(testShader);
        testShader.set("u_resolution", (float)width, (float)height);
        testShader.set("u_time", millis()/1000.0f);
        testShader.set("u_sharp", sharp);
        testShader.set("u_epilepsy", epilepsy);
        testShader.set("u_amplitude", (getAmplitude()*2));
        System.out.println(getAmplitude());
        // testShader.set("u_sharp", sharp);
        // testShader.set("u_circleRadius", circleRadius);
        
        rect(0,0,width,height);
        // drawBackground();
        // drawGround();
        drawEye();
    }
    // float angle = 0;

    public void drawGround() {
        resetShader();
        fill(0, 200f);
        quad((width*0.25f), height/2, (width*0.75f), height/2, (width*0.9167f), height, (width*0.0833f), height);
        int startHeight = height/2;
        int endHeight = height;
        int lineNum = 15;
        for (int i = 0; i < lineNum; i++) {
            float ln2 = lineNum * lineNum;
            float i2 = i * i;
            float curHeight = map(i2, 0, ln2, startHeight, endHeight);
            stroke(255);
            float startWidth = map(i2, 0, ln2, 0.25f, 0.0833f) * width;
            float endWidth = map(i2, 0, ln2, 0.75f, 0.9167f) * width;
            line(startWidth, curHeight, endWidth, curHeight);
        }
        line((3*width)/4, startHeight, (11*width)/12, endHeight);
        line((width)/4, startHeight, (1*width)/12, endHeight);

        

    }

    public void drawEye() {
        resetShader();
        shapeMode(CENTER);
        float pupilX, pupilY;
        float shakeX = map(getAmplitude(), 0, 1, -10, 10);
        pupilX = width/2 + shakeX;
        pupilY = height/2 + shakeX;
        shape(eyeShape, width/2, height/2, 800, 400+getAmplitude()*50);
        fill(255, 255, 255);
        ellipse(width/2, height/2, 300, 300);
        fill(0, 0, 0);
        ellipse(pupilX, pupilY, 120, 120);
        fill(255, 255, 255);
        ellipse(pupilX, pupilY, 100, 100);
        fill(0, 0, 0);
        ellipse(pupilX, pupilY, 80, 80);
    }
    
    public void drawBackground() {
        background(0);
        stroke(255);
        // resetShader();
        
    }
}

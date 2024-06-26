package C22501743;
import ddf.minim.AudioPlayer;
import processing.core.PShape;
import ie.tudublin.Visual;
import processing.opengl.PShader;

public class EyeVisual extends Visual {
    
    int sharp = 0;
    int epilepsy = 0;
    int lastEyeMove = 0; // millis timestamp of when the eye last moved
    int eyeMoveDelay = 2000; // delay between eye movements
    AudioPlayer ap;
    PShape eyeShape;
    PShader testShader;
    Eye eye;
    
    public void settings() {
        // size(1920, 1080, P2D);
        fullScreen(P2D);
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
        eye = new Eye(this);

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
            case 'm':
                float randwidth = random(0, width);
                eye.movePupil(randwidth, 100);
                break;
            case 'n':
                eye.handleEyeCloseOpen();
                break;
            default:
                break;
        }
    }

    public void draw() {
        calculateAverageAmplitude();
        testShader = loadShader("test.frag");

        shader(testShader);
        testShader.set("u_resolution", (float)width, (float)height);
        testShader.set("u_time", millis()/1000.0f);
        testShader.set("u_sharp", sharp);
        testShader.set("u_epilepsy", epilepsy);
        testShader.set("u_amplitude", (getAmplitude()*2));
        // System.out.println(getAmplitude());

        
        rect(0,0,width,height);
        
        resetShader();
        eye.renderEye();
        if (millis() - lastEyeMove > eyeMoveDelay) {
            float randwidth = random(0, width);
            eye.movePupil(randwidth, 10);
            lastEyeMove = millis();
        }
        
        
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

    
    
    public void drawBackground() {
        background(0);
        stroke(255);
        // resetShader();
        
    }
}

package C22501743;
import ie.tudublin.Visual;
import processing.opengl.PShader;
public class MyVisual extends Visual {
    int rectsPerLine = 60;
    float[][] rectCoords = new float[rectsPerLine][2];
    float[] startPoints = new float[2];     // x, y values for the start point of a line that the rectangles will be drawn on
    float[] endPoints = new float[2];       // x, y values for the end point of a line that the rectangles will be drawn on

    public void settings() {
        size(1920, 1080, P2D);
    }

    public void setup() {
        frameRate(60);
        startMinim();
        loadAudio("heroplanet.mp3");
        colorMode(HSB);
        startPoints[0] = 0;
        startPoints[1] = 0;
        endPoints[0] = width/2;
        endPoints[1] = height/2;
    }
    PShader testShader;
    public void draw() {
        drawBackground();
        testShader = loadShader("test.frag");
        shader(testShader);
        float halfHeight = height/2;
        testShader.set("u_resolution", (float)width, (float)halfHeight);
        testShader.set("u_time", millis()/1000.0f);
        rect(0,0,width,height);
        drawGround();

    }

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
        resetShader();
        
    }
}

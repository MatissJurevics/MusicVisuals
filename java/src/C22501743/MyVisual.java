package C22501743;
import ie.tudublin.Visual;
public class MyVisual extends Visual {
    int rectsPerLine = 60;
    float[][] rectCoords = new float[rectsPerLine][2];
    float[] startPoints = new float[2];     // x, y values for the start point of a line that the rectangles will be drawn on
    float[] endPoints = new float[2];       // x, y values for the end point of a line that the rectangles will be drawn on

    public void settings() {
        size(1920, 1080);
    }

    public void setup() {
        startMinim();
        loadAudio("heroplanet.mp3");
        colorMode(HSB);
        startPoints[0] = 0;
        startPoints[1] = 0;
        endPoints[0] = width/2;
        endPoints[1] = height/2;
    }

    public void draw() {
        // calculateAverageAmplitude();
        background(0);
        // stroke(255);
        int rectSize = 60;

        for (int i = 0; i < rectsPerLine; i++) {
            rectCoords[i][0] = map(i, 0, rectsPerLine, startPoints[0], endPoints[0]);
            rectCoords[i][1] = map(i, 0, rectsPerLine, startPoints[1], endPoints[1]);
        }
        int i = 0;
        
        while ( i < rectsPerLine) {
            float c = map(i, 0, rectsPerLine, 0, 255);
            fill(c, 255, 255, map(i, 0, rectsPerLine, 20, 200));
            // print a rect once every 0.5 seconds
            if (millis() % 500 < 10) {
                rect(rectCoords[i][0], rectCoords[i][1], rectSize, rectSize);
                i++;
            }
            
        }
        
    }
}

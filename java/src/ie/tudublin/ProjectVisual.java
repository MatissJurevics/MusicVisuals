package ie.tudublin;
import ie.tudublin.Visual;
import processing.opengl.PShader;
import C22501743Refactor.Raycaster;

public class ProjectVisual extends Visual 
{
    // PShader shader;
    private Raycaster raycaster = new Raycaster(this);
    public void settings() {
        size(1200, 1200);

    }
    
    public void setup() {

        startMinim();
        loadAudio("heroplanet.mp3");
        colorMode(RGB);
    }
    public void keyPressed() {
        if (key == 'a') {
            raycaster.c.turn(0.03f);
        } 
        if (key == 'd') {
            raycaster.c.turn(-0.03f);
        }
    }

    public void draw() {
        
        raycaster.run();
        raycaster.cam.turn(10);
    }
}
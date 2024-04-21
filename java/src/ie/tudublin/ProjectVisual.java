package ie.tudublin;
import C22501743.oldCode.C22501743Refactor.Raycaster;
import ie.tudublin.Visual;
import processing.opengl.PShader;

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
            System.out.println("turning: " + raycaster.cam.degAngle);
            raycaster.cam.turn(10f);
        } 
        if (key == 'd') {
            raycaster.cam.turn(-10f);
        }
    }

    public void draw() {
        
        raycaster.run();
        
    }
}
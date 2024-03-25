package ie.tudublin;
import ie.tudublin.Visual;
import C22501743.Raycaster;

public class ProjectVisual extends Visual 
{
    private Raycaster raycaster = new Raycaster(this);
    public void settings() {
        size(1080, 1920);
    }

    public void setup() {
        startMinim();
        loadAudio("heroplanet.mp3");
        colorMode(RGB);
    }
    public void keyPressed() {
        if (key == 'a') {
            raycaster.c.angle += 0.3;
        } 
        if (key == 'd') {
            raycaster.c.angle -= 0.3;
        }
    }

    public void draw() {
        raycaster.run();
    }
}
package ie.tudublin;
import ie.tudublin.Visual;
import C22501743.Raycaster;

public class ProjectVisual extends Visual 
{
    public void settings() {
        size(1080, 1920);
    }

    public void setup() {
        startMinim();
        loadAudio("heroplanet.mp3");
        colorMode(RGB);
    }

    public void draw() {
        Raycaster raycaster = new Raycaster(this);
        raycaster.run();
    }
}
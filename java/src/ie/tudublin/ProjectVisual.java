package ie.tudublin;
import C22501743.EyeVisual;
import ie.tudublin.Visual;
import processing.opengl.PShader;

public class ProjectVisual extends Visual 
{
    int scene = 1;
    
    public void settings() {
        size(1200, 1200);

    }
    
    public void setup() {

        startMinim();
        loadAudio("heroplanet.mp3");
        colorMode(RGB);
    }
    public void keyPressed() {
       if (key == ' ')
       {
           getAudioPlayer().cue(0);
           getAudioPlayer().play();
       } else if (key == '1')
       {
           scene = 1;
       } else if (key == '2')
       {
           scene = 2;
       }

    }

    public void draw() {
        switch (scene) {
            case 1:
                EyeVisual mv = new EyeVisual();
                mv.setup();
                mv.draw();
                
                break;
        
            default:
                break;
        }
        
        
    }
}
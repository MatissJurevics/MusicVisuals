package ie.main;

import ie.tudublin.Visual;
import C22386123.KarlsVisual;
import C22501743.EyeVisual;
import processing.core.PApplet;
import ddf.minim.AudioPlayer;
import processing.core.PShape;


public class Controller extends Visual {
    KarlsVisual karlsVisual;
    EyeVisual eyeVisual;
    AudioPlayer ap;
    public PShape eyeShape;
    
    
    
    
    
    
    public void settings() {
        // fullScreen(SPAN, P3D)f;
    }
    public void setup() {
        frameRate(60);
        startMinim();
        loadAudio("Skrillex, Missy Elliott, & Mr. Oizo - RATATA [Official Visualizer].mp3");
        colorMode(HSB);
        ap = getAudioPlayer();
        getAudioPlayer().play();
        
        eyeShape = loadShape("eye2.svg");
        karlsVisual = new KarlsVisual();
        eyeVisual = new EyeVisual(this);
        eyeVisual.setup();
    }
    public void draw() {
        // karlsVisual.draw();
        eyeVisual.draw();
    }
}

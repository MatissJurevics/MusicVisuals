package ie.tudublin;

// import example.CubeVisual;
// import example.CubeVisual1;
// import example.MyVisual;
// import example.RotatingAudioBands;
import C22386123.myVisual1;
import C22501743.EyeVisual;
import processing.core.PApplet;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        PApplet eye;
        eye = new EyeVisual();
        PApplet myVisual1;
        myVisual1 = new myVisual1();

        PApplet.runSketch(a, eye);
        
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }

}
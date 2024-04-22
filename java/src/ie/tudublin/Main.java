package ie.tudublin;

// import example.CubeVisual;
// import example.CubeVisual1;
// import example.MyVisual;
// import example.RotatingAudioBands;
import C22386123.myVisual1;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new myVisual1());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }

}
package ie.tudublin;

// import example.CubeVisual;
// import example.CubeVisual1;
// import example.MyVisual;
// import example.RotatingAudioBands;
import C22386123.KarlsVisual;
// import C22501743.EyeVisual;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new KarlsVisual());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }

}
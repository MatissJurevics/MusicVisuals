package C22501743;
// import processing.core.PApplet;

public class Eye  {
    EyeVisual canvas;
    float blinkState = 1; // 1 = open, 0 = closed, Used to scale height
    float eyeType = 0; // 0 = Default circle, 1 = SVG X

    float innerPupilSize = 80;
    float outerPupilSize = 100;
    float blackRingSize = 120;
    float eyeSize = 300;

    float pupilOffsetX = 0;
    float pupilOffsetY = 0;
    float eyeOffsetX = 0;
    float eyeOffsetY = 0;

    public Eye(EyeVisual canvas) {
        this.canvas = canvas;
    }

    public void renderEye() {
        // Eye
        canvas.fill(255);
        ellipse(width / 2, height / 2, 200, 200);
    }

    public void renderPupil() {
        // Pupil
        fill(0);
        ellipse(width / 2, height / 2, 100, 100);
    }
}

package C22501743;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PConstants;

// import processing.core.PApplet;

public class Eye  {
    EyeVisual canvas;
    PShape eyeShape;
    float blinkState = 1; // 1 = open, 0 = closed, Used to scale height
    float eyeType = 0; // 0 = Default circle, 1 = SVG X
    float width, height;

    float innerPupilSize = 80;
    float outerPupilSize = 100;
    float blackRingSize = 120;
    float eyeSize = 300;

    float pupilOffsetX = -80f;
    float pupilOffsetY = 0;
    float eyeOffsetX = 0;
    float eyeOffsetY = 0;

    public Eye(EyeVisual _canvas) {
        this.canvas = _canvas;
        this.width = _canvas.width;
        this.height = _canvas.height;
        eyeShape = _canvas.eyeShape;
    }

    public void renderEye() {
        // Eye
        canvas.shapeMode(PConstants.CENTER);        
        canvas.shape(eyeShape, width / 2, height / 2, 800, (400 + canvas.getAmplitude() * 50) * blinkState);
        renderInnerEye();
    }

    public void renderInnerEye() {
        // Inner Eye
        float pupilX, pupilY;
        float shakeX = PApplet.map(canvas.getAmplitude(), 0, 1, -10, 10);
        float shakeY = PApplet.map(canvas.getAmplitude(), 0, 1, 10, -10);
        float halfWidth = canvas.width / 2;
        float halfHeight = canvas.height / 2;
        pupilX = halfWidth + shakeX;
        pupilY = halfHeight + shakeY;

        canvas.fill(255, 255, 255);
        canvas.ellipse(halfWidth + eyeOffsetX, halfHeight + eyeOffsetY , eyeSize, eyeSize* blinkState); // you can change the height by +- 50
        canvas.fill(0, 0, 0);
        canvas.ellipse(pupilX + pupilOffsetX + eyeOffsetX, pupilY + pupilOffsetY + eyeOffsetY, blackRingSize, blackRingSize* blinkState);
        canvas.fill(255, 255, 255);
        canvas.ellipse(pupilX + pupilOffsetX + eyeOffsetX, pupilY + pupilOffsetY + eyeOffsetY, outerPupilSize, outerPupilSize* blinkState);
        canvas.fill(0, 0, 0);
        canvas.ellipse(pupilX + pupilOffsetX + eyeOffsetX, pupilY + pupilOffsetY + eyeOffsetY, innerPupilSize, innerPupilSize* blinkState);
    }

    /**
     * Move the pupil so that it is looking at a given point at the bottom
     * of th screen
     * @param x The point at the bottom of the screen
     * @param t The time in milliseconds for the pupil to move
     */
    public void movePupil(float x, int t) {
        if (blinkState == 0) {
            return;
        }
        float endOffsetX, endOffsetY;
        float startOffsetX = pupilOffsetX;
        float startOffsetY = pupilOffsetY;
        float startTime = canvas.millis();
        float h = height /2;
        float w = x-(width / 2);
        float angle = PApplet.atan2(h, w);
        endOffsetX = PApplet.cos(angle) * 80;
        endOffsetY = PApplet.sin(angle) * 80;
        while (canvas.millis() - startTime < t) {
            float p = (canvas.millis() - startTime) / t;
            pupilOffsetX = PApplet.lerp(startOffsetX, endOffsetX, p);
            pupilOffsetY = PApplet.lerp(startOffsetY, endOffsetY, p);
            // renderInnerEye();
        }
    }

    public void resetPupil() {
        pupilOffsetX = 0;
        pupilOffsetY = 0;
    }

    public void handleEyeCloseOpen() {
        // over 300 miliseconds close the eye
        float startBlinkState = blinkState;
        float startTime = canvas.millis();
        resetPupil();
        blinkState = blinkState == 1 ? 0 : 1;

    }
}

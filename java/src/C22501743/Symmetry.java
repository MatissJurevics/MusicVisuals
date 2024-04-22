public class Symmetry {
    int numOfAxis;      // Number of axis of symmetry
    int typeOfSymmetry; // 0 = no symmetry, 1 = vertical, 2 = horizontal, 3 = radial
    int height;
    int width;

    Symmetry(int numOfAxis, String typeOfSymmetry, int height, int width) {
        this.numOfAxis = numOfAxis;
        this.height = height;
        this.width = width;
        switch (typeOfSymmetry) {
            case "VERT":
                this.typeOfSymmetry = 1;
                break;
            case "HORI":
                this.typeOfSymmetry = 2;
                break;
            case "RAD":
                this.typeOfSymmetry = 3;
                break;
        
            default:
                this.typeOfSymmetry = 0;
                break;
        }
    }

    public void changeSymmetryType( String typeOfSymmetry) {
        switch (typeOfSymmetry) {
            case "VERT":
                this.typeOfSymmetry = 1;
                break;
            case "HORI":
                this.typeOfSymmetry = 2;
                break;
            case "RAD":
                this.typeOfSymmetry = 3;
                break;
        
            default:
                this.typeOfSymmetry = 0;
                break;
        }
    }

    public void changeNumOfAxis(int numOfAxis) {
        this.numOfAxis = numOfAxis;
    }

    public void calculatePoints(float x1, float y1, float x2, float y2) {
        float[] pointsX = new float[numOfAxis];
        float[] pointsY = new float[numOfAxis];
        // TODO : Implement the logic to calculate the points based on the symmetry type

    }



}

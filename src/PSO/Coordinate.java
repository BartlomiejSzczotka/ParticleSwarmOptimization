package PSO;


/**
 * Here the locations of the particles are defined.
 */


public class Coordinate {

    private double x;
    private double y;


    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public Coordinate multiply(double multiplier){
        x = x * multiplier;
        y = y * multiplier;
        return new Coordinate(x, y);
    }


    public double getX() {
        return x;
    }

    public double getY() { return y; }


    //Unused methods to compute particles
    public Coordinate multiplyCoordinates(Coordinate coordinate){
        x = x * coordinate.x;
        y = y * coordinate.y;
        return new Coordinate(x, y);
    }

    public Coordinate add(Coordinate coordinate){
        x = x + coordinate.x;
        y = y + coordinate.y;
        return new Coordinate(x, y);
    }

    public Coordinate subtract(Coordinate coordinate){
        x = x - coordinate.x;
        y = y - coordinate.y;
        return new Coordinate(x, y);
    }


    @Override
    public String toString() {
        return " x=" + x + ", y=" + y;
    }
}

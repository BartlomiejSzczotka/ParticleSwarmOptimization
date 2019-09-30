package PSO;



/**
 * In this class the different functions for optimization can be defined.
 * Additional function scan easily be implemented (e.g. from this list: en.wikipedia.org/wiki/Test_functions_for_optimization)
 * Here, the Ackley-function is the default function.
 *
 * In order to use different functions the method calculateCurrentValue() in class Particle needs to be adjusted accordingly.
 * Also, both boundaries in class Particle need to be adjusted depending on the function.
 */


public class Functions {

    //Global minimum: 0 at (0,0)
    //Search space: -5 <= x,y <= 5
    public double calculateAckleyFunction(Coordinate coordinate){
        double x = coordinate.getX();
        double y = coordinate.getY();

        double term1 = -20 * Math.exp(-0.2 * Math.sqrt(0.5 * (x*x + y*y)));
        double term2 = -Math.exp(0.5 * (Math.cos(2 * Math.PI * x) + Math.cos(2 * Math.PI * y))) + Math.E + 20;

        return term1+term2;
    }


    //Global minimum: 0 at (0,0)
    //Search space: -5.12 <= x,y <= 5.12
    public double calculateRastriginFuction(Coordinate coordinate){
        double x = coordinate.getX();
        double y = coordinate.getY();

        double term1 = x*x - 10*Math.cos(2*Math.PI*x);
        double term2 = y*y - 10*Math.cos(2*Math.PI*y);
        return 10*2 + term1 + term2;
    }


    //Global minimum: 0 at (0,0)
    //Search space: -10 <= x,y <= 10
    public double calculateSphereFunction(Coordinate coordinate){
        double x = coordinate.getX();
        double y = coordinate.getY();

        double term1 = x*x;
        double term2 = y*y;
        return term1 + term2;
    }


    //Global minimum: 3 at (0,-1)
    //Search space: -2 <= x,y <= 2
    public double calculateGoldsteinPriceFunction(Coordinate coordinate){
        double x = coordinate.getX();
        double y = coordinate.getY();

        double term1 = 1 + Math.pow((x + y + 1), 2) * (19 - 14*x + 3*x*x - 14*y + 6*x*y + 3*y*y);
        double term2 = 30 + Math.pow((2*x - 3*y), 2) * (18 - 32*x + 12*x*x + 48*y - 36*x*y + 27*y*y);
        return  term1 * term2;
    }


    //Global minimum: 0 at (1,3)
    //Search space: -10 <= x,y <= 10
    public double calculateBoothFunction (Coordinate coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();

        double term1 = Math.pow(x + 2*y - 7, 2);
        double term2 = Math.pow(2*x + y - 5, 2);

        return term1 + term2;
    }


    //The Eggholder function will not be calculated correctly, because its global minimum is outside of the search space.
    //An additional constraint could be implemented, that limits the movement of the particles to the search space.
    //This way the global in the desired search space would be found.
    //
    //Global minimum: -959.6407 at (512,404.2319)
    //Search space: -512 <= x,y <= 512
    public double calculateEggholderFunction(Coordinate coordinate){
        double x = coordinate.getX();
        double y = coordinate.getY();

        double term1 = (-y - 47) * Math.sin(Math.sqrt(Math.abs((x/2 + (y + 47)))));
        double term2 = -x * Math.sin(Math.sqrt(Math.abs((x - (y + 47)))));

        return term1 + term2;
    }
}

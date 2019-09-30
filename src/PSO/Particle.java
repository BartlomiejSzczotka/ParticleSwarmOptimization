package PSO;

import java.util.concurrent.ThreadLocalRandom;


/**
 * In this class the Particles are defined. Several methods for updating the variables were created.
 */


public class Particle {

    private Coordinate position;
    private Coordinate velocity;
    private Coordinate personalBestPosition;
    private double personalBestValue;
    private double currentValue;

    private Functions function = new Functions();

    //Set the boundaries of the search space. THESE VALUES SHOULD BE ADJUSTED ACCORDING TO THE FUNCTION TYPE!
    private double lowerBoundary = -5;
    private double upperBoundary = 5;


    //The initial position and velocity of each particle is randomly generated within the boundaries of the search space.
    public Particle(){
        this.position = new Coordinate(randPos(), randPos());
        this.velocity = new Coordinate(randVel(), randVel());
        this.personalBestPosition = position;
        this.personalBestValue = calculateCurrentValue();
    }


    //Calculates the value of the particle at the current position.
    public double calculateCurrentValue(){
        this.currentValue = function.calculateAckleyFunction(position); //SWAP FUNCTION TYPE HERE
        return currentValue;
    }


    public void updatePosition(Coordinate updatedPosition){
        this.position = updatedPosition;
    }


    public void updateVelocity(Coordinate updatedVelocity){
        this.velocity = updatedVelocity;
    }


    public void updatePersonalBest(){
        if(currentValue < personalBestValue){
            this.personalBestValue = currentValue;
            this.personalBestPosition = position;
        }
    }


    public Coordinate addTwoCoordinates(Coordinate coordinate1, Coordinate coordinate2){
        double x = coordinate1.getX() + coordinate2.getX();
        double y = coordinate1.getY() + coordinate2.getY();
        return new Coordinate(x, y);
    }


    //Subtracts the value of coordinate2 from coordinate1
    public Coordinate subtractTwoCoordinates(Coordinate coordinate1, Coordinate coordinate2){
        double x = coordinate1.getX() - coordinate2.getX();
        double y = coordinate1.getY() - coordinate2.getY();
        return new Coordinate(x, y);
    }


    //unused
    public Coordinate multiplyTwoCoordinates(Coordinate coordinate1, Coordinate coordinate2){
        double x = coordinate1.getX() * coordinate2.getX();
        double y = coordinate1.getY() * coordinate2.getY();
        return new Coordinate(x, y);
    }


    //Getters to access the values of each particle from other classes
    public Coordinate getPosition(){return position;}

    public Coordinate getVelocity(){return velocity;}

    public Coordinate getPersonalBestPosition(){return personalBestPosition;}

    public double getPersonalBestValue(){return personalBestValue;}


    //creates a random position with the predefined boundaries
    private double randPos(){
        double rand = ThreadLocalRandom.current().nextDouble(lowerBoundary, upperBoundary);
        return rand;
    }


    //creates a random velocity with the predefined boundaries
    private double randVel(){
        double rand = ThreadLocalRandom.current().nextDouble(-Math.abs(upperBoundary-lowerBoundary), Math.abs(upperBoundary-lowerBoundary));
        return rand;
    }


    @Override
    public String toString() {
        return "Position: " + position.toString() + "; Velocity: " + velocity.toString() + "; Value: " + currentValue + "\n";    }

}

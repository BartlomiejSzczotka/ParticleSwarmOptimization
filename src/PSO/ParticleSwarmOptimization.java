package PSO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/**
 * This class is the core of the algorithm. Here the swarm is initialized and the best value of the function is
 * calculated.
 */


public class ParticleSwarmOptimization {

    private int terminationCriterion; //default: 400
    private double velocityFactor1; //default: 2
    private double velocityFactor2; //default: 2
    private double inertiaWeight; //default: 0.9 --- Is reduced linearly to inertiaWeightMinimum
    private double inertiaWeightMinimum; //default: 0.4

    private Coordinate globalBestPosition;
    private List<Particle> swarm = new ArrayList<>();
    private double globalBestValue = Double.MAX_VALUE;

    private double random1;
    private double random2;

    private Coordinate nextVelocity;
    private Coordinate nextPosition;


    //The algorithm is initialized
    public ParticleSwarmOptimization(int swarmSize, int terminationCriterion, double inertiaWeight,
                                     double inertiaWeightMinimum, double velocityFactor1, double velocityFactor2){

        this.terminationCriterion = terminationCriterion;
        this.inertiaWeight = inertiaWeight;
        this.inertiaWeightMinimum = inertiaWeightMinimum;
        this.velocityFactor1 = velocityFactor1;
        this.velocityFactor2 = velocityFactor2;

        initializeSwarm(swarmSize);
    }


    private void initializeSwarm(int swarmSize){
        for(int i=0; i<swarmSize; i++){
            Particle p = new Particle();
            swarm.add(p);
        }
        System.out.println(swarmSize + " Particles were initialized at: \n" + Arrays.toString(swarm.toArray()) + "\n");

        initializeGlobalBest();
    }


    private void initializeGlobalBest(){
        for(int i=0; i<swarm.size(); i++){

            if(swarm.get(i).getPersonalBestValue() < globalBestValue ){
                globalBestValue = swarm.get(i).getPersonalBestValue();
                globalBestPosition = swarm.get(i).getPersonalBestPosition();
            }
        }
    }


    //Main loop
    public void findBestPosition(){

        for(int j=0; j<terminationCriterion; j++){
            for(int i=0; i<swarm.size(); i++){

                Particle currentParticle = swarm.get(i);

                calculateNextPosition(currentParticle);
                currentParticle.updatePosition(nextPosition);

                currentParticle.calculateCurrentValue();
                currentParticle.updatePersonalBest();

                if(currentParticle.getPersonalBestValue() < globalBestValue){
                        globalBestValue = currentParticle.getPersonalBestValue();
                        globalBestPosition = currentParticle.getPersonalBestPosition();
                }

                calculateNextVelocity(currentParticle, j);
            }
            System.out.println("Best value in iteration " + j + ": " + globalBestValue + "     an Position:" + globalBestPosition);
        }
        System.out.println("\nBester gefundener Wert: " + globalBestValue + "  at" + globalBestPosition);
    }


    //v = w*v + c1*r1*(pbest-x) + c2*r2*(gbest-x)
    private void calculateNextVelocity(Particle particle, int iteration){
        initializeRandomFactors();

        Coordinate cognitiveTerm = particle.subtractTwoCoordinates(particle.getPersonalBestPosition(), particle.getPosition()); //pbest - x
        cognitiveTerm.multiply(velocityFactor1);
        cognitiveTerm.multiply(random1);

        Coordinate socialTerm = particle.subtractTwoCoordinates(globalBestPosition, particle.getPosition()); //gbest - x
        socialTerm.multiply(velocityFactor2);
        socialTerm.multiply(random2);

        Coordinate previousVelocity = particle.getVelocity().multiply(determineCurrentInertiaWeight(iteration)); //w * v
        Coordinate bothTerms = particle.addTwoCoordinates(cognitiveTerm, socialTerm);
        nextVelocity = particle.addTwoCoordinates(previousVelocity, bothTerms);

        particle.updateVelocity(nextVelocity);
    }


    //x = x + v
    private void calculateNextPosition(Particle particle){
        nextPosition = particle.addTwoCoordinates(particle.getPosition(), particle.getVelocity());

        particle.updatePosition(nextPosition);
    }


    //Initialize the two random factors between 0 and 1
    private void initializeRandomFactors(){
        random1 = ThreadLocalRandom.current().nextDouble(0, 1);
        random2 = ThreadLocalRandom.current().nextDouble(0, 1);
    }


    //Linearly decrease the inertia weight depending on the current iteration
    private double determineCurrentInertiaWeight(int iteration){
        double inertiaReductionPerIteration = (inertiaWeight - inertiaWeightMinimum) / terminationCriterion;
        return inertiaWeight - inertiaReductionPerIteration*iteration;
    }
}

package PSO;


/**
 * @author Jan Steinbach, jan.steinbach@wwu.de
 *
 * This program calculates the global minimum of a given function with Particle Swarm Optimization.
 *
 * In this class the algorithm is initialized and the required parameters are set.
 *
 * The parameters are:
 *      swarmSize (default: 50): The amount of particles that are used (sum(i))
 *      terminationCriterion (default: 400): The amount of iterations for the algorithm (t)
 *      inertiaWeight (default: 0.9): The value of the inertia at the first iteration (w)
 *      inertiaWeightMinimum (default: 0.4): The value of the inertia at the last iteration (w)
 *      velocityFactor1 (default: 2): The weight for the local/cognitive term (c1)
 *      velocityFactor2 (default: 2): The weight for the global/social term (c2)
 *
 * The available functions can be seen in the class Functions. Small adjustments in the code are necessary to change
 * the function type.
 */


public class Main {

    public static void main(String[] args) {

        //initialize the particle swarm with the desired parameters
        ParticleSwarmOptimization pso = new ParticleSwarmOptimization(50, 400,
                0.9, 0.4, 2, 2);

        pso.findBestPosition();
    }
}

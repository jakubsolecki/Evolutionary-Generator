import visualization.Simulation;

// TODO: reading initial parameters from JSON file
public class World {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation(40, 20, 5, 1,
                5000, 100, 10000, 50);
        simulation.simulate(15);
    }

}

package world;
//import classes.Animal;
import classes.*;
import visualization.Simulation;

// TODO: reading initial parameters from JSON file
public class World {
    public static void main(String[] args) throws InterruptedException {
        WorldMap map = new WorldMap(40, 40, 10,
                10, 5, 1, 50, 25);
        Animal pet = new Animal(new Vector2D(11,11), 500, map);
        Animal pet2 = new Animal(new Vector2D(15, 20), 500, map);
        Animal pet3 = new Animal(new Vector2D(10,10), 500, map);
        Animal pet4 = new Animal(new Vector2D(18,9), 500, map);

        map.placeOnMap(pet);
        map.placeOnMap(pet2);
        map.placeOnMap(pet3);
        map.placeOnMap(pet4);
        Simulation simulation = new Simulation(map, 10000);
        simulation.simulate();
    }

}

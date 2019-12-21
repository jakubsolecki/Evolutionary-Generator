package world;
//import classes.Animal;
import static enumClasses.MapDirection.*;
import classes.*;
import visualization.Simulation;

import java.io.IOException;
import java.util.Arrays;

public class World {
    public static void main(String[] args) throws InterruptedException {
        WorldMap map = new WorldMap(40, 40, 10, 10, 5, 1, 50, 25);
        Animal pet = new Animal(new Vector2D(11,11), 500, map);
        Animal pet2 = new Animal(new Vector2D(15, 20), 500, map);
        Animal pet3 = new Animal(new Vector2D(10,10), 500, map);
        Animal pet4 = new Animal(new Vector2D(18,9), 500, map);

        map.place(pet);
        map.place(pet2);
        map.place(pet3);
        map.place(pet4);
        Simulation simulation = new Simulation(map, 10000);
        simulation.simulate();
    }

}

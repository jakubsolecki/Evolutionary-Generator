package world;
//import classes.Animal;
import static enumClasses.MapDirection.*;
import classes.*;
import visualization.Simulation;

import java.io.IOException;
import java.util.Arrays;

public class World {
    public static void main(String[] args) throws InterruptedException {
        WorldMap map = new WorldMap(20, 20, 5, 5, 5, 1, 50, 25);
        Animal pet = new Animal(new Vector2D(9,10), 200, map);
        Animal pet2 = new Animal(new Vector2D(5, 5), 200, map);
        map.place(pet);
        map.place(pet2);
        Simulation simulation = new Simulation(map, 100);
        simulation.simulate();

        /*System.out.println(pet.getPosition().toString());
        System.out.println((Arrays.toString(pet.getGenes().getGenes())));


        for(int i = 0; i < 100; i++){
            System.out.println(map.toString());
            map.removeDeadAnimals();
            map.moveRandomAllAnimals();
            map.grandFeast();
            map.copulation();
            map.spawnGrass();
            map.nextDay();
            Thread.sleep(200); // in milliseconds
        }*/

    }

}

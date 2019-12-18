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
        Animal pet = new Animal(new Vector2D(0,49), 50, map);
        Animal pet2 = new Animal(new Vector2D(20, 49), 50, map);
        Animal pet3 = new Animal(new Vector2D(10,10), 50, map);
        Animal pet4 = new Animal(new Vector2D(7,2), 50, map);

        map.place(pet);
        map.place(pet2);
        map.place(pet3);
        map.place(pet4);
        Simulation simulation = new Simulation(map, 1000);
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

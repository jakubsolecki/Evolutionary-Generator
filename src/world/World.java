package world;
//import classes.Animal;
import static enumClasses.MapDirection.*;
import classes.*;

import java.util.Arrays;

public class World {
    public static void main(String[] args){
        WorldMap map = new WorldMap(100, 30, 10, 10, 5, 1, 50, 25);
        Animal pet = new Animal(new Vector2D(5,5), 200, map);
        Animal pet2 = new Animal(new Vector2D(5, 5), 200, map);
        map.place(pet);
        map.place(pet2);
        System.out.println(pet.getPosition().toString());
        System.out.println((Arrays.toString(pet.getGenes().getGenes())));

        for(int i = 0; i < 10000; i++){

            map.removeDeadAnimals();
            map.moveRandomAllAnimals();
            // map.grandFeast();
            System.out.println(map.toString());
            map.copulation();
            map.spawnGrass();
            //map.nextDay();
        }

        }

}

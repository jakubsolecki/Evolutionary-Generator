package world;
//import classes.Animal;
import static enumClasses.MapDirection.*;
import classes.*;

import java.util.Arrays;

public class World {
    public static void main(String[] args){
        WorldMap map = new WorldMap(100, 30, 10, 10, 5, 1, 50, 25);
        Animal pet = new Animal(new Vector2D(5,5), 20, map);
        System.out.println(pet.getPosition().toString());
        System.out.println((Arrays.toString(pet.getGenes().getGenes())));
        }
}

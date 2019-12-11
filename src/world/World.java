package world;
//import classes.Animal;
import static enumClasses.MapDirection.*;
import classes.*;

import java.util.Arrays;

public class World {
    public static void main(String[] args){
        Animal pet = new Animal(2,2, SE, 50);
        System.out.println(pet.getPosition().toString());
        System.out.println((Arrays.toString(pet.getGenes().getGenes())));
        }
}

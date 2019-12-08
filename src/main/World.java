package main;
import static main.Directions.*;

public class World {
    public static void main(String[] args){
        Animal pet = new Animal(2,2, SE);
        System.out.println(pet.getPosition().toString());
        pet.showGenotype();
        System.out.println(pet.toString());
    }
}

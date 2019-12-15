package tests;

import classes.Animal;
import classes.Vector2D;
import org.junit.jupiter.api.Test;
import world.WorldMap;
import java.util.Arrays;
import static enumClasses.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.lang.System.*;

public class WorldMapTest {

    @Test
    void placeTest(){
        WorldMap map = new WorldMap(100, 30, 10, 10, 5, 1, 50, 25);
        Animal pet = new Animal(new Vector2D(5,5), 20, map);
        Animal pet2 = new Animal(new Vector2D(5,5), 20, map);
        Animal pet3 = new Animal(new Vector2D(5,5), 20, map);
        Animal pet4 = new Animal(new Vector2D(5,5), 20, map);
        Animal pet5 = new Animal(new Vector2D(5,5), 20, map);

        assertTrue(map.place(pet));
        assertTrue(map.place(pet2));
        assertTrue(map.place(pet3));
        assertTrue(map.place(pet4));
       // assertTrue(map.place(pet5));
        System.out.println(pet.getPosition().toString());
        System.out.println((Arrays.toString(pet.getGenes().getGenes())));

        System.out.println(map.toString());
    }

    @Test
    void posCurveTest(){
        WorldMap map = new WorldMap(100, 30, 10, 10, 5, 1, 50, 25);
        Vector2D pos = new Vector2D(109, 100);
        Vector2D curved = map.posCurve(pos);
        out.println(curved.toString());

        Animal pet = new Animal(new Vector2D(5,5), 20, map);
        pet.move(FORWARD);
        out.println(pet.toString());

    }

}

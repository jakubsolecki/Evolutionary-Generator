package main;
import java.util.Random;
import static main.MapDirection.*;

public class Animal {
    private int[] genotype;
    private Vector2D position;
    private int energy;
    private MapDirection direction;

    Animal(int x, int y, MapDirection facing){
        position = new Vector2D(x, y);
        direction = facing;
        Random random = new Random();
        genotype = new int[32];
        for(int i = 0; i < genotype.length; i++)
            genotype[i] = random.nextInt(8);
    }

  /*      @Override
    public String toString(){
        if(direction == N)
            return "N";
        else if(direction == NE)
            return "NE";
        else if(direction == E)
            return "E";
        else if(direction == SE)
            return "SE";
        else if(direction == S)
            return "S";
        else if(direction == SW)
            return "SW";
        else if(direction == W)
            return "W";
        else //if(direction == NW)
            return "NW";
    }*/

    public void showGenotype(){
        for(int i : genotype)
            System.out.print(genotype[i] + " ");

        System.out.println();
    }

    public Vector2D getPosition() {
        return new Vector2D(position.x, position.y);
    }
}

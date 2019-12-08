package main;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap{ //extends AbstractWorldMap {
    private List<Animal> animalList = new ArrayList<>();
    private Vector2D lowerLeft;
    private Vector2D upperRight;

    public RectangularMap(int width, int height){
        if(width > 0 && height >0) {
            this.lowerLeft = new Vector2D(0,0);
            this.upperRight = new Vector2D(width, height);
        }
        else
            System.out.println("Złe rozmiary mapy");
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualization = new MapVisualizer(this);
        return mapVisualization.draw(this.lowerLeft, this.upperRight);
    }

    /*public Object objectAt(Vector2D position) { //implement using ListArray
        //return animals.get(position);
    }*/

}

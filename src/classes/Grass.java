package classes;

import interfaces.IPositionChangeObserver;
import interfaces.IWorldMapElement;

public class Grass implements IWorldMapElement {
    private Vector2D position;
    private int energy; //is it necessary?

    public Vector2D getPosition(){
        return position;
    }

    public Grass(Vector2D initialPosition){
        position = initialPosition;
    }

    public boolean isMovable(){
        return false;
    }

    @Override
    public String toString(){
        return "*";
    }

    public void addObserver(IPositionChangeObserver observer){
        return;
    }

}

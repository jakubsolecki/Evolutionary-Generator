package classes;

import interfaces.IPositionChangeObserver;
import interfaces.IWorldMapElement;

import java.awt.*;

public class Grass implements IWorldMapElement {
    private Vector2D position;

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

    public Color toColor() {
        return new Color(0,160,7);
    }
}

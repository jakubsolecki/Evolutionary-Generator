package classes;

import interfaces.IPositionChangeObserver;
import interfaces.IWorldMapElement;

import java.awt.*;

public class Grass implements IWorldMapElement {
    private Vector2D position;

    public Grass(Vector2D initialPosition){
        position = initialPosition;
    }


    public Vector2D getPosition(){
        return position;
    }


    public boolean isMovable(){
        return false;
    }


    public void addObserver(IPositionChangeObserver observer){
        return;
    }

    // TODO: add removeObsever


    // displaying
    @Override
    public String toString(){
        return "*";
    }

    public Color toColor() {
        return new Color(0,160,7);
    }
}

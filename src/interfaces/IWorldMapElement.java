package interfaces;

import classes.Vector2D;

public interface IWorldMapElement {

    boolean isMovable();

    Vector2D getPosition();

    void addObserver(IPositionChangeObserver observer);
}


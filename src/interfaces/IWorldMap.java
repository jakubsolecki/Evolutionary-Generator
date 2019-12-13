package interfaces;
import classes.*;
import enumClasses.*;

import java.util.LinkedList;

public interface IWorldMap {

    /**
     * Place an animal on the map.
     *
     * @param entity
     *            The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    boolean place(IWorldMapElement entity);


    /**
     * Return an object at a given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    Object objectAt(Vector2D position);


    boolean canMoveTo(Vector2D position);

}

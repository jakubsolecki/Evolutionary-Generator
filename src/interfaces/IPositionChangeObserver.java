package interfaces;
import classes.*;

public interface IPositionChangeObserver {

    boolean positionChanged(Vector2D oldPosition, Vector2D newPosition, Object entity);
    }

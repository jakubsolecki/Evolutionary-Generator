package enumClasses;

import classes.Vector2D;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum MapDirection {
    N, NE, E, SE, S, SW, W, NW;

    private static final List<MapDirection> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();


    public static MapDirection getRandomDirection() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }


    public static MapDirection next(MapDirection t){
        switch(t){
            case N:
                return NE;
            case NE:
                return E;
            case E:
                return SE;
            case SE:
                return S;
            case S:
                return SW;
            case SW:
                return W;
            case W:
                return NW;
            case NW:
                return N;
        }
        return t;
    }

    public static MapDirection previous(MapDirection t){
        //MapDirection n = t;
        switch(t){
            case N:
                return NW;
            case NE:
                return N;
            case E:
                return NE;
            case SE:
                return E;
            case S:
                return SE;
            case SW:
                return S;
            case W:
                return SW;
            case NW:
                return W;
        }
        return t;
    }


    public static Vector2D toUnitVector(MapDirection t){
        switch(t){
            case N:
                return new Vector2D(0,1);
            case NE:
                return new Vector2D(1,1);
            case E:
                return new Vector2D(1,0);
            case SE:
                return new Vector2D(1,-1);
            case S:
                return new Vector2D(0, -1);
            case SW:
                return new Vector2D(-1, -1);
            case W:
                return new Vector2D(-1, 0);
            case NW:
                return new Vector2D(-1,1);
            default:
                return new Vector2D(0,0);
        }
    }


    public static String toString(MapDirection directions){
        if(directions == N)
            return "N";
        else if(directions == NE)
            return "NE";
        else if(directions == E)
            return "E";
        else if(directions == SE)
            return "SE";
        else if(directions == S)
            return "S";
        else if(directions == SW)
            return "SW";
        else if(directions == W)
            return "W";
        else if(directions == NW)
            return "NW";
        else
            return null;
    }
}

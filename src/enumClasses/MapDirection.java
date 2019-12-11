package enumClasses;
import classes.Vector2D;

public enum MapDirection {
    N, NE, E, SE, S, SW, W, NW;

    //@Override
    public String toString(MapDirection directions){
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

    public static MapDirection next(MapDirection t){
        MapDirection n = t;
        if(t == N)
            n = NE;
        else if(t == NE)
            n = E;
        else if(t == E)
            n = SE;
        else if(t == SE)
            n = S;
        else if(t == S)
            n = SW;
        else if(t == SW)
            n = W;
        else if(t == W)
            n = NW;
        else //if(direction == NW)
            n = S;

        return n;
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
        return null;
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

}

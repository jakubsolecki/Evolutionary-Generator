package main;

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
        else //if(direction == NW)
            return "NW";
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

    public static MapDirection prevoius(MapDirection t){
        MapDirection n = t;
        switch(t){
            case N:
                n = NW;
                break;
            case NE:
                n = N;
                break;
            case E:
                n = NE;
                break;
            case SE:
                n = E;
                break;
            case S:
                n = SE;
                break;
            case SW:
                n = S;
                break;
            case W:
                n = SW;
                break;
            case NW:
                n = W;
                break;
        }
        return n;
    }

    /*public static Vector2D toUnitVector(MapDirection t){
        switch(t){
            case N:
                return new Vector2d(0,1);
            case SOUTH:
                return new Vector2d(0,-1);
            case EAST:
                return new Vector2d(1,0);
            case WEST:
                return new Vector2d(-1,0);
        }
        return new Vector2d(0,0);
    }*/

}

package classes;

public class Vector2D {
    public final int x;
    public final int y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }


    public boolean precedes(Vector2D other){
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2D other) {
        return x >= other.x && y >= other.y;
    }


    // TODO: consider removing
    public Vector2D upperRight(Vector2D other){
        int newX = Math.max(this.x, other.x);
        int newY = Math.max(this.y, other.y);
        return new Vector2D(newX, newY);
    }

    public Vector2D lowerLeft(Vector2D other){
        int newX = Math.min(this.x, other.x);
        int newY = Math.min(this.y, other.y);
        return new Vector2D(newX, newY);
    }


    public Vector2D add(Vector2D other){
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D subtract(Vector2D other){
        return new Vector2D(x - other.x, y - other.y);
    }


    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2D))
            return false;
        Vector2D that = (Vector2D) other;
        return x == that.x && y == that.y;
    }


    // required for hash map
    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }


    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }
}

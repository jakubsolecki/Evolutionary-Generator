package main;

public class Vector2D {
    public final int x;
    public final int y;

    Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }

}

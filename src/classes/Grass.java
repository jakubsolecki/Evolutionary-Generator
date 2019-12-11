package classes;

public class Grass {
    private Vector2D position;
    private int energy; //is it necessary?

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


}

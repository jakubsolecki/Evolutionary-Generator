package classes;
import enumClasses.MoveDirection;
import enumClasses.MapDirection;

import java.util.HashSet;
import java.util.Set;
import interfaces.*;

import static enumClasses.MapDirection.*;

public class Animal implements IWorldMapElement{
    private Genes genes;
    private Vector2D position;
    private int energy;
    private MapDirection direction;
    private Set<IPositionChangeObserver> observers = new HashSet<>();


    public Animal(int x, int y, MapDirection facing, int startEnergy) {
        position = new Vector2D(x, y);
        direction = facing;
        energy = startEnergy;
        genes = new Genes(32, 8);
    }

    @Override
    public boolean isMovable(){
        return true;
    }

    public Vector2D getPosition() {
        return new Vector2D(position.x, position.y);
    }

    public void changeEnergy(int changeEnergy){
        energy += changeEnergy;
        if(energy < 0)
            energy = 0;
    }

    public boolean isDead(){
        return energy == 0;
    }

    public Genes getGenes(){
        return genes;
    }

    public void move(MoveDirection toDirection) {
        Vector2D moved;
        switch (toDirection) {
            case LEFT:
                direction = previous(direction);
                break;
            case RIGHT:
                this.direction = next(direction);
                break;
            case FORWARD:
                moved = this.position.add(toUnitVector(this.direction));
                //if(map.canMoveTo(moved)){
                //    positionChanged(moved);
                this.position = moved;
                //}
                break;
            case BACKWARD:
                moved = this.position.subtract(toUnitVector(this.direction));
                //if(map.canMoveTo(moved)){
                //    positionChanged(moved);
                this.position = moved;
                //}
                break;
        }
    }

    public void rotate(int degree){

    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2D newPosition){
        for(IPositionChangeObserver observer : observers)
            observer.positionChanged(this.getPosition(), newPosition);
    }
}

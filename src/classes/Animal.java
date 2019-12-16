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
    private IWorldMap map;


    private Animal() {
        this.direction = N;
        genes = new Genes(32, 8);
        position = new Vector2D(2, 2);
    }

    private Animal(IWorldMap map) {
        this();
        this.map = map;
    }

    private Animal(IWorldMap map, Vector2D initialPosition) {
        this(map);
        this.position = initialPosition;
    }


    public Animal(Vector2D initialPosition, int startEnergy, IWorldMap map) {
        this(map, initialPosition);
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


    public int getEnergy(){
        return energy;
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
                moved = this.position.add(map.posCurve(toUnitVector(this.direction)));
                if(map.canMoveTo(moved)){
                    positionChanged(moved);
                this.position = moved;
                }
                break;
            case BACKWARD:
                moved = this.position.subtract(map.posCurve(toUnitVector(this.direction)));
                if(map.canMoveTo(moved)) {
                    positionChanged(moved);
                    this.position = moved;

                    break;
                }
        }
    }


    public void rotate() {
        int numOfRotation = genes.returnRandomGen();
        for (int i = 0; i < numOfRotation; i++) {
            this.move(MoveDirection.RIGHT);
        }
    }


    public Animal copulation(Animal mother) {

        int childEnergy = (int) (0.25 * mother.energy) + (int) (this.energy * 0.25);
        mother.changeEnergy((int) -(0.25 * mother.energy));
        this.changeEnergy((int) -(this.energy * 0.25));

        Animal child = new Animal(mother.getPosition(), childEnergy, map);
        child.genes = new Genes(this.genes, mother.genes);

        return child;
    }


    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }


    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }


    private void positionChanged(Vector2D newPosition){
        for(IPositionChangeObserver observer : observers)
            observer.positionChanged(this.getPosition(), newPosition, this);
    }

    @Override
    public String toString() {
        return energy == 0 ? "X" : "O"; //this.direction.toString();
    }
}

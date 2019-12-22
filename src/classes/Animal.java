package classes;

import enumClasses.MoveDirection;
import enumClasses.MapDirection;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import interfaces.*;

import static enumClasses.MapDirection.*;

public class Animal implements IWorldMapElement{
    private Genes genes;
    private Vector2D position;
    private int energy;
    private int startEnergy;
    private MapDirection direction;
    private Set<IPositionChangeObserver> observers = new HashSet<>();
    private IWorldMap map;
    private int age;

    // constructors
    private Animal() {
        direction = getRandomDirection();
        genes = new Genes(32, 8);
    }

    private Animal(IWorldMap map) {
        this();
        this.map = map;
    }

    private Animal(IWorldMap map, Vector2D initialPosition) {
        this(map);
        position = initialPosition;
    }

    public Animal(Vector2D initialPosition, int energy, IWorldMap map) {
        this(map, initialPosition);
        this.energy = energy;
        this.startEnergy = energy;
        age = 0;
    }


    // moving animal
    @Override
    public boolean isMovable(){
        return true;
    }

    public void move(MoveDirection toDirection) {
        Vector2D moved;
        switch (toDirection) {
            case LEFT:
                direction = previous(direction);
                break;
            case RIGHT:
                direction = next(direction);
                break;
            case FORWARD:
                moved = position.add(map.toProperPosition(toUnitVector(direction)));
                if(map.canMoveTo(moved)){
                    positionChanged(moved);
                    position = moved;
                }
                break;
            case BACKWARD:
                moved = position.subtract(map.toProperPosition(toUnitVector(direction)));
                if(map.canMoveTo(moved)) {
                    positionChanged(moved);
                    position = moved;

                }
                break;
        }
    }

    public void rotate() {
        int numOfRotation = genes.getRandomGen();
        for (int i = 0; i < numOfRotation; i++) {
            this.move(MoveDirection.RIGHT);
        }
    }


    public void changeEnergy(int changeEnergy){
        energy += changeEnergy;
        if(energy < 0)
            energy = 0;
    }


    public void increaseAge(){
        age++;
    }


    public boolean isDead(){
        return energy == 0;
    }


    public Animal copulate(Animal mother) {
        int childEnergy = (int) (0.25 * mother.energy) + (int) (this.energy * 0.25);
        mother.changeEnergy((int) -(0.25 * mother.energy));
        this.changeEnergy((int) -(this.energy * 0.25));

        // child is placed on it's parents' position
        Animal child = new Animal(mother.getPosition(), childEnergy, map);
        child.genes = new Genes(this.genes, mother.genes);

        return child;
    }


    // handling observers
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


    // family of get- methods
    public Vector2D getPosition() {
        return new Vector2D(position.x, position.y);
    }

    public int getEnergy(){
        return energy;
    }

    public Genes getGenes(){
        return genes;
    }

    public int getAge(){
        return age;
    }


    // displaying
    @Override
    public String toString() {
        return energy == 0 ? "X" : "O";
    }

    public Color toColor() {
        if (energy == 0) return new Color(222, 221, 224);
        if (energy < 0.2 * startEnergy) return new Color(224, 179, 173);
        if (energy < 0.4 * startEnergy) return new Color(224, 142, 127);
        if (energy < 0.6 * startEnergy) return new Color(201, 124, 110);
        if (energy < 0.8 * startEnergy) return new Color(182, 105, 91);
        if (energy < startEnergy) return new Color(164, 92, 82);
        if (energy < 2 * startEnergy) return new Color(146, 82, 73);
        if (energy < 4 * startEnergy) return new Color(128, 72, 64);
        if (energy < 6 * startEnergy) return new Color(119, 67, 59);
        if (energy < 8 * startEnergy) return new Color(88, 50, 44);
        if (energy < 10 * startEnergy) return new Color(74, 42, 37);
        return new Color(55, 31, 27);
    }
}

package world;
import interfaces.*;
import classes.*;
import classes.Animal;

import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver{
    //map size
    private Vector2D upperRight = new Vector2D(39,39);
    private Vector2D lowerLeft = new Vector2D(0,0);
    private Vector2D jungleUpperRight = new Vector2D(24,24);
    private Vector2D jungleLowerLeft = new Vector2D(14,14);
    private int jungeWidth = 10;
    private int jungleHeight = 10;
    private int width = 40;
    private int height = 40;

    //living
    private int grassEnergy;
    private int energyDayDrain;
    private int startEnergy;
    private int copulationEnergyLowerLimit;

    private Map<Vector2D, Grass> grass = new HashMap<>();
    private Map<Vector2D, LinkedList<Animal>> animals = new HashMap<>();
    public LinkedList<Animal> animalList;
    public LinkedList<Grass> grassList;

    public WorldMap(/*int width, int height, int jungleWidth, int jungleHeight,*/ int grassEnergy, int dayCost, int initialEnergy, int copulationEnergy){
        //map attributes
        //this.width = width;
        //this.height = height;
        //lowerLeft = new Vector2D(0,0);
        //upperRight = new Vector2D(width - 1, height - 1);
        this.grassEnergy = grassEnergy;
        this.energyDayDrain = (-1) * dayCost;
        startEnergy = initialEnergy;
        copulationEnergyLowerLimit = copulationEnergy;
        grassList = new LinkedList<>();
        animalList = new LinkedList<>();
        //this.jungeWidth = jungleWidth;
        //this.jungleHeight = jungleHeight;

        //jungle
        /*int jly = 0;
        int jlx = 0;
        int jux = width - 1;
        int juy = height - 1;*/

        //potem dodoam dynamiczne obliczanie skrajnych wektorow dzungli oraz jej "skalowanie" (przesuwanie na srodek)
    }

    @Override
    public void positionChanged(Vector2D oldPosition, Vector2D newPosition){
        Animal pet = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, pet);
    }


    public boolean canMoveTo(Vector2D position){
        if(animals.containsKey(position))
            return false;
        else return !(isOccupied(position));
    }


    /*public String toString() {
        MapVisualizer mapVisualization = new MapVisualizer(this);
        return mapVisualization.draw(this.lowerLeft, this.upperRight);
    }*/


    public boolean place(Animal animal) {
        Vector2D pos = animal.getPosition();
        if (!isOccupied(pos)) {
            animals.put(animal.getPosition(), animal);
            animal.addObserver(this);
            animalList.add(animal);

            return true;
        }
        else throw new IllegalArgumentException(animal.getPosition().toString() + " Given position is already occupied!");
    }


    public boolean isOccupied(Vector2D position) {
        return animals.get(position) != null;
    }


    /*public void run(MoveDirection[] directions) {
        int i = 0;
        for(MoveDirection direction : directions) {
            Animal tmpAnimal = animalList.get(i % animalList.size());
            Vector2d hashKey = tmpAnimal.getPosition();
            animals.remove(hashKey);
            tmpAnimal.move(direction);
            animals.put(tmpAnimal.getPosition(), tmpAnimal);
            i++;
        }
    }*/


    public Object objectAt(Vector2D position) {
        return animals.get(position);
    }

}

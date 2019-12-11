package world;
import enumClasses.MoveDirection;
import interfaces.*;
import classes.*;
import classes.Animal;

import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver{
    //map properties
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

    //storing elements on map
    private Map<Vector2D, Grass> grass = new HashMap<>();
    private Map<Vector2D, LinkedList<Animal>> animals = new HashMap<>();
    private List<Animal> animalList;
    private List<Grass> grassList;

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
        grassList = new ArrayList<>();
        animalList = new ArrayList<>();
        //this.jungeWidth = jungleWidth;
        //this.jungleHeight = jungleHeight;

        //jungle
        /*int jly = 0;
        int jlx = 0;
        int jux = width - 1;
        int juy = height - 1;*/

        //dynamic jungle moving to the center will be added later
    }

    private Vector2D posCurve(Vector2D position){ //allows Animals to feel like on the globe
        int newX;
        int newY;

        if(position.x < lowerLeft.x)
            newX = (width - Math.abs(position.x % width)) % width;
        else
            newX = position.x % width;

        if(position.y < lowerLeft.y)
            newY = (height - Math.abs(position.y % width)) % width;
        else
            newY = position.y % width;

        return new Vector2D(newX, newY);
    }

    public boolean place(IWorldMapElement entity) {
        Vector2D position = posCurve(entity.getPosition());

        if(!canBePlaced(position))
            throw new IllegalArgumentException("Field" + position.toString() + "is already full");

        if(entity instanceof Animal){
            addAnimal((Animal) entity, position);
            animalList.add((Animal) entity);
            entity.addObserver(this);
        }

        if(entity instanceof Grass){
            if(grass.get(position) == null)
                grass.put(position, (Grass) entity);
            grassList.add((Grass) entity);
        }

        return true;
    }

    private boolean canBePlaced(Vector2D position){
        Vector2D mapPosition = posCurve(position);

        if(animals.get(position) == null)
            return true;

        return animals.get(position).size() < 2;
    }

    public boolean canMoveTo(Vector2D position){ //has to be checked
        if(animals.containsKey(position))
            return false;
        else return !(isOccupied(position));
    }

    public boolean isOccupied(Vector2D position) {
        return animals.get(position) != null; //??
    }

    private void addAnimal(Animal animal, Vector2D position){
        //will be added later
    }

    public void run(MoveDirection[] directions){
        //will be added later
    }

    public void positionChanged(Vector2D oldPosition, Vector2D newPosition){
        //Animal pet = animals.get
    }

    public Object objectAt(Vector2D position) {
        return animals.get(position);
    }

}

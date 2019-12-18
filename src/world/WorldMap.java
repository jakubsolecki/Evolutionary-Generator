package world;
import enumClasses.MapDirection;
import enumClasses.MoveDirection;
import interfaces.*;
import classes.*;
import classes.Animal;
import visualization.MapVisualizer;

import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver{
    //map properties
    public final Vector2D upperRight;
    public final Vector2D lowerLeft;
    public final Vector2D jungleUpperRight;
    public final Vector2D jungleLowerLeft;
    public final int jungleWidth;
    public final int jungleHeight;
    public final int width;
    public final int height;


    //living
    private int grassEnergy;
    private int energyDayDrain;
    private int startEnergy;
    private int copulationEnergyLowerLimit;


    //storing elements on map
    private Map<Vector2D, Grass> grass = new HashMap<>();
    private Map<Vector2D, LinkedList<Animal>> animals = new HashMap<>();
    private LinkedList<Animal> animalList;
    private LinkedList<Grass> grassList;


    public WorldMap(int width, int height, int jungleWidth, int jungleHeight, int grassEnergy, int dayCost, int initialEnergy, int copulationEnergy){
        // map attributes
        this.width = width;
        this.height = height;
        lowerLeft = new Vector2D(0,0);
        upperRight = new Vector2D(width - 1, height - 1);
        this.grassEnergy = grassEnergy;
        this.energyDayDrain = (-1) * dayCost;
        startEnergy = initialEnergy;
        copulationEnergyLowerLimit = copulationEnergy;
        grassList = new LinkedList<>();
        animalList = new LinkedList<>();
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;

        //jungle
        int jly = 0;
        int jlx = 0;
        int jux = width - 1;
        int juy = height - 1;

        //dynamic jungle moving to the center
        for (int i = 0; i < (width - jungleWidth); i++) {
            if (i % 2 == 0) {
                jlx++;
            } else {
                jux--;
            }
        }

        for (int i = 0; i < (height - jungleHeight); i++) {
            if (i % 2 == 0) {
                jly++;
            } else {
                juy--;
            }
        }

        this.jungleLowerLeft = new Vector2D(jlx, jly);
        this.jungleUpperRight = new Vector2D(jux, jux);
    }


    public Vector2D posCurve(Vector2D position){ //"connects" opposite edges of the map
        int newX;
        int newY;

        if(position.x < lowerLeft.x)
            newX = (width - Math.abs(position.x % width)) % width;
        else
            newX = Math.abs(position.x % width);

        if(position.y < lowerLeft.y)
            newY = (height - Math.abs(position.y % height) % height);
        else
            newY = Math.abs(position.y % height);

        return new Vector2D(newX, newY);
    }


    private boolean canBePlaced(Vector2D position){
        Vector2D mapPosition = posCurve(position);

        if(animals.get(mapPosition) == null)
            return true;

        return animals.get(mapPosition).size() < 3;
    }


    public boolean canMoveTo(Vector2D position){
        Vector2D mapPosition = posCurve(position);

        if(animals.get(mapPosition) == null)
            return true;

        return animals.get(mapPosition).size() < 2;
    }


    public boolean isOccupied(Vector2D position) {
        return objectAt(position) != null;
    }


    public void moveRandomAllAnimals() {
        LinkedList<Animal> l = animalList;
        for (int i = 0; i < l.size(); i++) {
            animalList.get(i).rotate();
            animalList.get(i).move(MoveDirection.FORWARD);
        }
    }


    public void copulation() {
        for (LinkedList<Animal> animalList : animals.values()) {
            if (animalList != null) {
                if (animalList.size() == 2) {
                    Animal parent1 = animalList.get(0);
                    Animal parent2 = animalList.get(1);
                    if (parent1.getEnergy() > copulationEnergyLowerLimit)
                        if (parent2.getEnergy() > copulationEnergyLowerLimit) {
                            Animal child = parent2.copulation(parent1);
                            place(child);
                            //System.out.println(child.getGenes().toString());
                        }//statistic to end
                }
            }
        }
    }


    /*public Vector2D placeToBirth(Vector2D position){
        for(MapDirection i : MapDirection.values()){
            Vector2D birthPos = posCurve(MapDirection.toUnitVector(i).add(position));
            if(canBePlaced(birthPos));
                return birthPos;
        }
        return
    }*/


    public void nextDay() {
        for (LinkedList<Animal> animalList : animals.values()) {
            if (animalList != null) {
                if (animalList.size() > 0) {
                    for (Animal a : animalList) {
                        a.changeEnergy(energyDayDrain);
                    }
                }
            }
        }
    }

    public void removeDeadAnimals() {
        LinkedList<Animal> l = animalList;
        for (int i = 0; i < l.size(); i++) {
            Animal a = animalList.get(i);
            if (a.isDead()) {
                removeAnimal(a, a.getPosition());
                a.removeObserver(this);
                animalList.remove(a);
            }
        }
    }

    public boolean addAnimalOnRandomField() {

        int toMuchTimes = 0;
        while (toMuchTimes < width * height * 2) {
            Vector2D position = new Vector2D((int) (Math.random() * (width) + lowerLeft.x), (int) (Math.random() * (height) + lowerLeft.y));
            if (canBePlaced(position)) {
                place(new Animal( position, startEnergy, this));
                return true;
            }
            toMuchTimes++;
        }
        return false;
    }

    public boolean placeAnimalToRandomFieldInJungle() {
        int jungleSize = jungleWidth * jungleHeight;
        int mapSize = height * width;
        int steppeSize = mapSize - jungleSize;

        int toMuchTimes = 0;
        while ((double) toMuchTimes < (double) 2 * ((double) jungleSize / (double) steppeSize) * mapSize) {

            Vector2D position = new Vector2D((int) (Math.random() * (jungleWidth) + jungleLowerLeft.x)
                    , (int) (Math.random() * (jungleHeight) + jungleLowerLeft.y));
            if (canBePlaced(position)) {
                place(new Animal(position, startEnergy, this));
                return true;
            }
            toMuchTimes++;
        }
        return false;
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


    private void addAnimal(Animal animal, Vector2D position){
        if(animal == null)
            return;
        Vector2D mapPos = posCurve(position);
        LinkedList<Animal> list = animals.get(mapPos);
        if(list == null) {
            LinkedList<Animal> tmp = new LinkedList<>();
            tmp.add(animal);
            animals.put(mapPos, tmp);
        }
        else // if(list != null)
            list.add(animal);
    }


    private void removeAnimal(Animal animal, Vector2D position){
        Vector2D mapPos = posCurve(position);
        LinkedList<Animal> list = animals.get(mapPos);
        if(list == null || list.size() == 0)
            throw new IllegalArgumentException("Animal" + animal.getPosition() + "is not here");
        else{
            list.remove(animal);
            if(list.size() == 0)
                animals.remove(mapPos);
        }
    }


    public Object objectAt(Vector2D position) {
        Vector2D mapPos = posCurve(position);
        LinkedList<Animal> list = animals.get(mapPos);
        if(list == null || list.size() == 0)
            return grass.get(mapPos);
        else return list.getFirst();
    }


    public void grandFeast(){
        LinkedList<Grass> willBeEaten = new LinkedList<>();

        for(Grass food : grass.values()){
            LinkedList<Animal> eatingAnimals = animals.get(food.getPosition());

            if(eatingAnimals != null && eatingAnimals.size() > 0){
                int maxEnergy = 0;
                int mightyAnimals = 0;

                for(Animal a : eatingAnimals)
                    if(a.getEnergy() > maxEnergy)
                        maxEnergy = a.getEnergy();

                for(Animal a : eatingAnimals)
                    if(a.getEnergy() == maxEnergy)
                        mightyAnimals++;

                for(Animal a : eatingAnimals)
                    if(a.getEnergy() == maxEnergy)
                        a.changeEnergy(grassEnergy / mightyAnimals);
            }
            willBeEaten.add(food);
        }
        for(Grass g : willBeEaten){
            grass.remove(g);
        }
    }


    public boolean positionChanged(Vector2D oldPosition2, Vector2D newPosition2, Object entity) {

        Vector2D oldPosition = posCurve(oldPosition2);
        Vector2D newPosition = posCurve(newPosition2);

        if (canMoveTo(newPosition)) {

            removeAnimal((Animal) entity, oldPosition);
            addAnimal((Animal) entity, newPosition);
            return true;
        }
        return false;
    }


    public void spawnGrass() {

        //For Jungle

        int jungleSize = jungleWidth * jungleHeight;
        int mapSize = height * width;
        int steppeSize = mapSize - jungleSize;
        int toMuchTimes = 0;
        // stop looking for free place for grass in jungle, following to uniform probability distribution: after (size of jungle) times we should find free position
        //but if we didn't we can stop and meaning that jungle fields are close to be full of grass.
        while (toMuchTimes < 2 * jungleSize) {

            //random position in jungle
            Vector2D newGrass = new Vector2D((int) (Math.random() * (jungleWidth) + jungleLowerLeft.x), (int) (Math.random() * (jungleHeight) + jungleLowerLeft.y));
            if (grass.get(newGrass) == null && canBePlaced(newGrass)) {
                place(new Grass(newGrass));
                break;
            }
            toMuchTimes++;
        }

        //For Steppe

        toMuchTimes = 0;
        // stop looking for free place for grass in steppe, following to uniform probability distribution: after (size of steppe) times we should find free position
        //but if we didn't we can stop and be sure that steppe fields are close to be full of grass.
        while ((double) toMuchTimes < (double) 2 * ((double) jungleSize / (double) steppeSize) * mapSize) {

            Vector2D newGrass = new Vector2D((int) (Math.random() * (width) + lowerLeft.x), (int) (Math.random() * (height) + lowerLeft.y));
            if (grass.get(newGrass) == null && canBePlaced(newGrass) && !(newGrass.follows(jungleLowerLeft) && newGrass.precedes(jungleUpperRight))) {
                place(new Grass(newGrass));
                break;
            }
            toMuchTimes++;
        }
    }
    

    @Override
    public String toString(){
        MapVisualizer mapVisualization = new MapVisualizer(this);
        return mapVisualization.draw(lowerLeft, upperRight);
    }


    public LinkedList<Grass> getGrassList(){
        return this.grassList;
    }


    public LinkedList<Animal> getAnimalsList(){
        return this.animalList;
    }

    // TODO: remove?

    /*public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Vector2D getLowerLeft(){
        return this.lowerLeft;
    }

    public Vector2D getUpperRight(){
        return this.upperRight;
    }

    public Vector2D getJungleLowerLeft(){
        return this.jungleLowerLeft;
    }

    public Vector2D getJungleUpperRight(){
        return  this.jungleUpperRight;
    }

    public int getJungleWidth(){
        return this.jungleWidth;
    }

    public int getJungleHeight(){
        return this.jungleHeight;
    }*/

}

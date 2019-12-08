package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMap {
    protected Map<Vector2D, Animal> animals = new HashMap<>();
    protected List<Animal> animalList = new ArrayList<>();

    Vector2D lowerLeft;
    Vector2D upperRight;


/*    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal pet = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, pet);
    }*/


//    public boolean canMoveTo(Vector2d position){
//        if(animals.containsKey(position))
//            return false;
//        else return !(isOccupied(position));
//    }


    public String toString() {
        MapVisualizer mapVisualization = new MapVisualizer(this);
        return mapVisualization.draw(this.lowerLeft, this.upperRight);
    }


    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        if (!isOccupied(pos)) {
            animals.put(animal.getPosition(),animal);
            animal.addObserver(this);
            animalList.add(animal);

            return true;
        }
        else throw new IllegalArgumentException(animal.getPosition().toString() + " Given position is already occupied!");
    }


    public boolean isOccupied(Vector2d position) {
        return animals.get(position) != null;
    }


    /*public void run(MoveDirection[] directions){
        if(animals.isEmpty()){
            System.out.println("No animals on map");
            return;
        }
        Collection<Animal> animalsCollection = animals.values();
        ArrayList<Animal> animalsArray = new ArrayList<>(animalsCollection);
        Iterator it = animals.entrySet().iterator();
        for(int i = 0; i < directions.length; i++ ){
            Animal pet = animalsArray.get(i % animalsArray.size());
            Vector2d prevPosition = pet.getPosition();
            pet.move(directions[i]);
            if(!pet.getPosition().equals(prevPosition)){
                animals.remove(prevPosition);
                animals.put(pet.getPosition(), pet);
            }
        }
    }*/

    public void run(MoveDirection[] directions) {
        int i = 0;
        for(MoveDirection direction : directions) {
            Animal tmpAnimal = animalList.get(i % animalList.size());
            Vector2d hashKey = tmpAnimal.getPosition();
            animals.remove(hashKey);
            tmpAnimal.move(direction);
            animals.put(tmpAnimal.getPosition(), tmpAnimal);
            i++;
        }
    }


    public Object objectAt(Vector2D position) {
        return animals.get(position);
    }
}
}

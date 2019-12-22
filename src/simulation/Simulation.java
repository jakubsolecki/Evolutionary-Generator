package simulation;

import classes.Animal;
import classes.Grass;
import map.WorldMap;
import visualization.RenderPanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

public class Simulation {
    public WorldMap map;
    public int currentDay;
    public int totalDays;
    public JFrame frame;
    private int dayCost;
    private int energyOfGrass;
    private int animalsBornToday = 0;
    private int deadAnimalsToday = 0;
    private int cycleLength;
    private int firstAnimals;



    public Simulation(){
        // this.map = new WorldMap(widthAndHeight, jungleWidthAndHeight, grassEnergy, dayCost, startEnergy, copulationEnergy);
        JSONReader jsonParameters = new JSONReader();
        this.map = new WorldMap(jsonParameters.width, jsonParameters.height, jsonParameters.jungleWidth,
                jsonParameters.jungleHeight, jsonParameters.grassEnergy, jsonParameters.dayCost,
                jsonParameters.startEnergy, jsonParameters.copulationEnergy);
        frame = new JFrame("Evolutionary Generator");
        this.totalDays = jsonParameters.totalDays;
        currentDay = 0;
        this.dayCost = jsonParameters.dayCost;
        this.energyOfGrass = jsonParameters.grassEnergy;
        this.cycleLength = jsonParameters.cycleLengthMilliseconds;
        this.firstAnimals = jsonParameters.firstAnimals;
    }


    public void nextDay(){
        deadAnimalsToday = map.removeDeadAnimals();
        map.moveAllAnimals();
        map.grandFeast();
        animalsBornToday = map.copulate();
        map.spawnGrass();
        map.nextDay();
    }


    // Core of the simulation
    public void simulate() throws InterruptedException{
        LinkedList<Animal> animalsList = map.getAnimalsList();
        LinkedList<Grass> grassList = map.getGrassList();
        float avgEnergy = 0;
        float avgAge = 0;

        // frame.setResizable( false );
        frame.setSize(960, 540);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RenderPanel renderPanel = new RenderPanel(map, frame);
        frame.add(renderPanel);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(1,2, 5,0));

        JPanel infoPanel = new JPanel();
        JLabel mapSize = new JLabel("Map size (W x H): " + map.width + " x " + map.height);
        JLabel initialPopulation = new JLabel("Initial population: " + firstAnimals);
        JLabel dayDrain = new JLabel("Daily energy drain: " + dayCost);
        JLabel grassEnergy = new JLabel("Grass energy: " + energyOfGrass);
        JLabel day = new JLabel("Day: " + this.currentDay);
        JLabel animalCounter = new JLabel("Animals on map: " + animalsList.size());
        JLabel grassCounter = new JLabel("Plants on map: " + grassList.size());
        JLabel averageAge = new JLabel("Average animal age: " + avgAge);
        JLabel averageEnergy = new JLabel("Average animal energy: " + avgEnergy);
        JLabel animalsBorn = new JLabel("Animals born this day: " + animalsBornToday);
        JLabel deadAnimals = new JLabel("Dead animals today: " + deadAnimalsToday);

        infoPanel.setSize((int) (0.5 * frame.getWidth()), frame.getHeight());
        infoPanel.add(mapSize);
        infoPanel.add(initialPopulation);
        infoPanel.add(dayDrain);
        infoPanel.add(grassEnergy);
        infoPanel.add(day);
        infoPanel.add(animalCounter);
        infoPanel.add(grassCounter);
        infoPanel.add(averageAge);
        infoPanel.add(averageEnergy);
        infoPanel.add(animalsBorn);
        infoPanel.add(deadAnimals);
        infoPanel.setLayout(new GridLayout(11, 1));
        infoPanel.setVisible(true);

        frame.add(infoPanel);

        out.println("Simulation has begun.");

        // placing first animals randomly
        for(int i = 0; i < firstAnimals; i++)
            map.randomPlacingInJungle();

        TimeUnit.SECONDS.sleep(1);

        for(int i = 0; i < totalDays; i++){
            avgEnergy = 0;
            avgAge = 0;

            for(Animal a : animalsList) {
                avgEnergy += (float) a.getEnergy();
                avgAge += (float) a.getAge();
            }

            if(animalsList.size() > 0){
                int size = animalsList.size();
                avgEnergy = avgEnergy / size;
                avgAge = avgAge / size;
            }
            else{
                avgEnergy = 0;
                avgAge = 0;
            }

            out.println();
            out.println("Day: " + currentDay);
            day.setText("Day: " + currentDay);
            out.println("Animals on map: " + animalsList.size());
            animalCounter.setText("Animals on map: " + animalsList.size());
            out.println("Plants on map: " + grassList.size());
            grassCounter.setText("Plants on map: " + grassList.size());
            out.println("Average animal age: " + String.format("%.2f", avgAge));
            averageAge.setText("Average animal age: " + String.format("%.2f", avgAge));
            out.println("Average animal energy: " + String.format("%.2f", avgEnergy));
            averageEnergy.setText("Average animal energy: " + String.format("%.2f", avgEnergy));
            out.println("Animals born this day: " + animalsBornToday);
            animalsBorn.setText("Animals born this day: " + animalsBornToday);
            out.println("Dead animals today: " + deadAnimalsToday);
            deadAnimals.setText("Dead animals today: " + deadAnimalsToday);
            renderPanel.repaint();
            TimeUnit.MILLISECONDS.sleep(cycleLength);

            this.nextDay();
            currentDay++;
        }
    }
}

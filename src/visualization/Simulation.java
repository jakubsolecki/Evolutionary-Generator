package visualization;

import classes.Animal;
import classes.Grass;
import map.WorldMap;
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


    public Simulation(WorldMap map, int totalDays){
        this.map = map;
        frame = new JFrame("Evolutionary Generator");
        this.totalDays = totalDays;
        currentDay = 0;
    }


    public void nextDay(){
        map.removeDeadAnimals();
        map.moveAllAnimals();
        map.grandFeast();
        map.copulate();
        map.spawnGrass();
        map.nextDay();
    }


    // Core of the simulation. Everything happens here.
    public void simulate() throws InterruptedException{
        LinkedList<Animal> animalsList = map.getAnimalsList();
        LinkedList<Grass> grassList = map.getGrassList();
        //int animalsCounter = animalsList.size();
        float avgEnergy = 0;
        float avgAge = 0;
        int children = 0;

        // frame.setResizable( false );
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RenderPanel renderPanel = new RenderPanel(map, frame);
        // renderPanel.setSize(new Dimension(1, 1));
        frame.add(renderPanel);
        frame.setVisible(true);
        frame.setLayout(new GridLayout());

        JPanel infoPanel = new JPanel();
        JLabel mapSize = new JLabel("Map size (W x H): " + map.width + " x " + map.height);
        // TODO: add values below
        JLabel initialPopulation = new JLabel("Initial population: ");
        JLabel dayDrain = new JLabel("Daily energy drain: ");
        JLabel grassEnergy = new JLabel("Grass energy: ");

        JLabel day = new JLabel("Day: " + this.currentDay);
        JLabel animalCounter = new JLabel("Animals on map: " + animalsList.size());
        JLabel grassCounter = new JLabel("Plants on map: " + grassList.size());
        JLabel averageAge = new JLabel("Average animal age: " + avgAge);
        JLabel averageEnergy = new JLabel("Average animal energy: " + avgEnergy);
        // TODO: implement counting born and dead animals
        // JLabel animalsBorn = new JLabel("Animals born this day: " + children);
        // JLabel deadAnimals = new JLabel("Dead animals today: ");

        infoPanel.setSize((int) (0.4 * frame.getWidth()), frame.getHeight());
        infoPanel.add(mapSize);
        infoPanel.add(initialPopulation);
        infoPanel.add(dayDrain);
        infoPanel.add(grassEnergy);
        infoPanel.add(day);
        infoPanel.add(animalCounter);
        infoPanel.add(grassCounter);
        infoPanel.add(averageAge);
        infoPanel.add(averageEnergy);
        infoPanel.setLayout(new GridLayout(10, 1));
        infoPanel.setVisible(true);

        frame.add(infoPanel);

        out.println("Simulation has begun.");
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
            renderPanel.repaint();
            TimeUnit.MILLISECONDS.sleep(50);

            this.nextDay();
            currentDay++;
        }
    }
}

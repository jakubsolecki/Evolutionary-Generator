package visualization;

import world.WorldMap;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

public class Simulation {
    public WorldMap map;
    public int AdamEvePopulation;
    public int dailyGrassAmount;
    public int currentDay;
    public int totalDays;

    public JFrame frame;
    public RenderPanel renderPanel;
    public Timer timer;

    // TODO: make it work ;__; and move some code from simulate()
    public Simulation(WorldMap map, int totalDays){
        this.map = map;
        frame = new JFrame("Evolutionary Generator");
        this.totalDays = totalDays;
        currentDay = 0;


    }

    // TODO: counting alive and dead animals
    public void nextDay(){
        map.removeDeadAnimals();
        map.moveRandomAllAnimals();
        map.grandFeast();
        map.copulation();
        map.spawnGrass();
        currentDay++;
    }

    public void simulate() throws InterruptedException{
        out.println("Day: " + currentDay);
        out.println(map.toString());
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: move to the constructor
        RenderPanel renderPanel = new RenderPanel(map, frame);

        frame.setVisible(true);
        frame.setLayout(new GridLayout());
        frame.add(renderPanel);
        TimeUnit.SECONDS.sleep(1);

        for(int i = 1; i < totalDays; i++){
            this.nextDay();
            out.println("Day: " + currentDay);
            out.println(map.toString());
            renderPanel.repaint();
            TimeUnit.MILLISECONDS.sleep(20);
        }

    }

}

package visualization;

import classes.Animal;
import classes.Grass;
import classes.Vector2D;
import map.WorldMap;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;

public class RenderPanel extends JPanel {
    public WorldMap map;
    LinkedList<Animal> animalsList;
    LinkedList<Grass> grassList;
    public JFrame frame;
    private Image mapTexture;
    private Image jungleTexture;
    private Image bushTexture;
    private Image jungleBushTexture;
    private Image animalTexture;

    private int width;
    private int height;
    private int heightScale;
    private int widthScale;

    public RenderPanel(WorldMap map, JFrame frame) {
        this.map = map;
        this.frame = frame;
        this.animalsList = map.getAnimalsList();
        this.grassList = map.getGrassList();
        this.setSize((int) (0.5 * (frame.getWidth())) , (int)  ( 0.95 * frame.getHeight()));
        width = this.getWidth();
        height = this.getHeight();
        this.widthScale = Math.round(width / map.width);
        this.heightScale = Math.round(height / map.height);
        this.setBorder(BorderFactory.createLineBorder(Color.black));

    }


    @Override
    protected void paintComponent(Graphics gg){
        Graphics2D g = (Graphics2D) gg;
        super.paintComponent(g);

        //draw map
        g.setColor(new Color(216, 185, 0));
        g.fillRect(0, 0, map.width * widthScale, map.height * heightScale);

        //draw jungle
        g.setColor(new Color(89, 177, 28));
        g.fillRect(map.jungleLowerLeft.x * widthScale,
                map.jungleLowerLeft.y * heightScale,
                map.jungleWidth * widthScale,
                map.jungleHeight * heightScale);

        //draw grass
        for (Grass grass : grassList) {
            g.setColor(grass.toColor());
            int y = map.toProperPosition(grass.getPosition()).y * heightScale;
            int x = map.toProperPosition(grass.getPosition()).x * widthScale;
            g.fillRect(x, y, widthScale, heightScale);
        }

        //draw Animals
        for (Animal a : animalsList) {
            g.setColor(a.toColor());
            int y = map.toProperPosition(a.getPosition()).y * heightScale;
            int x = map.toProperPosition(a.getPosition()).x * widthScale;
            g.fillOval(x, y, widthScale, heightScale);
        }
    }
}

package visualization;

import classes.Animal;
import classes.Grass;
import world.WorldMap;
import java.awt.*;
import javax.swing.*;

public class RenderPanel extends JPanel {
    public WorldMap map;
    public Simulation simulation;
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
        // this.simulation = simulation;
        this.frame = frame;
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.widthScale = width / map.width;
        this.heightScale = height / map.height;
        this.setSize((int) ((frame.getWidth()) * 0.6), frame.getHeight());
        this.setLocation((int) (0.4 * frame.getWidth()), 0);
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.black);
        //g.setColor(Color.GREEN);
        g.setColor(new Color(170, 224, 103));
        g.fillRect(0, 0, width, height);


        //this.setLocation((int) (0.4 * frame.getWidth()), 0);



        g.setColor(new Color(0, 160, 7));
        g.fillRect(map.jungleLowerLeft.x * widthScale,
                map.jungleLowerLeft.y * heightScale,
                map.jungleWidth * widthScale,
                map.jungleHeight * heightScale);

        //draw grass
        for (Grass grass : map.getGrassList()) {
            g.setColor(grass.toColor());
            int y = map.posCurve(grass.getPosition()).y * heightScale;
            int x = map.posCurve(grass.getPosition()).x * widthScale;
            g.fillRect(x, y, widthScale, heightScale);
        }

        //draw Animals
        for (Animal a : map.getAnimalsList()) {
            g.setColor(a.toColor());
            int y = map.posCurve(a.getPosition()).y * heightScale;
            int x = map.posCurve(a.getPosition()).x * widthScale;
            g.fillOval(x, y, widthScale, heightScale);
        }
    }
}

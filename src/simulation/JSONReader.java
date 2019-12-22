package simulation;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;


public class JSONReader {
    public int width;
    public int height;
    public int jungleWidth;
    public int jungleHeight;
    public int dayCost;
    public int startEnergy;
    public int copulationEnergy;
    public int totalDays;
    public int grassEnergy;
    public int cycleLengthMilliseconds;
    public int firstAnimals;

    private JSONReader(String filename){

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filename)) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            width = (int) (long) jsonObject.get("width");
            height = (int) (long) jsonObject.get("height");
            jungleWidth = (int) (long) jsonObject.get("jungleWidth");
            jungleHeight = (int) (long) jsonObject.get("jungleWidth");
            dayCost = (int) (long) jsonObject.get("dayCost");
            startEnergy = (int) (long) jsonObject.get("startEnergy");
            copulationEnergy = (int) (long) jsonObject.get("copulationEnergy");
            totalDays = (int) (long) jsonObject.get("totalDays");
            grassEnergy = (int) (long) jsonObject.get("grassEnergy");
            cycleLengthMilliseconds = (int) (long) jsonObject.get("cycleLengthMilliseconds");
            firstAnimals = (int) (long) jsonObject.get("firstAnimals");
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONReader() {
        this("parameters.json");
    }
}

package levels;

import java.io.File;

// Kind of a wrapper class for BrickMap. Contains a BrickMap and a level number.
public class Level {
    int levelNumber;
    BrickMap brickMap;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        instantiateBrickMap();
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
        instantiateBrickMap();
    }

    public BrickMap getBrickMap() {
        return brickMap;
    }

    // Reads in a map from a CSV and transforms it into
    // a format that can be easily used to create a BrickSet.
    private void instantiateBrickMap() {
        File file;
        file = new File("src/main/java/levels/level" + levelNumber + ".csv");
        brickMap = new BrickMap(file);
    }
}

package levels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BrickMap {
    String[][] map = new String[10][10];

    public BrickMap(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int row = 0;
            String line;
            while((line = bufferedReader.readLine()) != null && row < 10) {
                String[] vals = line.split(",");
                for(int col = 0; col < 10; col++) {
                    map[row][col] = vals[col];
                }
                row++;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getValueAt(int row, int col) {
        return map[row][col];
    }
}

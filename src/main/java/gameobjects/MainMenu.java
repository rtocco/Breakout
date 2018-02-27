package gameobjects;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenu implements GameObject {

    private String[] selections = {"Level 1", "Level 2", "Level 3"};
    private int selection = 0;
    private int FRAME_WIDTH;
    private int FRAME_HEIGHT;
    private String selectedLevel = "";

    public MainMenu(int frameWidth, int frameHeight) {
        FRAME_WIDTH = frameWidth;
        FRAME_HEIGHT = frameHeight;
    }

    public void move() {}

    public void render(Graphics2D g2d) {
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));

        g2d.setColor(selections[selection].equals("Level 1") ? Color.WHITE : Color.BLACK);
        g2d.drawString("Level 1", (FRAME_WIDTH / 2) - 50, (FRAME_HEIGHT / 2) - 50);

        g2d.setColor(selections[selection].equals("Level 2") ? Color.WHITE : Color.BLACK);
        g2d.drawString("Level 2", (FRAME_WIDTH / 2) - 50, (FRAME_HEIGHT / 2));

        g2d.setColor(selections[selection].equals("Level 3") ? Color.WHITE : Color.BLACK);
        g2d.drawString("Level 3", (FRAME_WIDTH / 2) - 50, (FRAME_HEIGHT / 2) + 50);
    }

    public void checkStatus() {}

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_DOWN) {
            if(selection >= selections.length - 1) {
                return;
            }
            selection++;
        }

        if(key == KeyEvent.VK_UP) {
            if(selection == 0) {
                return;
            }
            selection--;
        }

        if(key == KeyEvent.VK_ENTER) {
            selectedLevel = selections[selection];
        }
    }

    public void keyReleased(KeyEvent e) {}

    public String getSelectedLevel() {
        return selectedLevel;
    }
}

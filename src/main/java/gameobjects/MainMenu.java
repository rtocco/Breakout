package gameobjects;

import java.awt.*;
import java.awt.event.KeyEvent;

// The menu presented to the user when they first start the game.
public class MainMenu implements GameObject {

    // The menu options.
    private String[] selections = {"Level 1", "Level 2", "Level 3"};

    // The option the user is currently focused on.
    private int selection = 0;

    private int FRAME_WIDTH;
    private int FRAME_HEIGHT;

    // The option the user has selected, if they have selected one.
    private String selectedLevel = "";

    public MainMenu(int frameWidth, int frameHeight) {
        FRAME_WIDTH = frameWidth;
        FRAME_HEIGHT = frameHeight;
    }

    public void move() {}

    // Paint the menu.
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

    // Check if the key pressed is up, down, or enter.
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Move focus one selection down.
        if(key == KeyEvent.VK_DOWN) {
            if(selection >= selections.length - 1) {
                return;
            }
            selection++;
        }

        // Move focus one selection up.
        if(key == KeyEvent.VK_UP) {
            if(selection == 0) {
                return;
            }
            selection--;
        }

        // Select the option currently focused on.
        if(key == KeyEvent.VK_ENTER) {
            selectedLevel = selections[selection];
        }
    }

    public void keyReleased(KeyEvent e) {}

    public String getSelectedLevel() {
        return selectedLevel;
    }
}

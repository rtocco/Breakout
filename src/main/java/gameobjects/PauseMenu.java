package gameobjects;

import java.awt.*;
import java.awt.event.KeyEvent;

// The game's pause menu, called when the user pressed "p".
public class PauseMenu implements GameObject {

   // The pause menu options.
   private String[] selections = {"Resume", "Exit"};

   // The option currently being focused on.
   private int selection = 0;

   // The window dimensions.
   private int FRAME_WIDTH;
   private int FRAME_HEIGHT;

   // The option that has been selected, if any has.
   private String selectedOption = "";

   public PauseMenu(int frameWidth, int frameHeight) {
      FRAME_WIDTH = frameWidth;
      FRAME_HEIGHT = frameHeight;
   }

   public void move() {}

   // Paint the pause menu.
   public void render(Graphics2D g2d) {
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));

      g2d.setColor(selections[selection].equals("Resume") ? Color.WHITE : Color.BLACK);
      g2d.drawString("Resume", (FRAME_WIDTH / 2) - 50, (FRAME_HEIGHT / 2) - 50);

      g2d.setColor(selections[selection].equals("Exit") ? Color.WHITE : Color.BLACK);
      g2d.drawString("Exit", (FRAME_WIDTH / 2) - 25, (FRAME_HEIGHT / 2));
   }

   public void checkStatus() {}

   // Check if the up, down or enter keys have been pressed.
   public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();

      // Move the focus down one option.
      if(key == KeyEvent.VK_DOWN) {
         if(selection >= selections.length - 1) {
            return;
         }
         selection++;
      }

      // Move the focus up one option.
      if(key == KeyEvent.VK_UP) {
         if(selection == 0) {
            return;
         }
         selection--;
      }

      // Select the option currently in focus.
      if(key == KeyEvent.VK_ENTER) {
         selectedOption = selections[selection];
      }
   }

   public String getSelection() {
      return selectedOption;
   }

   public void keyReleased(KeyEvent e) {}
}

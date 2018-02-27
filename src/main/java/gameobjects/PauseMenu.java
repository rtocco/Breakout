package gameobjects;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseMenu implements GameObject {
   private String[] selections = {"Resume", "Exit"};
   private int selection = 0;
   private int FRAME_WIDTH;
   private int FRAME_HEIGHT;
   private String selectedOption = "";

   public PauseMenu(int frameWidth, int frameHeight) {
      FRAME_WIDTH = frameWidth;
      FRAME_HEIGHT = frameHeight;
   }

   public void move() {}

   public void render(Graphics2D g2d) {
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));

      g2d.setColor(selections[selection].equals("Resume") ? Color.WHITE : Color.BLACK);
      g2d.drawString("Resume", (FRAME_WIDTH / 2) - 50, (FRAME_HEIGHT / 2) - 50);

      g2d.setColor(selections[selection].equals("Exit") ? Color.WHITE : Color.BLACK);
      g2d.drawString("Exit", (FRAME_WIDTH / 2) - 25, (FRAME_HEIGHT / 2));
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
         selectedOption = selections[selection];
      }
   }

   public String getSelection() {
      return selectedOption;
   }

   public void keyReleased(KeyEvent e) {}
}

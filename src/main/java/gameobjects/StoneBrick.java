package gameobjects;

import java.awt.*;

// This is a particular type of brick that cannot be destroyed.
// It is not required that the user hits this brick in order to win.
public class StoneBrick extends Brick {

   public StoneBrick(int x, int y, int width, int height) {
      super(x, y, width, height);
   }

   // Paint the brick.
   @Override
   public void render(Graphics2D g2d) {
      if(exists == false) {
         return;
      }

      g2d.setColor(Color.GRAY);
      g2d.fill(rect);

      g2d.setColor(Color.BLACK);
      g2d.draw(rect);
   }

   // The brick cannot be destroyed so we don't need to do anything here.
   @Override
   public void checkStatus() {}
}

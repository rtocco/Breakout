package gameobjects;

import java.awt.*;

public class StoneBrick extends Brick {

   public StoneBrick(int x, int y, int width, int height) {
      super(x, y, width, height);
   }

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

   @Override
   public void checkStatus() {
      Ball ball = gameObjectContainer.getBall();
   }
}

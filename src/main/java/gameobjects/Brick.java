package gameobjects;

import java.awt.*;
import utils.*;

// Should implement GameObject.
// Maybe have different kinds of bricks extend this class.
public class Brick implements GameObject {

   GameObjectContainer gameObjectContainer = GameObjectContainer.getInstance();

   int width;
   int height;
   int x;
   int y;
   Shape rect;
   boolean exists = true;

   public Brick(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      rect = new Rectangle(x, y, width, height);
   }

   public void move() {}

   public void render(Graphics2D g2d) {
      if(exists == false) {
         return;
      }

      g2d.setColor(Color.GRAY);
      g2d.fill(rect);

      g2d.setColor(Color.BLACK);
      g2d.draw(rect);
   }

   public void checkStatus() {
      Ball ball = gameObjectContainer.getBall();
      if(rect.getBounds2D().intersects(ball.getShape().getBounds2D())) {
         exists = false;
      }
   }

   public int getWidth() {
      return width;
   }

   public int getHeight() { return height; }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public boolean getExists() {
      return exists;
   }

   public Shape getShape() {
      return rect;
   }
}

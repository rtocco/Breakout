package gameobjects;

import java.awt.*;
import utils.*;

// Should implement GameObject.
// Maybe have different kinds of bricks extend this class.
public class Brick implements GameObject {

   // A singleton that holds all of the game objects.
   GameObjectContainer gameObjectContainer = GameObjectContainer.getInstance();

   // The width and height of the brick shape.
   int width;
   int height;

   // The coordinates of the brick.
   int x;
   int y;

   // The brick shape.
   Shape rect;

   boolean exists = true;

   public Brick(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;

      // Create the brick shape based on the coordinates, width, and height.
      rect = new Rectangle(x, y, width, height);
   }

   public void move() {}

   // Paint the brick object.
   public void render(Graphics2D g2d) {
      if(exists == false) {
         return;
      }

      g2d.setColor(Color.YELLOW);
      g2d.fill(rect);

      g2d.setColor(Color.BLACK);
      g2d.draw(rect);
   }

   // Check if the brick has been hit and act accordingly.
   public void checkStatus() {
      Ball ball = gameObjectContainer.getBall();
      if(rect.getBounds2D().intersects(ball.getShape().getBounds2D())) {
         exists = false;
      }
   }

   public int getWidth() {
      return width;
   }

   public int getHeight() {
      return height;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public boolean getExists() {
      return exists;
   }

   public void setExists(boolean exists) {
      this.exists = exists;
   }

   public Shape getShape() {
      return rect;
   }
}

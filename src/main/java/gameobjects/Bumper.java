package gameobjects;

import java.awt.event.KeyEvent;
import java.awt.*;
import utils.*;

// The bumper that the user moves around at the bottom of the screen.
public class Bumper implements GameObject {

   // The window dimensions
   private int FRAME_WIDTH;
   private int FRAME_HEIGHT;

   // The speed/direction of the bumper.
   private Velocity velocity;

   // The bumper coordinates
   private int x;
   private static final int y = 575;

   // The bumper dimensions.
   private static final int WIDTH = 200;
   private static final int HEIGHT = 25;

   // The shape to be painted.
   private Shape rect;

   public Bumper(int frameWidth, int frameHeight) {
      this.FRAME_WIDTH = frameWidth;
      this.FRAME_HEIGHT = frameHeight;

      // Set an initial velocity (0) and position.
      velocity = new Velocity(0, 0, true, true);
      x = 400;

      rect = new Rectangle(x, y, WIDTH, HEIGHT);
   }

   // Move the bumper based on the current
   // velocity (which is based on user input).
   public void move() {
      x += velocity.getDx();

      if(x < 0) {
         x = 0;
      } else if(x > (FRAME_WIDTH - WIDTH)) {
         x = FRAME_WIDTH - WIDTH;
      }

      rect = new Rectangle(x, y, WIDTH, HEIGHT);
   }

   // Paint the bumper.
   public void render(Graphics2D g2d) {
      g2d.setColor(Color.GREEN);
      g2d.fill(rect);
   }

   public void checkStatus() {}

   public Shape getShape() {
      return rect;
   }

   public int getX() {
      return x;
   }

   public int getWidth() {
      return WIDTH;
   }

   // Set the bumper velocity when the left
   // or right arrow keys are pressed.
   public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();

      if(key == KeyEvent.VK_LEFT) {
         velocity.setSpeed(2);
         velocity.setStraightLeft();
      }

      if(key == KeyEvent.VK_RIGHT) {
         velocity.setSpeed(2);
         velocity.setStraightRight();
      }
   }

   // Stop bumper movement when the left
   // or right arrow keys are released
   public void keyReleased(KeyEvent e) {
      int key = e.getKeyCode();

      if(key == KeyEvent.VK_LEFT) {
         velocity.freeze();
      }

      if(key == KeyEvent.VK_RIGHT) {
         velocity.freeze();
      }
   }
}

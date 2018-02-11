package gameobjects;

import java.awt.event.KeyEvent;
import java.awt.*;
import utils.*;

public class Bumper implements GameObject {

   private int FRAME_WIDTH;
   private int FRAME_HEIGHT;
   private Velocity velocity;
   private int x;
   private static final int y = 575;
   private static final int WIDTH = 200;
   private static final int HEIGHT = 25;
   private Shape rect;

   public Bumper(int frameWidth, int frameHeight) {
      this.FRAME_WIDTH = frameWidth;
      this.FRAME_HEIGHT = frameHeight;
      velocity = new Velocity(0, 0, true, true);
      x = 400;
      rect = new Rectangle(x, y, WIDTH, HEIGHT);
   }

   public void move() {
      x += velocity.getDx();

      if(x < 0) {
         x = 0;
      } else if(x > (FRAME_WIDTH - WIDTH)) {
         x = FRAME_WIDTH - WIDTH;
      }

      rect = new Rectangle(x, y, WIDTH, HEIGHT);
   }

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

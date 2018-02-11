package gameobjects;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import utils.*;

public class Ball implements GameObject {

   private int FRAME_WIDTH;
   private int FRAME_HEIGHT;
   private Velocity velocity;
   private int x;
   private int y;
   private int speed = 4;
   private boolean hitBottom = false;
   private static final int WIDTH = 25;
   private static final int HEIGHT = 25;
   private Shape circle;
   GameObjectContainer gameObjectContainer = GameObjectContainer.getInstance();

   public Ball(int frameWidth, int frameHeight) {
      this.FRAME_WIDTH = frameWidth;
      this.FRAME_HEIGHT = frameHeight;
      x = 250;
      y = 350;
      velocity = new Velocity(speed, 1, true, true);
      circle = new Ellipse2D.Double(x, y, WIDTH, HEIGHT);
   }

   public void move() {
      x += velocity.getDx();
      y += velocity.getDy();
      circle = new Ellipse2D.Double(x, y, WIDTH, HEIGHT);
   }

   public void render(Graphics2D g2d) {
      g2d.setColor(Color.RED);
      g2d.fill(circle);
   }

   private void checkIfHitBumper() {
      Bumper bumper = gameObjectContainer.getBumper();

      if(circle.getBounds2D().intersects(bumper.getShape().getBounds2D())) {
         int ballMiddleX = x + (WIDTH / 2);
         int bumperLeftX = bumper.getX();
         int bumperRightX = bumper.getX() + bumper.getWidth();
         int bumperMiddleX = bumper.getX() + (bumper.getWidth() / 2);

         if(ballMiddleX < bumperLeftX) { // The ball hit the left vertical of the bumper.
            velocity.setNegativeDx();
         } else if(ballMiddleX > bumperRightX) { // The ball hit the right vertical of the bumper.
            velocity.setPositiveDx();
         } else if(ballMiddleX < bumperMiddleX) { // The ball hit the left portion of the bumper.
            double ratio = 2.0 * ((double)(bumperMiddleX - ballMiddleX) / (double)(bumper.getWidth() / 2));
            velocity.changeXYRatio(ratio, false, false);
         } else if(ballMiddleX > bumperMiddleX) { // The ball hit the right portion of the bumper.
            double ratio = 2.0 * ((double)(ballMiddleX - bumperMiddleX) /  (double)(bumper.getWidth() / 2));
            velocity.changeXYRatio(ratio, true, false);
         } else { // The ball hit the middle of the bumper.
            velocity.setStraightUp();
         }
      }
   }

   private void checkIfHitWalls() {
      if(x >= 1000 - WIDTH) { // Right Side.
         velocity.setNegativeDx();
      }
      if(y <= 0) { // Top.
         velocity.setPositiveDy();
      }
      if(x <= 0) { // Left Side.
         velocity.setPositiveDx();
      }
      if(y >= (FRAME_HEIGHT - HEIGHT)) { // Bottom.
         velocity.freeze();
         hitBottom = true;
      }
   }

   private void checkIfHitBricks() {
      BrickSet brickSet = gameObjectContainer.getBrickSet();
      ArrayList<Brick> bricks = brickSet.bricksCloseToBall();

      for(int i = 0; i < bricks.size(); i++) {
         Brick brick = bricks.get(i);
         // If the ball and the brick intersect.
         if(circle.getBounds2D().intersects(brick.getShape().getBounds2D())) {
            int ballMiddleX = x + (WIDTH / 2);
            int ballMiddleY = y + (HEIGHT / 2);
            int brickLeftX = brick.getX();
            int brickRightX = brick.getX() + brick.getWidth();
            int brickTopY = brick.getY();
            int brickBottomY = brick.getY() + brick.getHeight();

            if(ballMiddleX < brickLeftX) { // The ball hit the left side of the brick.
               velocity.setNegativeDx();
            } else if(ballMiddleX > brickRightX) { // The ball hit the right side of the brick.
               velocity.setPositiveDx();
            } else if(ballMiddleY < brickTopY) { // The ball hit the top of the brick.
               velocity.setNegativeDy();
            } else { // The ball hit the bottom of the brick.
               velocity.setPositiveDy();
            }
         }
      }
   }

   public void checkStatus() {
      // Check if the ball hit the bumper.
      checkIfHitBumper();

      // Check if the ball hit any walls.
      checkIfHitWalls();

      // Check if the ball hit any bricks.
      checkIfHitBricks();
   }

   public Shape getShape() {
      return circle;
   }

   public int getWidth() {
      return WIDTH;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public boolean getHitBottom() {
      return hitBottom;
   }
}

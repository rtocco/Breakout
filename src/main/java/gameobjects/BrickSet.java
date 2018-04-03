package gameobjects;

import java.awt.*;
import java.util.ArrayList;
import utils.*;
import levels.BrickMap;

// The GameObject responsible for arranging a set of bricks.
public class BrickSet implements GameObject {

   // Singleton containing game objects.
   GameObjectContainer gameObjectContainer = GameObjectContainer.getInstance();

   // Matrix of bricks corresponding to the layout specified in the level.
   Brick[][] set = new Brick[10][10];

   // The constructor initializes the set matrix with bricks.
   public BrickSet(int frameWidth, int frameHeight, BrickMap brickMap) {
      int brickWidth = frameWidth / 10;
      int brickHeight = (frameHeight / 2) / 10;

      for(int row = 0; row < 10; row++) {
         for(int col = 0; col < 10; col++) {
            if(brickMap.getValueAt(row, col).equals("Normal")) {
               set[row][col] = new Brick(col * brickWidth, row * brickHeight, brickWidth, brickHeight);
            } else if(brickMap.getValueAt(row, col).equals("Stone")) {
               set[row][col] = new StoneBrick(col * brickWidth, row * brickHeight, brickWidth, brickHeight);
            } else if(brickMap.getValueAt(row, col).equals("Empty")) {
               set[row][col] = new Brick(col * brickWidth, row * brickHeight, brickWidth, brickHeight);
               set[row][col].setExists(false);
            }
         }
      }
   }

   public void move() {}

   // Paint the bricks.
   public void render(Graphics2D g2d) {
      for(int row = 0; row < 10; row++) {
         for(int col = 0; col < 10; col++) {
            set[row][col].render(g2d);
         }
      }
   }

   // Call the checkStatus method of every brick close to the ball.
   public void checkStatus() {
      Ball ball = gameObjectContainer.getBall();
      ArrayList<Brick> bricks = bricksCloseToBall();

      for(int i = 0; i < bricks.size(); i++) {
         Brick brick = bricks.get(i);
         brick.checkStatus();
      }
   }

   // Return an ArrayList of bricks that are currently close to the ball.
   // This should be improved by also accounting for Y coordinates.
   public ArrayList<Brick> bricksCloseToBall() {
      Ball ball = gameObjectContainer.getBall();
      ArrayList<Brick> bricks = new ArrayList<Brick>();

      for(int row = 0; row < 10; row++) {
         for(int col = 0; col < 10; col++) {
            Brick brick = set[row][col];
            if(brick.getX() >= (ball.getX() - brick.getWidth())) {
               if(brick.getX() <= (ball.getX() + ball.getWidth() + brick.getWidth())) {
                  if(brick.getExists()) {
                     bricks.add(brick);
                  }
               }
            }
         }
      }

      return bricks;
   }

   // Check and see if all of the bricks that can be destroyed have been.
   public boolean bricksGone() {
      for(int row = 0; row < 10; row++) {
         for(int col = 0; col < 10; col++) {
            Brick brick = set[row][col];
            if(brick.getExists() == true && !(brick instanceof StoneBrick)) {
               return false;
            }
         }
      }
      return true;
   }
}

package gameobjects;

import java.awt.*;
import java.util.ArrayList;
import utils.*;
import levels.BrickMap;

// The GameObject responsible for arranging a set of bricks.
public class BrickSet implements GameObject {

   GameObjectContainer gameObjectContainer = GameObjectContainer.getInstance();

   Brick[][] set = new Brick[10][10];

   public BrickSet(int frameWidth, int frameHeight, BrickMap brickMap) {
      int brickWidth = frameWidth / 10;
      int brickHeight = (frameHeight / 2) / 10;

      for(int row = 0; row < 10; row++) {
         for(int col = 0; col < 10; col++) {
            set[row][col] = new Brick(col * brickWidth, row * brickHeight, brickWidth, brickHeight);
            if(brickMap.getValueAt(row, col).equals("Empty")) {
               set[row][col].setExists(false);
            }
         }
      }
   }

   public void move() {}

   public void render(Graphics2D g2d) {
      for(int row = 0; row < 10; row++) {
         for(int col = 0; col < 10; col++) {
            set[row][col].render(g2d);
         }
      }
   }

   public void checkStatus() {
      Ball ball = gameObjectContainer.getBall();
      ArrayList<Brick> bricks = bricksCloseToBall();

      for(int i = 0; i < bricks.size(); i++) {
         Brick brick = bricks.get(i);
         brick.checkStatus();
      }
   }

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

   public boolean bricksGone() {
      for(int row = 0; row < 10; row++) {
         for(int col = 0; col < 10; col++) {
            Brick brick = set[row][col];
            if(brick.getExists() == true) {
               return false;
            }
         }
      }
      return true;
   }
}

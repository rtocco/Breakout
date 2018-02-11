package utils;

import gameobjects.Ball;
import gameobjects.BrickSet;
import gameobjects.Bumper;

// Singleton class that stores all of the game objects.
public class GameObjectContainer {

   // Singleton Instance.
   private static GameObjectContainer instance = null;

   private Bumper bumper = null;
   private Ball ball = null;
   private BrickSet brickSet = null;

   private GameObjectContainer() {}

   public static GameObjectContainer getInstance() {
      if(instance == null) {
         instance = new GameObjectContainer();
      }
      return instance;
   }

   public void setBumper(Bumper bumper) {
      this.bumper = bumper;
   }

   public Bumper getBumper() {
      return bumper;
   }

   public void setBall(Ball ball) {
      this.ball = ball;
   }

   public Ball getBall() {
      return ball;
   }

   public void setBrickSet(BrickSet brickSet) {
      this.brickSet = brickSet;
   }

   public BrickSet getBrickSet() {
      return brickSet;
   }
}

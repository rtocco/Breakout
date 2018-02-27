package utils;

import gameobjects.*;

// Singleton class that stores all of the game objects.
public class GameObjectContainer {

   // Singleton Instance.
   private static GameObjectContainer instance = null;

   private MainMenu mainMenu = null;
   private PauseMenu pauseMenu = null;
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

   public void setMainMenu(MainMenu mainMenu) { this.mainMenu = mainMenu; }

   public MainMenu getMainMenu() { return mainMenu; }

   public void setPauseMenu(PauseMenu pauseMenu) { this.pauseMenu = pauseMenu; }

   public PauseMenu getPauseMenu() { return pauseMenu; }

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

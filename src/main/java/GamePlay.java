
import gameobjects.*;
import utils.GameObjectContainer;
import levels.Level;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// This class is added to the Breakout JFrame. It controls all of
// the game play action, including the main menu and pause menu.
public class GamePlay extends JPanel implements ActionListener {

   // For keeping track of which screen should be displayed.
   enum Screen { MAIN_MENU, GAME, PAUSE_MENU }
   // For keeping track of what's happening in the actual game.
   enum GameState { PLAYING, WON, LOST }

   // Singleton that holds all "game objects".
   GameObjectContainer gameObjectContainer = GameObjectContainer.getInstance();

   // Length of time between timer ticks.
   private static final int DELAY = 10;

   // Used for creating motion.
   private Timer timer;

   // Window dimensions.
   private int frameWidth;
   private int frameHeight;

   // These two variables keep track of state.
   private GameState gameState = GameState.PLAYING;
   private Screen screen = Screen.MAIN_MENU;

   // Constructor
   // frameWidth: Window width.
   // frameHeight: Window height.
   public GamePlay(int frameWidth, int frameHeight) {
      addKeyListener(new TAdapter()); // Listen for keyboard input.
      setFocusable(true); // Allow this component to be focused.
      setBackground(Color.BLUE);

      this.frameWidth = frameWidth;
      this.frameHeight = frameHeight;

      // Add a main menu and pause menu to the game object singleton.
      gameObjectContainer.setMainMenu(new MainMenu(frameWidth, frameHeight));
      gameObjectContainer.setPauseMenu(new PauseMenu(frameWidth, frameHeight));

      timer = new Timer(DELAY, this);
      timer.start(); // Start the timer.
   }

   // Called when a user selects a level. Starts the game.
   // Level: An object containing info for the user-selected level.
   private void initializeGame(Level level) {
      // Create new game objects for the actual game.
      Bumper bumper = new Bumper(frameWidth, frameHeight);
      Ball ball = new Ball(frameWidth, frameHeight);
      BrickSet brickSet = new BrickSet(frameWidth, frameHeight, level.getBrickMap());

      // Add new game objects to the game object singleton.
      gameObjectContainer.setBumper(bumper);
      gameObjectContainer.setBall(ball);
      gameObjectContainer.setBrickSet(brickSet);

      // Set the state to game play.
      screen = Screen.GAME;
   }

   // Run whenever repaint is called. Re-renders different game objects,
   // which are possibly in different locations/states now.
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      if(screen == Screen.MAIN_MENU) {
         renderMainMenu(g2d);
      } else if(screen == Screen.GAME) {
         renderGame(g2d);
      } else if(screen == Screen.PAUSE_MENU) {
         renderPauseMenu(g2d);
      }

      Toolkit.getDefaultToolkit().sync(); // Ensures the display is up to date.
   }

   // Paint the main menu on the screen.
   private void renderMainMenu(Graphics2D g2d) {
      gameObjectContainer.getMainMenu().render(g2d);
   }

   // Paint the game on the screen.
   private void renderGame(Graphics2D g2d) {
      // Paint the game objects.
      gameObjectContainer.getBumper().render(g2d);
      gameObjectContainer.getBall().render(g2d);
      gameObjectContainer.getBrickSet().render(g2d);

      // If the game has been won or lost, paint a corresponding message.
      if(gameState == GameState.LOST) {
         g2d.setFont(new Font("TimesRoman", Font.PLAIN, 200));
         g2d.setColor(Color.RED);
         g2d.drawString("You Lose", 50, 250);

         g2d.setFont(new Font("TimesRoman", Font.PLAIN, 25));
         g2d.setColor(Color.WHITE);
         g2d.drawString("Press ESC to go to the main menu.", 275, 350);

      } else if(gameState == GameState.WON) {
         g2d.setFont(new Font("TimesRoman", Font.PLAIN, 200));
         g2d.setColor(Color.GREEN);
         g2d.drawString("You Win!", 50, 250);

         g2d.setFont(new Font("TimesRoman", Font.PLAIN, 25));
         g2d.setColor(Color.WHITE);
         g2d.drawString("Press ESC to go to the main menu.", 275, 350);
      }
   }

   // Paint the pause menu.
   private void renderPauseMenu(Graphics2D g2d) {
      gameObjectContainer.getPauseMenu().render(g2d);
   }

   // Called when the timer ticks, moves the game objects and repaints the scene.
   public void actionPerformed(ActionEvent e) {

      // If the user is in the main menu.
      if(screen == Screen.MAIN_MENU) {
         // These two methods currently don't do anything.
         gameObjectContainer.getMainMenu().move();
         gameObjectContainer.getMainMenu().checkStatus();

         // Check if the user has selected a level. If so, start the game.
         if(gameObjectContainer.getMainMenu().getSelectedLevel().equals("Level 1")) {
            initializeGame(new Level(1));
         } else if(gameObjectContainer.getMainMenu().getSelectedLevel().equals("Level 2")) {
            initializeGame(new Level(2));
         } else if(gameObjectContainer.getMainMenu().getSelectedLevel().equals("Level 3")) {
            initializeGame(new Level(3));
         }

         // If the user is in the game.
      } else if(screen == Screen.GAME) {

         // Move the game objects if necessary.
         gameObjectContainer.getBumper().move();
         gameObjectContainer.getBall().move();
         gameObjectContainer.getBrickSet().move();

         // Check if the game objects state should change.
         gameObjectContainer.getBumper().checkStatus();
         gameObjectContainer.getBall().checkStatus();
         gameObjectContainer.getBrickSet().checkStatus();

         // Check if the game has been lost.
         if(gameObjectContainer.getBall().getHitBottom() == true) {
            if(gameObjectContainer.getBrickSet().bricksGone() == false) {
               gameState = GameState.LOST;
            }
         }

         // Check if the game has been won.
         if(gameObjectContainer.getBrickSet().bricksGone() == true) {
            gameState = GameState.WON;
         }

         // If the user is in the pause menu.
      } else if(screen == Screen.PAUSE_MENU) {
         // These two methods currently don't do anything.
         gameObjectContainer.getPauseMenu().move();
         gameObjectContainer.getPauseMenu().checkStatus();

         // Check if the user has selected an option and act accordingly.
         if(gameObjectContainer.getPauseMenu().getSelection().equals("Resume")) {
            screen = Screen.GAME;
            gameObjectContainer.setPauseMenu(new PauseMenu(frameWidth, frameHeight));
            gameObjectContainer.getBall().unpause();
         } else if(gameObjectContainer.getPauseMenu().getSelection().equals("Exit")) {
            gameObjectContainer.setMainMenu(new MainMenu(frameWidth, frameHeight));
            gameObjectContainer.setPauseMenu(new PauseMenu(frameWidth, frameHeight));
            gameState = GameState.PLAYING;
            screen = Screen.MAIN_MENU;
         }
      }

      repaint();
   }

   // Handles keyboard events.
   private class TAdapter extends KeyAdapter {

      // Called automatically whenever the user releases a key.
      @Override
      public void keyReleased(KeyEvent e) {
         if(screen == Screen.MAIN_MENU) {
            gameObjectContainer.getMainMenu().keyReleased(e);

         } else if(screen == Screen.GAME) {
            gameObjectContainer.getBumper().keyReleased(e);
            gameObjectContainer.getBall().keyReleased(e);

         } else if(screen == Screen.PAUSE_MENU) {
            gameObjectContainer.getPauseMenu().keyReleased(e);
         }
      }

      // Called automatically whenever the user presses a key.
      @Override
      public void keyPressed(KeyEvent e) {
         if(screen == Screen.MAIN_MENU) {
            gameObjectContainer.getMainMenu().keyPressed(e);

         } else if(screen == Screen.GAME) {
            if(e.getKeyCode() == KeyEvent.VK_P) {
               screen = Screen.PAUSE_MENU;
            } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
               gameObjectContainer.setMainMenu(new MainMenu(frameWidth, frameHeight));
               gameObjectContainer.setPauseMenu(new PauseMenu(frameWidth, frameHeight));
               gameState = GameState.PLAYING;
               screen = Screen.MAIN_MENU;
            }

            gameObjectContainer.getBumper().keyPressed(e);
            gameObjectContainer.getBall().keyPressed(e);

         } else if(screen == Screen.PAUSE_MENU) {
            gameObjectContainer.getPauseMenu().keyPressed(e);
         }
      }
   }
}

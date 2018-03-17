
import gameobjects.*;
import utils.GameObjectContainer;
import levels.Level;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// This class is added to the Breakout JFrame. It controls all of
// the gameplay action, including the main menu and pause menu.
public class GamePlay extends JPanel implements ActionListener {
   // For keeping track of which screen should be displayed.
   enum Screen { MAIN_MENU, GAME, PAUSE_MENU }
   // For keeping track of what's happening in the actual game.
   enum GameState { PLAYING, WON, LOST }

   GameObjectContainer gameObjectContainer = GameObjectContainer.getInstance();
   private Timer timer;
   private static final int DELAY = 10;
   private int frameWidth;
   private int frameHeight;
   private GameState gameState = GameState.PLAYING;
   private Screen screen = Screen.MAIN_MENU;

   public GamePlay(int frameWidth, int frameHeight) {
      addKeyListener(new TAdapter()); // Listen for keyboard input.
      setFocusable(true); // This component can be focused.
      setBackground(Color.BLUE);

      this.frameWidth = frameWidth;
      this.frameHeight = frameHeight;

      gameObjectContainer.setMainMenu(new MainMenu(frameWidth, frameHeight));
      gameObjectContainer.setPauseMenu(new PauseMenu(frameWidth, frameHeight));

      timer = new Timer(DELAY, this);
      timer.start(); // Start the timer.
   }

   private void initializeGame(Level level) {
      // Create new game objects.
      Bumper bumper = new Bumper(frameWidth, frameHeight);
      Ball ball = new Ball(frameWidth, frameHeight);
      BrickSet brickSet = new BrickSet(frameWidth, frameHeight, level.getBrickMap());

      // Add new game objects.
      gameObjectContainer.setBumper(bumper);
      gameObjectContainer.setBall(ball);
      gameObjectContainer.setBrickSet(brickSet);

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

   private void renderMainMenu(Graphics2D g2d) {
      gameObjectContainer.getMainMenu().render(g2d);
   }

   private void renderGame(Graphics2D g2d) {
      gameObjectContainer.getBumper().render(g2d);
      gameObjectContainer.getBall().render(g2d);
      gameObjectContainer.getBrickSet().render(g2d);

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

   private void renderPauseMenu(Graphics2D g2d) {
      gameObjectContainer.getPauseMenu().render(g2d);
   }

   // Called when the timer ticks, moves the game objects and repaints the scene.
   public void actionPerformed(ActionEvent e) {
      if(screen == Screen.MAIN_MENU) {
         gameObjectContainer.getMainMenu().move();
         gameObjectContainer.getMainMenu().checkStatus();

         if(gameObjectContainer.getMainMenu().getSelectedLevel().equals("Level 1")) {
            initializeGame(new Level(1));
         } else if(gameObjectContainer.getMainMenu().getSelectedLevel().equals("Level 2")) {
            initializeGame(new Level(2));
         } else if(gameObjectContainer.getMainMenu().getSelectedLevel().equals("Level 3")) {
            initializeGame(new Level(3));
         }

      } else if(screen == Screen.GAME) {
         gameObjectContainer.getBumper().move();
         gameObjectContainer.getBall().move();
         gameObjectContainer.getBrickSet().move();

         gameObjectContainer.getBumper().checkStatus();
         gameObjectContainer.getBall().checkStatus();
         gameObjectContainer.getBrickSet().checkStatus();

         if(gameObjectContainer.getBall().getHitBottom() == true) {
            if(gameObjectContainer.getBrickSet().bricksGone() == false) {
               gameState = GameState.LOST;
            }
         }

         if(gameObjectContainer.getBrickSet().bricksGone() == true) {
            gameState = GameState.WON;
         }

      } else if(screen == Screen.PAUSE_MENU) {
         gameObjectContainer.getPauseMenu().move();
         gameObjectContainer.getPauseMenu().checkStatus();

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

   private class TAdapter extends KeyAdapter {
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

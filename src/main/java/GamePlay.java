
import gameobjects.Ball;
import gameobjects.BrickSet;
import gameobjects.Bumper;
import sun.util.logging.PlatformLogger;
import utils.GameObjectContainer;
import levels.Level;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// This class is added to the src.main.java.Breakout JFrame.
public class GamePlay extends JPanel implements ActionListener {

   GameObjectContainer gameObjectContainer = GameObjectContainer.getInstance();
   private Timer timer;
   private static final int DELAY = 10;

   public GamePlay(int frameWidth, int frameHeight) {
      addKeyListener(new TAdapter()); // Listen for keyboard input.
      setFocusable(true); // This component can be focused.
      setBackground(Color.BLUE);

      // For now, we just set the level number to 1.
      Level level = new Level(1);

      // Create new game objects.
      Bumper bumper = new Bumper(frameWidth, frameHeight);
      Ball ball = new Ball(frameWidth, frameHeight);
      BrickSet brickSet = new BrickSet(frameWidth, frameHeight, level.getBrickMap());

      // Add new game objects.
      gameObjectContainer.setBumper(bumper);
      gameObjectContainer.setBall(ball);
      gameObjectContainer.setBrickSet(brickSet);

      timer = new Timer(DELAY, this);
      timer.start(); // Start the timer.
   }

   // Run whenever repaint is called. Rerenders different game objects,
   // which are possibly in different locations/states now.
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      gameObjectContainer.getBumper().render(g2d);
      gameObjectContainer.getBall().render(g2d);
      gameObjectContainer.getBrickSet().render(g2d);

      if(gameObjectContainer.getBall().getHitBottom() == true) {
         if(gameObjectContainer.getBrickSet().bricksGone() == false) {
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 200));
            g2d.setColor(Color.RED);
            g2d.drawString("You Lose", 50, 250);
         }
      }

      if(gameObjectContainer.getBrickSet().bricksGone() == true) {
         g2d.setFont(new Font("TimesRoman", Font.PLAIN, 200));
         g2d.setColor(Color.GREEN);
         g2d.drawString("You Win!!!", 25, 250);
      }

      Toolkit.getDefaultToolkit().sync(); // Ensures the display is up to date.
   }

   // Called when the timer ticks, moves the game objects and repaints the scene.
   public void actionPerformed(ActionEvent e) {
      gameObjectContainer.getBumper().move();
      gameObjectContainer.getBall().move();
      gameObjectContainer.getBrickSet().move();

      gameObjectContainer.getBumper().checkStatus();
      gameObjectContainer.getBall().checkStatus();
      gameObjectContainer.getBrickSet().checkStatus();

      repaint();
   }

   private class TAdapter extends KeyAdapter {
      @Override
      public void keyReleased(KeyEvent e) {
         gameObjectContainer.getBumper().keyReleased(e);
      }

      @Override
      public void keyPressed(KeyEvent e) {
         gameObjectContainer.getBumper().keyPressed(e);
      }
   }
}

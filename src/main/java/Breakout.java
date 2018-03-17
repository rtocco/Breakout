
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Breakout extends JFrame {

   // Window dimensions.
   public static final int FRAME_WIDTH = 1000;
   public static final int FRAME_HEIGHT = 650;

   public Breakout() {
      // Add our custom JPanel. Note that we pass in the window dimensions so
      // the game objects will know the game boundaries.
      add(new GamePlay(FRAME_WIDTH, FRAME_HEIGHT));

      // Set the window dimensions.
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
      setResizable(false);

      // Set a title that will appear in the top bar.
      setTitle("Breakout");
      // Set window to be located in the center of the screen.
      setLocationRelativeTo(null);
      // Exit the application using the System exit method.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            JFrame jFrame = new Breakout();
            jFrame.setVisible(true); // Show the window.
         }
      });
   }
}

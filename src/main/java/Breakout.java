
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Breakout extends JFrame {

   // Window dimensions.
   public static final int FRAME_WIDTH = 1000;
   public static final int FRAME_HEIGHT = 650;

   public Breakout() {
      add(new GamePlay(FRAME_WIDTH, FRAME_HEIGHT)); // Add our custom JPanel.

      setSize(FRAME_WIDTH, FRAME_HEIGHT); // Set window dimensions.
      setResizable(false);

      setTitle("src.main.java.Breakout");
      setLocationRelativeTo(null); // Set window to be located in the center of the screen.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application using the System exit method.
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

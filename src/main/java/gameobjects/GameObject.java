package gameobjects;

import java.awt.*;

public interface GameObject {

   // Move the object to another position if necessary.
   public void move();

   // Paint the game object.
   public void render(Graphics2D g2d);

   // See if the object's state needs to change (velocity, etc.)
   public void checkStatus();
}

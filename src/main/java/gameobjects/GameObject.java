package gameobjects;

import java.awt.*;

public interface GameObject {

   public void move();

   public void render(Graphics2D g2d);

   public void checkStatus();
}

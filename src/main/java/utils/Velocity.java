package utils;

public class Velocity {

   private int speed;
   private double dx;
   private double dy;

   public Velocity(int speed, double xyRatio, boolean positiveX, boolean positiveY) {
      this.speed = speed;
      changeXYRatio(xyRatio, positiveX, positiveY);
   }

   public void setSpeed(int speed) {
      this.speed = speed;
   }

   public double getDx() {
      return dx;
   }

   public double getDy() {
      return dy;
   }

   public void setNegativeDx() {
      dx = dx < 0 ? dx : -dx;
   }

   public void setPositiveDx() {
      dx = dx > 0 ? dx : -dx;
   }

   public void setNegativeDy() {
      dy = dy < 0 ? dy : -dy;
   }

   public void setPositiveDy() {
      dy = dy > 0 ? dy : -dy;
   }

   public void freeze() {
      dx = 0;
      dy = 0;
   }

   public void setStraightUp() {
      dx = 0;
      dy = -speed;
   }

   public void setStraightLeft() {
      dx = -speed;
      dy = 0;
   }

   public void setStraightRight() {
      dx = speed;
      dy = 0;
   }

   public void setStraightDown() {
      dx = 0;
      dy = speed;
   }

   public void changeXYRatio(double xyRatio, boolean positiveX, boolean positiveY) {
      double opposite = 1;
      double adjacent = xyRatio;
      double angle = Math.atan(opposite / adjacent);
      double xSpeed = Math.cos(angle) * speed;
      double ySpeed = Math.sin(angle) * speed;
      dx = positiveX ? xSpeed : -xSpeed;
      dy = positiveY ? ySpeed : -ySpeed;
   }
}

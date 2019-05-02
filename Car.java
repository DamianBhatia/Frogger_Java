import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Car extends GameObject {

  Random random;
  
  //Constructor
  public Car(int x, int y, ID id) {
    super(x, y, id);
   
    //Movement to left and right
    random = new Random();
    setSpeedX(random.nextInt(5) + -1);
    //If zero give another value
    if(getSpeedX() == 0) {
      setSpeedX(random.nextInt(5) + 1);
    }
    
  }
  
  public void tick() {
    x+=speedX;
    
    //Reset position once at edge of screen
    if(x >= Game.WIDTH && speedX>0) setX(0);
    if(x <= 0 && speedX < 0) setX(Game.WIDTH);
  }
  
  public void render(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect(x, y, 64, 32);
  }
  
}
import java.awt.Graphics;
import java.awt.Color;

public class Player extends GameObject {
  
  //Constructor
  public Player(int x, int y, ID id) {
    super(x, y, id);
    
    setSpeedX(1);
  
  }
  
  //Updates player functionality
  public void tick() {
    x+= speedX;
    y += speedY;
  }
  
  //Redraws graphics of player
  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(x, y, 32, 32);
  }
  
}
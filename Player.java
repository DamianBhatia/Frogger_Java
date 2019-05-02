import java.awt.Graphics;
import java.awt.Color;

public class Player extends GameObject {
  
  //Constructor
  public Player(int x, int y, ID id) {
    super(x, y, id);  
  }
  
  //Updates player functionality
  public void tick() {
    x+=speedX;
    y+=speedY;
    
    x = Game.clamp(x, 0, Game.WIDTH - 37);
    y = Game.clamp(y, 0, Game.HEIGHT - 60);
  }
  
  //Redraws graphics of player
  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(x, y, 32, 32);
  }
  
}
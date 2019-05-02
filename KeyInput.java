import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
  
  private Handler handler;
  
  public KeyInput(Handler handler) {
    this.handler = handler;
  }
  
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    
    //Loop through every object
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      
      //Different action based off of key pressed and GameObject
      if(tempObject.getID() == ID.Player){
        if(key == KeyEvent.VK_UP) tempObject.setSpeedY(-5);
        if(key == KeyEvent.VK_DOWN) tempObject.setSpeedY(5);
        if(key == KeyEvent.VK_LEFT) tempObject.setSpeedX(-5);
        if(key == KeyEvent.VK_RIGHT) tempObject.setSpeedX(5);
      }
    }
  }
  
  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    
    //Loop through every object
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      
      //Different action based off of key pressed and GameObject
      if(tempObject.getID() == ID.Player){
        if(key == KeyEvent.VK_UP) tempObject.setSpeedY(0);
        if(key == KeyEvent.VK_DOWN) tempObject.setSpeedY(0);
        if(key == KeyEvent.VK_LEFT) tempObject.setSpeedX(0);
        if(key == KeyEvent.VK_RIGHT) tempObject.setSpeedX(0);
      }
    }
  }
}
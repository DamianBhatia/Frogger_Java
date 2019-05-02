import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
  
  //Stores all gameObjects in game
  LinkedList<GameObject> object = new LinkedList<GameObject>();

  //Updates gameObjects
  public void tick() {
    //Goes through every object in list and updates it
    for(int i = 0; i < object.size(); i++) {
      //tempObject could be player or enemy etc.
      GameObject tempObject = object.get(i);
      
      //Calls the child class tick function.
      tempObject.tick();
    }
  }
  
  //Draws each object/ updates its graphics
  public void render(Graphics g) {
    for(int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);
      
      tempObject.render(g);
    }
  }
  
  //Add passed object to the LinkedList
  public void addObject(GameObject object) {
    this.object.add(object);
  }
  
  //Remove passed object from LinkedList
  public void removeObject(GameObject object) {
    this.object.remove(object);
  }
  
  
}
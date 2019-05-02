import java.awt.Graphics;

//Every thing in game is GameObject
//All thins in game inherit GameObject
public abstract class GameObject {
  
  protected int x, y;
  protected ID id;
  protected int speedX, speedY;
  
  //Constructor
  public GameObject(int x, int y, ID id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }
  
  //Used by instances of GameObject Class
  public abstract void tick();
  public abstract void render(Graphics g);
  
  //Getters and Setters
  public void setX(int x) {
    this.x = x;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public void setID(ID id) {
    this.id = id;
  }
  
  public ID getID() {
    return this.id;
  }
  
  public void setSpeedX(int speedX) {
    this.speedX = speedX;
  }
  
  public void setSpeedY(int speedY) {
    this.speedY = speedY;
  }
  
  public int getSpeedX() {
    return this.speedX;
  }
  
  public int getSpeedY() {
    return this.speedY;
  }
  
}
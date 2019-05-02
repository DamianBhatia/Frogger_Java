import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;

public class Game extends Canvas implements Runnable {
  
  //Constants for dimensions of frame
  private static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
  
  //Dealing with Threads
  private Thread thread;
  private boolean running = false;
  
  //Creates object Handler
  private Handler handler;
  
  //Constructor
  public Game() {
    handler = new Handler();
    
    new Window(WIDTH, HEIGHT, "Frogger", this);    
    
    //Adding players to game
    handler.addObject(new Player(100, 100, ID.Player));
    handler.addObject(new Player(200, 200, ID.Player));
  }
  
  //Called from window class and starts the thread
  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }
  
  //Safely stops the thread
  public synchronized void stop() {
    try{
      thread.join();
      running = false;
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  //Game Loop
  public void run() {
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while(running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while(delta >= 1) {
        //Calls tick
        tick();
        delta--;
      }
      if(running)
        //Calls render
        render();
      frames++;
      
      if(System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    stop();
  }
  
  //Update objects
  private void tick() {
    handler.tick();
  }
  
  /* Being called over and over by game loop
   * Draws everything to screen
   */
  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    //If bufferstrategy doesnt exist make one
    if(bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    
    //Get graphics object which acts as pen that draws
    Graphics g = bs.getDrawGraphics();
    
    //Actual Drawing
    g.setColor(Color.BLACK);
    g.fillRect(0,0,WIDTH,HEIGHT);
    
    handler.render(g);
    
    //Gets rid of pen to prevent errors
    //Puts buffer screen in queue to be displayed
    g.dispose();
    bs.show();
  }
  
  public static void main(String args[]) {
    new Game();
  }
}
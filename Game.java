import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Game extends Canvas implements Runnable {
  
  //Constants for dimensions of frame
  public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
  
  //Dealing with Threads
  private Thread thread;
  private boolean running = false;
  
  //Creates object Handler
  private Handler handler;
  
  private Random random;
  
  //Constructor
  public Game() {
    handler = new Handler();
    random = new Random();
    this.addKeyListener(new KeyInput(handler));
    
    new Window(WIDTH, HEIGHT, "Frogger", this);    
    
    //Adding player to game
    handler.addObject(new Player(WIDTH/2-32, HEIGHT, ID.Player));
    
    //Adding cars to game
    for(int i = 0; i < 8; i++) {
    handler.addObject(new Car(0, random.nextInt(400), ID.Car));
    }
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
        //System.out.println("FPS: " + frames);
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
  
  //Keep GameObject within screen boundaries
  public static int clamp(int var, int min, int max) {
    if(var >= max)
      return var = max;
    else if(var <= min)
      return var = min;
    else
      return var;
  }
  
  public static void main(String args[]) {
    new Game();
  }
}
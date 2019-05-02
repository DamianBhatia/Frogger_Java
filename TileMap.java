import java.io.*;
import java.awt.*;

public class TileMap {

  private int x;
  private int y;
  
  private int tileSize;
  public int[][] map;
  private int mapWidth;
  private int mapHeight;
  
  public TileMap(String s, int tileSize) {
    this.tileSize = tileSize;
    
    //Loading in map
    try {
      //File Reader
      BufferedReader br = new BufferedReader(new FileReader(s));
      
      //Reads file for info
      mapWidth = Integer.parseInt(br.readLine());
      mapHeight = Integer.parseInt(br.readLine());
      map = new int[mapHeight][mapWidth];
      
      //Seperate each block by spaces
      String delimiter = " ";
      for(int row = 0; row < mapHeight; row++) {
        //Read line and break up 0 and 1 into array
        String line = br.readLine();
        String[] tokens = line.split(delimiter);
        for(int col = 0; col < mapWidth; col++) {
          //Store value in array of map
          map[row][col] = Integer.parseInt(tokens[col]);
        }
      }  
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  public void render(Graphics g) {
    //Go through each location and fill based on color
    for(int row = 0; row < mapHeight; row++) {
      for(int col = 0; col < mapWidth; col++) {
        int loc = map[row][col];
        
        switch(loc) {
          case 0:
            g.setColor(Color.GREEN);
            break;
          case 1:
            g.setColor(Color.GRAY);
            break;
          case 2:
            g.setColor(Color.BLUE);
            break;
        }
        
        //Get the squares to make a grid like map
        g.fillRect(x + col * tileSize, y + row * tileSize, tileSize, tileSize);
      }
    }
  }
  
}
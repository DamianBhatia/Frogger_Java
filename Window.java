import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

//Creates the JFrame Window which holds the game
public class Window extends Canvas {

	//Constructor
	public Window(int width, int height, String title, Game game) {
		//Creating Window
		JFrame frame = new JFrame(title);

		//Sets size and makes sure user cannot change window size
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//Puts in middle of screen
		frame.setLocationRelativeTo(null);
		//Adds game to frame
		frame.add(game);
		frame.setVisible(true);
		//Starts game
		game.start();

	}

}
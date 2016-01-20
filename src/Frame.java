import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Sets parameters of our window (size, icon etc.).
 * @author Patryk Przekwas
 *
 */
public class Frame extends Canvas{

	private static final long serialVersionUID = 1L;
	ImageIcon icon = new ImageIcon("Textures/h3.png");
	
	/**
	 * Frame constructor.
	 * @param width - width of the window
	 * @param height - height of the window
	 * @param title - window title (up on the window bar)
	 * @param game - handler to Game object
	 */
	public Frame(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}

}

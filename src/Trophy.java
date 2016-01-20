import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Trophy object. Scoring trophy equals getting points.
 * @author Patryk Przekwas
 *
 */
public class Trophy extends GameObject {
	
	private Image trophy = new ImageIcon("Textures/trophy1.png").getImage();
	
	/**
	 * Trophy constructor.
	 * @param x - X coordinate
	 * @param y - Y coordinate
	 * @param id - object ID
	 */
	public Trophy(float x, float y, ID id) {
		super(x, y, id);
	}
	
	/**
	 * Returns borders of our object. 
	 * Used later to detect collisions using intersection.
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	/**
	 *  Empty method.
	 */
	@Override
	public void logic() {		
	}
	
	/**
	 * Renders trophy.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(trophy,(int)x,(int)y,null);
	}

}

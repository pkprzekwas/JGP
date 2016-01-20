import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Class which describes properties of a simple foe.
 * @author Patryk Przekwas
 *
 */
public class FirstFoe extends GameObject {
	
	private Handler handler;
	private Image h2 = new ImageIcon("Textures/h2.png").getImage();
	
	/**
	 * Constructor - sets handler, and velocity to constant.
	 * @param x - coordinate
	 * @param y - coordinate 
	 * @param id - object ID
	 * @param handler - game handler
	 */
	public FirstFoe(float x, float y, ID id, Handler handler) {
		super(x, y, id);	
		this.handler = handler;
		
		velX = 4;
		velY = 4;
	}
	
	/**
	 * Returns borders of our object. 
	 * Used later to detect collisions using intersection.
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 28, 28);
	}

	/**
	 * Constantly counts our coordinates (velocity is constant) and changes 
	 * direction of object in case of bouncing edge.
	 */
	@Override
	public void logic() {
		x += velX;
		y += velY;
		
		// Bouncing off the edges
		if(y <= 0 || y >= Game.HEIGHT - 64) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
	}

	/**
	 * Renders our foe. 
	 */
	@Override
	public void render(Graphics g) {
		//g.setColor(Color.blue);
		//g.fillRect((int)x, (int)y, 32, 32);
		g.drawImage(h2,(int)x,(int)y,null);

	}

}

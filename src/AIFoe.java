import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class AIFoe extends GameObject {
	
	private Handler handler;
	private GameObject player;
	private Image h3 = new ImageIcon("Textures/h3.png").getImage();
	
	/**
	 * Class constructor where we initialize object coordinates, ID and handler.
	 * @param x
	 * @param y
	 * @param id
	 * @param handler
	 */
	public AIFoe(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i=0; i<handler.object.size(); i++){
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
	}
	
	/**
	 * Returns borders of our object. 
	 * We use this method later to detect collisions using intersection.
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 28, 28);
	}

	@Override
	public void logic(){
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 1;
		float diffY = y - player.getY() - 1;
		// Using Pythagorean theorem to count the distance in straight line
		float distance = (float)Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		velX = (float)((-2.0/distance) * diffX);
		velY = (float)((-2.0/distance) * diffY);
	}

	@Override
	public void render(Graphics g){
		g.drawImage(h3,(int)x,(int)y,null);

	}

}

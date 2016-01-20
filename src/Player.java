import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
/**
 * Class which represents main object (our main character)
 * @author Patryk Przekwas
 *
 */
public class Player extends GameObject{

	private Random r = new Random();
	private Handler handler;
	private Properties properties;
	private Image hero = new ImageIcon("Textures/hero.png").getImage();
	
	/**
	 * Player constructor
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param id - object ID
	 * @param handler - game handler
	 * @param properties - game properties
	 */
	public Player(float x, float y, ID id, Handler handler, Properties properties) {
		super(x, y, id);
		this.handler = handler;
		this.properties = properties;
	}
	
	/**
	 * Returns borders of our object. 
	 * We use this method later to detect collisions using intersection.
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 38, 50);
	}
	
	/**
	 * Detects collision with other objects.
	 */
	private void collision(){
		for(int i=0; i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.BasicEnemy || 
					tempObject.getId() == ID.FastEnemy || 
					tempObject.getId() == ID.SmartEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					
					Properties.DAMAGE += 2;
				}
			}else if(tempObject.getId() == ID.Trophy){
				if(getBounds().intersects(tempObject.getBounds())){
					//collision with trophy (removing / points++)
					handler.removeObject(tempObject);
					handler.addObject(new Trophy(r.nextInt(Game.WIDTH-64),
							r.nextInt(Game.HEIGHT-64), ID.Trophy));
					properties.setPoints(properties.getPoints()+1);
				}
			}else if(tempObject.getId() == ID.HealthPoint){
					if(getBounds().intersects(tempObject.getBounds())){
						//collision with health point (removing / damage--)
						handler.removeObject(tempObject);
						Properties.DAMAGE -= 15; 
						}
					}
			}
	}

	/**
	 * Constantly counts our coordinates and velocity. 
	 * Checks collisions with other objects.
	 */
	@Override
	public void logic() {
		x += velX;
		y += velY;
 		x = Game.clamper(x, -5, Game.WIDTH-42 );
		y = Game.clamper(y, -10 , Game.HEIGHT-80);	
		collision();
	}

	/**
	 * Renders our object.
	 */
	@Override
	public void render(Graphics g) {	
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(hero,(int)x,(int)y,null);
	}
}

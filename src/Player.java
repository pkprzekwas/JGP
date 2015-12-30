import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Player extends GameObject{
	/**
	 * Class which represents main object (our main character)
	 */
	private Random r = new Random();
	private Handler handler;
	private Properties properties;
	private Image h1 = new ImageIcon("Textures/h1.png").getImage();
	
	public Player(float x, float y, ID id, Handler handler, Properties properties) {
		super(x, y, id);
		this.handler = handler;
		this.properties = properties;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
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
			}
		}
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamper(x, 0, Game.WIDTH-38);
		y = Game.clamper(y, 0, Game.HEIGHT-60);
		
		collision();
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		//g2d.setColor(Color.white);
		g2d.drawImage(h1,(int)x,(int)y,null);
		//g.fillRect((int)x, (int)y, 32, 32);

	}
	

}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject {
	
	private Handler handler;
	
	public FastEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 2;
		velY = 9;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		// Bouncing off the edges
		if(y <= 0 || y >= Game.HEIGHT - 64) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 32, 32);
	}

}

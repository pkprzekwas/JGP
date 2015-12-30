import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Trophy extends GameObject {
	
	private Image trophy = new ImageIcon("Textures/trophy1.png").getImage();
	
	public Trophy(float x, float y, ID id) {
		super(x, y, id);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(trophy,(int)x,(int)y,null);
	}

}

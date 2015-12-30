import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.annotation.Resource;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private Properties properties;
	private boolean[] keyDown = new boolean[4];
	private boolean isNamed;
	private String name;
	private boolean startBlow = true;
	private int signCounter = 0;

	
	public KeyInput(Handler handler, Properties properties){
		this.handler = handler;
		this.properties = properties;
		isNamed = false;
		name = "";
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(Game.gameState == STATE.Setup && !isNamed){
			if(key>47 && key<91 && signCounter < 11){
				name = name + (char)key;
				properties.setUserName(name);
				this.signCounter++;
			}
			if(key == KeyEvent.VK_ENTER) { isNamed = true; Game.gameState = STATE.Menu; }
			if(key == KeyEvent.VK_BACK_SPACE) { 
				if(this.signCounter > 0){
					name = name.substring(0, name.length()-1);
					properties.setUserName(name);
					this.signCounter--;}
				}
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			// Here we check if our object is a Player
			if(tempObject.getId() == ID.Player){
				
				if(key == KeyEvent.VK_SPACE) { 
					tempObject.setVelY(-5);
					if(startBlow){
						properties.startBlowing();
						startBlow = false;
					}
					keyDown[0] = true;}
				if(key == KeyEvent.VK_LEFT) { tempObject.setVelX(-5); keyDown[2] = true; }
				if(key == KeyEvent.VK_RIGHT) { tempObject.setVelX(5); keyDown[3] = true; }
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			// Here we check if our object is a Player
			if(tempObject.getId() == ID.Player){
				
				if(key == KeyEvent.VK_SPACE) {
					properties.stopBlowing();
					properties.countBlowInterval();
					startBlow = true;
					keyDown[0] = false; }
				if(key == KeyEvent.VK_LEFT) keyDown[2] = false;
				if(key == KeyEvent.VK_RIGHT) keyDown[3] = false;
			
				//vertical movement (Gravity)
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(5);
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
			
			if(key == KeyEvent.VK_ESCAPE)
				System.exit(1);
				
		}
	}
	

}

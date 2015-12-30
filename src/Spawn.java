import java.awt.Point;
import java.util.Random;

public class Spawn {

	private Handler handler;
	private Properties properties;
	private Random r = new Random();
	private boolean trigger;
	
	public Spawn(Handler handler, Properties properties){
		this.handler = handler;
		this.properties = properties;
		properties.startTimer();
	}
	
	public void tick(){
		if(properties.getPoints() >= 5){
			properties.setPoints(0);
			properties.setLevel(properties.getLevel()+1);
			trigger = true;
		}

		if(properties.getLevel()==2 && trigger){
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.BasicEnemy, handler));
			trigger = false;
		}else if (properties.getLevel()==3 && trigger){
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.BasicEnemy, handler));
			trigger = false;
		}else if (properties.getLevel()==4 && trigger){
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.FastEnemy, handler));
			trigger = false;
		}else if (properties.getLevel()==5 && trigger){
			handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.SmartEnemy, handler));	
			trigger = false;
		}
		
	}
		
}

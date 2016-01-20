import java.awt.Point;
import java.util.Random;

/**
 * Class responsible for spawning objects depending on current level.
 * @author Patryk Przekwas
 *
 */
public class Spawn {

	private Handler handler;
	private Properties properties;
	private Random r = new Random();
	private boolean trigger;
	private int i = 6;
	
	/**
	 * Spawn constructor.
	 * @param handler - game handler 
	 * @param properties - game properties
	 */
	public Spawn(Handler handler, Properties properties){
		this.handler = handler;
		this.properties = properties;
		properties.startTimer();
	}
	
	/**
	 * Spawns objects depending on current level.
	 * Counts points and increases level.
	 */
	public void logic(){
		if(properties.getPoints() >= 5){
			properties.setPoints(0);
			properties.setLevel(properties.getLevel()+1);
			trigger = true;
		}
		if(properties.getLevel()==2 && trigger){
			handler.addObject(new FirstFoe(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.BasicEnemy, handler));
			handler.addObject(new HealthPoint(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.HealthPoint));
			trigger = false;
		}else if (properties.getLevel()==3 && trigger){
			handler.addObject(new FirstFoe(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.BasicEnemy, handler));
			handler.addObject(new HealthPoint(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.HealthPoint));
			trigger = false;
		}else if (properties.getLevel()==4 && trigger){
			handler.addObject(new SecondFoe(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.FastEnemy, handler));
			handler.addObject(new HealthPoint(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.HealthPoint));
			trigger = false;
		}else if (properties.getLevel()==5 && trigger){
			handler.addObject(new AIFoe(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.SmartEnemy, handler));	
			handler.addObject(new HealthPoint(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.HealthPoint));
			trigger = false;
		}else if (properties.getLevel()==i && trigger){
			i +=1;
			trigger = false;
			handler.addObject(new HealthPoint(r.nextInt(Game.WIDTH-64),
					r.nextInt(Game.HEIGHT-64), ID.HealthPoint));
		}
		
	}
		
}

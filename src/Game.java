import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800, HEIGHT = 600;
	private Thread thread;
	private boolean running;
	
	private Handler handler;
	private Properties properties;
	private Spawn spawner;
	private Menu menu;
	private DataFormat data;
	
	public static STATE gameState = STATE.Setup;
	
	public Game(){
		// Creating our handler to add objects
		handler = new Handler();
		properties = new Properties();
		this.addKeyListener(new KeyInput(handler, properties));
		data = new DataFormat(properties.getUserName(),
				properties.getGameTime(), properties.getBlowTime(), properties.score());
		menu = new Menu(handler, properties, data); 
		this.addMouseListener(menu);
		
		new Frame(WIDTH, HEIGHT, "Pixel", this);
		
		spawner = new Spawn(handler, properties);
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		/**
		 * Main loop of game. Popular pattern. Magic!
		 */
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0; 
			}
		}
		stop();
	}
	
	private void tick(){
		handler.tick();
		if(gameState == STATE.Game)
		{
			boolean saved = false;
			properties.tick();
			spawner.tick();
			if(properties.DAMAGE >= 100){
				// Game Over
				handler.clearObjects();
				gameState = STATE.End;
				properties.stopTimer();
				// Creating data to save
				if(!saved){
					saved = true;
					properties.stopTimer();
					data.updateData(properties.getUserName(), properties.getGameTime(), 
							properties.getBlowTime(), properties.score());
					ResourceManager.save(data);
					ResourceManager.read(data);
				}
			}
		}else if(gameState == STATE.Menu
				|| gameState == STATE.Help
				|| gameState == STATE.End
				|| gameState == STATE.Setup){ 
			menu.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		// Drawing background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gameState == STATE.Game)
		{
			properties.render(g);		
		}else if(gameState == STATE.Menu
				|| gameState == STATE.End
				|| gameState == STATE.Help
				|| gameState == STATE.Setup){ 
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	public static float clamper(float val, float min, float max){
		if(val >= max) 
			return max;
		else if(val <= min) 
			return min;
		else
			return val;
	}
	
	public static void main(String args[]){
		new Game();
	}
	
}

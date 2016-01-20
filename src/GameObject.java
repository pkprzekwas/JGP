import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Abstract class used as a scaffold for objects in game.
 * @author Patryk
 *
 */
public abstract class GameObject {
	/**
	 * Scaffold for every object
	 */
	protected float x, y; 
	protected ID id;
	protected float velX, velY;
	
	/**
	 * Constructor
	 * @param x - coordinate
	 * @param y - coordinate
	 * @param id - object ID
	 */
	public GameObject(float x, float y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	/**
	 * Object logic
	 */
	public abstract void logic();
	
	/**
	 * Object rendering
	 * @param g
	 */
	public abstract void render(Graphics g);
	
	/**
	 * Returns borders of our object. 
	 * Used later to detect collisions using intersection.
	 * @return bounds
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * X coordinate setter.
	 * @param x
	 */
	public void setX(float x){
		this.x = x;
	}
	
	/**
	 * Y coordinate setter.
	 * @param y
	 */
	public void setY(float y){
		this.y = y;
	}
	
	/**
	 * X coordinate getter.
	 * @return x coordinate
	 */
	public float getX(){
		return this.x;
	}
	
	/**
	 * Y coordinate getter.
	 * @return y coordinate
	 */
	public float getY(){
		return this.y;
	}
	
	/**
	 * Object ID setter.
	 * @param id - object ID
	 */
	public void setId(ID id){
		this.id = id;
	}
	
	/**
	 * Object ID getter.
	 * @return object ID
	 */
	public ID getId(){
		return this.id;
	}
	
	/**
	 * Velocity in X direction setter.
	 * @param velX - velocity in X direction
	 */
	public void setVelX(float velX){
		this.velX = velX;
	}
	
	/**
	 * Velocity in Y direction setter.
	 * @param velY - velocity in Y direction
	 */
	public void setVelY(float velY){
		this.velY = velY;
	}
	
	/**
	 * Velocity in X direction setter.
	 * @return velX - velocity in X direction
	 */
	public float getVelX(){
		return this.velX;
	}
	
	/**
	 * Velocity in Y direction setter.
	 * @return velY - velocity in Y direction
	 */
	public float getVelY(){
		return this.velY;
	}
	
}

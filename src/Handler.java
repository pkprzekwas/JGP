import java.awt.Graphics;
import java.util.LinkedList;

/**
 * This class updates and renders all objects of our game. 
 * @author Patryk
 *
 */
public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>(); 
	
	/**
	 * Iterates thought all objects and refresh theirs logic.
	 */
	public void logic(){
		// Iterates through all objects and execute theirs logic.
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.logic();
		}
	}
	
	/**
	 * Iterates thought all objects and refresh theirs render.
	 * @param g
	 */
	public void render(Graphics g){
		// Iterates through all objects and refresh them
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	/**
	 * Deletes all objects.
	 */
	public void clearObjects(){
		object.clear();
	}
	
	/**
	 * Adds object to LinkedList of objects.
	 * @param object - GameObject instance
	 */
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	/**
	 * Removes object from LinkedList of objects.
	 * @param object - GameObject instance
	 */
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
}

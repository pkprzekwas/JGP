import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	/**
	 * This class will update and render all objects of our game 
	 */
	LinkedList<GameObject> object = new LinkedList<GameObject>(); 
	
	public void tick(){
		// Iterates through all objects and execute theirs logic
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){
		// Iterates through all objects and refresh them
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void clearObjects(){
		object.clear();
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
}

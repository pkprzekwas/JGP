import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * The main task for this class is to generate and store data. 
 * Moreover it draws health bar, player name, total time, score 
 * and current level.
 * @author Patryk Przekwas
 *
 */
public class Properties {
	
	public static float DAMAGE = 0;
	private float greenVal = 225;
	private long startTime, stopTime;
	private long startBlow, stopBlow, blowIvterval=0;
	private int level = 1;
	private int points = 0;
	private String userName = "";
	public boolean isNamed = false;
	Font font = new Font("arial", 1, 10);
	Font font1 = new Font("arial", 1, 12);
	Font font2 = new Font("arial", 1, 14);
	
	/**
	 * Method responsible for health bar behavior.
	 */
	public void logic(){
		DAMAGE = Game.clamper(DAMAGE, 0.0f, 100.0f);
		greenVal = Game.clamper(greenVal, 0.0f, 255.0f);
		greenVal = 201 - DAMAGE*2; 
	}
	
	/**
	 * Main drawing method for properties.
	 * It draws health bar, player name, total time,
	 * score and current level.
	 * @param g
	 */
	public void render(Graphics g){		
		
		g.setColor(new Color(120, (int)greenVal, 0));
		g.fillRect(750, 340, 10, 200);
		
		g.setColor(Color.GRAY);
		g.fillRect(750, 340, 10, (int)DAMAGE * 2);
		
		g.setColor(Color.white);
		g.drawRect(750, 340, 10, 200);
		
		g.setFont(font);
		g.drawString("Time: " + getCurrentGameTime(), 10, 75);
		g.setFont(font1);
		g.drawString("Level: " + level, 10, 40);
		g.drawString("Points: " + points, 10, 55);
		g.setFont(font2);
		g.setColor(Color.lightGray);
		g.drawString(userName, 10, 20 );		
	}
	
	/**
	 * Setter for user name.
	 * @param name
	 */
	public void setUserName(String name){
		this.userName = name;
	}
	
	/**
	 * Getter for user name.
	 * @return user nickname
	 */
	public String getUserName(){
		return userName;
	}
	
	/**
	 * Counts score basing on points and level
	 * @return score
	 */
	public int score(){
		return this.points + this.level*10;
	}
	
	/**
	 * Getter for level.
	 * @return level
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * Setter for level.
	 * @param level
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * Getter for points.
	 * @return points
	 */
	public int getPoints(){
		return points;
	}
	
	/**
	 * Setter for points.
	 * @param points
	 */
	public void setPoints(int points){
		this.points = points;
	}
	
	/**
	 * Takes value of current time. Used to start counting game time.
	 */
	public void startTimer(){
		this.startTime = System.currentTimeMillis();
	}
	
	/**
	 * Takes value of current time. Used to stop counting game time.
	 */
	public void stopTimer(){
		this.stopTime = System.currentTimeMillis();
	}
	
	/**
	 * Returns current game time.
	 * @return current time
	 */
	public long getCurrentGameTime(){
		return (System.currentTimeMillis() - startTime)/1000;
	}
	
	/**
	 * Time value of game time in seconds.
	 * @return game time
	 */
	public int getGameTime(){
		return (int)(this.stopTime - this.startTime)/1000;
	}
	
	/**
	 * Takes value of current time. Used to start counting blow time.
	 */
	public void startBlowing(){
		this.startBlow = System.currentTimeMillis();
	}
	
	/**
	 * Takes value of current time. Used to stop counting blow time.
	 */
	public void stopBlowing(){
		this.stopBlow = System.currentTimeMillis();
	}
	
	/**
	 * Time value of blow interval in seconds. It appends blowing when we blow.
	 */
	public void countBlowInterval(){
		this.blowIvterval = this.blowIvterval + (this.stopBlow - this.startBlow);
	}
	
	/**
	 * Returns total time of blowing in seconds.
	 * @return final blow time
	 */
	public int getBlowTime(){
		return (int)this.blowIvterval/1000;
	}
	
	
}

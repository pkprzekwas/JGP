import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Properties {
	
	public static float DAMAGE = 0;
	private float greenVal = 225;
	private long startTime, stopTime;
	private long startBlow, stopBlow, blowIvterval=0;
	private int level = 1;
	private int points = 0;
	private String userName = "";
	public boolean isNamed = false;
	
	public void tick(){
		DAMAGE = Game.clamper(DAMAGE, 0.0f, 100.0f);
		greenVal = Game.clamper(greenVal, 0.0f, 255.0f);
		greenVal = 201 - DAMAGE*2; 
	}
	
	public void render(Graphics g){		
		g.setColor(new Color(120, (int)greenVal, 0));
		g.fillRect(750, 340, 10, 200);
		
		g.setColor(Color.GRAY);
		g.fillRect(750, 340, 10, (int)DAMAGE * 2);
		
		g.setColor(Color.white);
		g.drawRect(750, 340, 10, 200);
		
		g.drawString("Time: " + (System.currentTimeMillis() - startTime)/1000, 10, 14);
		g.drawString("Level: " + level, 10, 30);
		g.drawString("Points: " + points, 10, 44);
		g.drawString(userName, 10, 58);
		
	}
	
	public void setUserName(String name){
		this.userName = name;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public int score(){
		return this.points + this.level*10;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getPoints(){
		return points;
	}
	
	public void setPoints(int points){
		this.points = points;
	}
	
	// Game time
	public void startTimer(){
		this.startTime = System.currentTimeMillis();
	}
	
	public void stopTimer(){
		this.stopTime = System.currentTimeMillis();
	}
	
	public int getGameTime(){
		return (int)(this.stopTime - this.startTime)/1000;
	}
	
	// Counting blow time
	public void startBlowing(){
		this.startBlow = System.currentTimeMillis();
	}
	public void stopBlowing(){
		this.stopBlow = System.currentTimeMillis();
	}
	
	public void countBlowInterval(){
		this.blowIvterval = this.blowIvterval + (this.stopBlow - this.startBlow);

	}
	
	public int getBlowTime(){
		return (int)this.blowIvterval/1000;
	}
	
	
}

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;



public class Menu extends MouseAdapter {
	
	private Handler handler;
	private Random r = new Random();
	private Properties properties;
	private DataFormat data;
	private Image exitImg = new ImageIcon("Textures/exit.png").getImage();
	private Image backBtn = new ImageIcon("Textures/backBtn.png").getImage();
	private Image logo = new ImageIcon("Textures/logo.png").getImage();

	public Menu(Handler handler, Properties properties, DataFormat data){
		this.handler = handler;
		this.properties = properties;
		this.data = data;
	}

	public void mousePressed(MouseEvent e){
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(Game.gameState == STATE.Menu){
			// Play button
			if(mouseIn(mouseX, mouseY, 230, 220, 360, 64)){
				Game.gameState = STATE.Game;
				// Creating basic setup
				handler.addObject(new Player(Game.WIDTH/2-32,
						Game.HEIGHT/2-32, ID.Player, handler, properties));
				handler.addObject(new Trophy(r.nextInt(Game.WIDTH-64),
						r.nextInt(Game.HEIGHT-64), ID.Trophy));
				handler.addObject(new FirstFoe(r.nextInt(Game.WIDTH-64),
						r.nextInt(Game.HEIGHT-64), ID.BasicEnemy, handler));
			}
			
			// Help button
			if(mouseIn(mouseX, mouseY, 230, 320, 360, 64)){
				Game.gameState = STATE.Help;
			}
			
			// Quit button
			if(mouseIn(mouseX, mouseY, 670, 420, 128, 128)){
				System.exit(1);
			}
		}
	
		// Back from Help
		if(Game.gameState == STATE.Help){
			if(mouseIn(mouseX, mouseY, 320, 390, 170, 79)){
				Game.gameState = STATE.Menu;
			}
		}
		
		
		if(Game.gameState == STATE.End){
			// Try Again button
			if(mouseIn(mouseX, mouseY, 260, 410, 300, 64)){
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler, properties));
				handler.addObject(new Trophy(r.nextInt(Game.WIDTH-64),r.nextInt(Game.HEIGHT-64), ID.Trophy));
				handler.addObject(new FirstFoe(r.nextInt(Game.WIDTH-64),r.nextInt(Game.HEIGHT-64), ID.BasicEnemy, handler));
				properties.DAMAGE = 0;
				properties.setPoints(0);
				properties.startTimer();	
				properties.setLevel(1);
			}
			// Quit button
			if(mouseIn(mouseX, mouseY, 670, 420, 128, 128)){
				System.exit(1);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
		
	private boolean mouseIn(int mouseX, int mouseY, int x, int y, int width, int height){
		if(mouseX > x && mouseX < x+width){
			if(mouseY > y && mouseY < y+height){ 
				return true;
			}else return false;
		}else return false;
	}
	
	public void logic(){
	}
	
	public void render(Graphics g){
		
		if(Game.gameState == STATE.Setup){

			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			
			g.drawImage(logo, 100, 0, 620, 270, null);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Wprowadz swój pseudonim", 80, 250);
			g.setFont(font2);
			g.setColor(Color.lightGray);
			g.drawString("nastêpnie potwierdz klawidzem 'Enter'", 150, 300);
			g.setColor(Color.darkGray);
			g.drawString("(max. 10 znaków)", 280, 350);
			g.setColor(Color.gray);
			g.drawString(properties.getUserName(), 400 - 10*properties.getUserName().length() , 400);
			g.setColor(Color.darkGray);
			g.drawLine(280, 410, 530, 410);
			

		}else if(Game.gameState == STATE.Menu){
			Font font = new Font("arial", 1, 45);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawImage(logo, 80, 0, 620, 270, null);
			
			g.setColor(Color.lightGray);
			g.drawString("Rozpocznij grê", 250, 270);

			g.drawString("Instrukcje", 300, 370);

			// Exit image
			g.drawImage(exitImg, 670, 420, 128, 128, null);
			
		}else if(Game.gameState == STATE.Help){
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 20);
			Font font3 = new Font("arial", 1, 30);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Zasady rozgrywki", 200, 150);
			
			g.setFont(font3);
			g.setColor(Color.lightGray);
			g.drawString("Twoim zadaniem jest unikanie ruchomych", 80, 250);
			g.drawString("przeciwników. Steruj bohaterem za pomoc¹", 80, 290);
			g.drawString("kursorów '<-' i '->' oraz SPACJI. Powodzenia !", 80, 330);
			g.drawString("kursorów '<-' i '->' oraz SPACJI. Powodzenia !", 80, 330);

			g.drawImage(backBtn, 320, 390, 170, 79, null);
			g.setFont(font2);
			g.setColor(Color.darkGray);
			g.drawString("(docelowo urz¹dzenie E-dmuchawka)", 420, 550);
			
		}else if(Game.gameState == STATE.End){
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 40);
			Font font3 = new Font("arial", 1, 30);
			
			g.setFont(font);
			g.setColor(Color.lightGray);
			g.drawString("Przegra³eœ!", 275, 110);
			
			g.setFont(font3);
			if(properties.score() < 50){
				g.setColor(Color.red);
			}
			else{
				g.setColor(Color.green);
			}
			g.drawString("Twój wynik:   " + properties.score(), 180, 200);
			g.setColor(Color.darkGray);
			g.drawString("Najlepszy wynik:   " + data.returnBestScore()[1], 180, 250);
			g.drawString("Najlepszy gracz " + data.returnBestScore()[0], 180, 290);
			if(Integer.parseInt(data.returnBestScore()[1]) == properties.score()){
				g.setColor(Color.red);
				g.drawString("GRATULACJE POBI£EŒ REKORD!", 180, 340);
			}
			
			g.setColor(Color.lightGray);
			g.setFont(font2);
			g.drawString("Jeszcze raz", 300, 455);
			
			// Exit image
			g.drawImage(exitImg, 670, 420, 128, 128, null);
		}
	}
}

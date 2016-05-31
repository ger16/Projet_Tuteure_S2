package fr.ring.gui.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.ring.personnage.*;

public class BattlePlayer {
	private Personnage p;
	private Image[] hero; 
	
	public BattlePlayer(Personnage p){
		this.p = p;
	}
	
	public void init () throws SlickException{
		hero = new Image[8];
		for(int i = 0; i<hero.length; i++){
			hero[i] = p.getSpriteSheet()[i].getSubImage(0, 3).getScaledCopy(2);
		}		
	}
	
	public void render(GameContainer container, Graphics g){
		for(int i = 0; i<hero.length; i++ ){
			hero[i].drawCentered(container.getWidth() * 1 / 4, container.getHeight() / 2);
		}
		Font font = g.getFont();
		String pv = Integer.toString((int)p.getVIT());
	    font.drawString(container.getWidth() * 1 / 4 - font.getWidth(pv) / 2, container.getHeight() / 2 - hero[0].getHeight() / 2 - font.getLineHeight(), pv, Color.white);
	
	}

	public Personnage getP() {
		return p;
	}

	public void setP(Personnage p) {
		this.p = p;
	}	
}

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
		p.initBattle();
		hero = new Image[p.getBattleSpriteSheet().length];
		for(int i = 0; i<hero.length; i++){
			hero[i] = p.getBattleSpriteSheet()[i].getSubImage(0, 3).getScaledCopy(2);
		}		
	}
	
	public void render(GameContainer container, Graphics g){
		for(int i = 0; i<hero.length; i++ ){
			hero[i].drawCentered(container.getWidth() * 1 / 4, container.getHeight() / 2);
		}
		Font font = g.getFont();
		String infos = p.getNom() + " pv:" +  Integer.toString((int)p.getVIT()) + " lvl: " + Integer.toString(p.getEXP());
	    font.drawString(container.getWidth() * 1 / 4 - font.getWidth(infos) / 2, container.getHeight() / 2 - hero[0].getHeight() / 2 - font.getLineHeight(),infos, Color.white);
	
	}

	public Personnage getP() {
		return p;
	}

	public void setP(Personnage p) {
		this.p = p;
	}	
}

package fr.ring.gui.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.ring.personnage.*;

public class BattleEnnemy {
	
	private Personnage p;
	private Image ennemy;
	
	public BattleEnnemy(Personnage p){
		this.p = p;
	}
	
	public void init () throws SlickException{
		ennemy = p.getBattleSpriteSheet()[0].getSubImage(0, 3).getScaledCopy(2);
	}
	
	public void render(GameContainer container, Graphics g){
		ennemy.drawCentered(container.getWidth() * 3 / 4, container.getHeight() / 2);
		Font font = g.getFont();
		String infos = p.getNom() + " pv:" +  Integer.toString((int)p.getVIT()) + " lvl: " + Integer.toString(p.getEXP());
	    font.drawString(container.getWidth() * 3 / 4 - font.getWidth(infos) / 2, container.getHeight() / 2 - ennemy.getHeight() / 2 - font.getLineHeight(), infos, Color.white);
	}

	public Personnage getP() {
		return p;
	}

	public void setP(Personnage p) {
		this.p = p;
	}
}

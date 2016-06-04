package fr.ring.gui.map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import fr.ring.personnage.*;

public class Player {
	private Personnage p;
	private Map map;
	
	public Player(Personnage p, Map map){
		this.p = p;
		this.map = map;
	}
	
	public void init() throws SlickException {
		p.initWalkCycle();
	}
	
	public void render(Graphics g) throws SlickException {
	    g.setColor(new Color(0, 0, 0, .5f));
	    g.fillOval(p.getX() - 16, p.getY() - 8, 32, 16);
		if(p instanceof Guerrier){
			((Guerrier) p).renderWalkCycle(g);
		}
		else if(p instanceof Mage){
			((Mage) p).renderWalkCycle(g);
		}
		else if(p instanceof Chasseur){
			((Chasseur) p).renderWalkCycle(g);
		}	    
	}
	
	
	public void update(int delta) {
		if (p.isMoving()) {
			float futurX = getFuturX(delta);
		    float futurY = getFuturY(delta);
		    boolean collision = this.map.isCollision(futurX, futurY);
		    if (collision) {
		    	p.setMoving(false);
		    } 
		    else {
		    	p.setX(futurX);
		      	p.setY(futurY);
		    }
		  }
	}

	public float getFuturX(int delta) {
		float futurX = p.getX();
		switch (p.getDirection()) {
			case 1: futurX = p.getX() - .1f * delta; break;
			case 3: futurX = p.getX() + .1f * delta; break;
		}
		return futurX;
	}

	public float getFuturY(int delta) {
		float futurY = p.getY();
		switch (p.getDirection()) {
			case 0: futurY = p.getY() - .1f * delta; break;
			case 2: futurY = p.getY() + .1f * delta; break;
		}
		return futurY;
	}

	public Personnage getP() {
		return p;
	}

	public void setP(Personnage p) {
		this.p = p;
	}
	
}

package fr.ring.personnage;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import fr.ring.capacites.BouleDeFeu;
import fr.ring.capacites.Capacite;
import fr.ring.capacites.Protect;

public class Mage extends Personnage { 
	
	public Mage() {
		super("",  10, 10, 30, 30, new ArrayList<Capacite>());
		getCAP().add(new BouleDeFeu());
		getCAP().add(new Protect());
	}

	public Mage(String nom, int fOR, int dEX, int iNT, int cON, ArrayList<Capacite> cAP, double vIT, int eXP, float x, float y){
		super(nom, fOR, dEX, iNT, cON, cAP, vIT, eXP, x, y);
		
	}

	public String toString() {
	return "Classe du heros: Magicien Nom: " + getNom() + ", Force: " + getFOR() + ", Dexeterites" + getDEX() + ", Intelligence: "
			+ getINT() + ", Concentration: " + getCON() + ", PV: " + getVIT() + ", Experience: " + getEXP();
			}	
	
	public boolean evolutionFOR() {
		if (getFOR() < getDEX())
			return true;
		
		else if(getINT() > getFOR()+15 && getCON() >getFOR()+15)
			return true;
		else
			return false;
	}

	public boolean evolutionDEX() {
		if (getDEX() < getFOR())
			return true;
		else if(getINT() > getDEX()+15 && getCON() > getDEX()+15)
			return true;
		else
			return false;
	}

	public boolean evolutionINT() {
			return true;
	}

	public boolean evolutionCON() {	
			return true;
	}
	
	public void initWalkCycle() throws SlickException {
		spriteSheet = new SpriteSheet[6];
		spriteSheet[0] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/HEAD_robe_hood.png", 64, 64);
		spriteSheet[1] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/BELT_rope.png", 64, 64);
		spriteSheet[2] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/TORSO_robe_shirt_brown.png", 64, 64);
		spriteSheet[3] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/LEGS_robe_skirt.png", 64, 64);
		spriteSheet[4] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/FEET_shoes_brown.png", 64, 64);
		spriteSheet[5] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/BODY_male.png", 64, 64);
		this.initSpriteSheet();
	}
	
	public void renderWalkCycle (Graphics g) throws SlickException{
	    g.drawAnimation(this.getUneAnimationCorps(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationPieds(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationJambes(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationTorse(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationCeinture(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
		g.drawAnimation(this.getUneAnimationTete(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
		    
	}

	@Override
	public void initBattle() throws SlickException {
		battleSpriteSheet = new SpriteSheet[5];
		battleSpriteSheet[0] = new SpriteSheet("fr/ring/gui/ressources/characters/spellcast/BODY_male.png", 64, 64);
		battleSpriteSheet[1] = new SpriteSheet("fr/ring/gui/ressources/characters/spellcast/FEET_shoes_brown.png", 64, 64);
		battleSpriteSheet[2] = new SpriteSheet("fr/ring/gui/ressources/characters/spellcast/LEGS_robe_skirt.png", 64, 64);
		battleSpriteSheet[3] = new SpriteSheet("fr/ring/gui/ressources/characters/spellcast/TORSO_robe_shirt_brown.png", 64, 64);
		battleSpriteSheet[4] = new SpriteSheet("fr/ring/gui/ressources/characters/spellcast/HEAD_robe_hood.png", 64, 64);
	}
}

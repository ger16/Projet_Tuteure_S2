package fr.ring.personnage;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import fr.ring.capacites.Arc;
import fr.ring.capacites.Capacite;
import fr.ring.capacites.ToucheGuerrisseur;

public class Chasseur extends Personnage {

	public static final int  BRACELETS = 6, SHIRT = 7, EPAULES = 8, CARQUOIS = 9;
	
	private Animation[] animationsBracelets = new Animation[8];
	private Animation[] animationsShirt = new Animation[8];
	private Animation[] animationsEpaules = new Animation[8];
	private Animation[] animationsCarquois = new Animation[8];
	
	public Chasseur() {
		super("",  20, 20, 20, 20, new ArrayList<Capacite>());
		getCAP().add(new Arc());
		getCAP().add(new ToucheGuerrisseur());
	}
	
	public Chasseur(String nom, int fOR, int dEX, int iNT, int cON, ArrayList<Capacite> cAP, double vIT, int eXP, float x, float y){
		super(nom, fOR, dEX, iNT, cON, cAP, vIT, eXP, x, y);
		
	}
	
	
	public String toString() {
		return "Classe du heros: Chasseur \n Nom: " + getNom() + ", Force: " + getFOR() + ", Dexeterites" + getDEX() + ", Intelligence: "
				+ getINT() + ", Concentration: " + getCON() + ", PV: " + getVIT() + ", Experience: " + getEXP();
	}

	@Override
	public boolean evolutionFOR() {
		return true;
	}

	@Override
	public boolean evolutionDEX() {
		return true;
	}

	@Override
	public boolean evolutionINT() {
		return true;
	}

	@Override
	public boolean evolutionCON() {
		return true;
	}
	
	public void initWalkCycle() throws SlickException {
		spriteSheet = new SpriteSheet[10];
	
		spriteSheet[0] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/HEAD_leather_armor_hat.png", 64, 64);
		spriteSheet[1] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/BELT_leather.png", 64, 64);
		spriteSheet[2] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/TORSO_leather_armor_torso.png", 64, 64);
		spriteSheet[3] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/LEGS_pants_greenish.png", 64, 64);
		spriteSheet[4] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/FEET_shoes_brown.png", 64, 64);
		spriteSheet[5] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/BODY_male.png", 64, 64);
		spriteSheet[6] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/TORSO_leather_armor_bracers.png", 64, 64);
		spriteSheet[7] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/TORSO_leather_armor_shirt_white.png", 64, 64);
		spriteSheet[8] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/TORSO_leather_armor_shoulders.png", 64, 64);
		spriteSheet[9] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/BEHIND_quiver.png", 64, 64);
		this.initSpriteSheet();
	}
	
	public void initBattle() throws SlickException {
		battleSpriteSheet = new SpriteSheet[11];
		battleSpriteSheet[0] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/BODY_animation.png", 64, 64);
		battleSpriteSheet[1] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/FEET_shoes_brown.png", 64, 64);
		battleSpriteSheet[2] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/LEGS_pants_greenish.png", 64, 64);
		battleSpriteSheet[3] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/TORSO_leather_armor_shirt_white.png", 64, 64);
		battleSpriteSheet[4] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/TORSO_leather_armor_torso.png", 64, 64);
		battleSpriteSheet[5] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/TORSO_leather_armor_bracers.png", 64, 64);
		battleSpriteSheet[6] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/TORSO_leather_armor_shoulders.png", 64, 64);
		battleSpriteSheet[7] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/HEAD_leather_armor_hat.png", 64, 64);
		battleSpriteSheet[8] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/BELT_leather.png", 64, 64);
		battleSpriteSheet[9] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/WEAPON_bow.png", 64, 64);
		battleSpriteSheet[10] = new SpriteSheet("fr/ring/gui/ressources/characters/bow/WEAPON_arrow.png", 64, 64);
	}
	
	public void renderWalkCycle(Graphics g) throws SlickException {
	    g.setColor(new Color(0, 0, 0, .5f));
	    g.fillOval(this.getX() - 16, this.getY() - 8, 32, 16);
	    g.drawAnimation(this.getUneAnimationCarquois(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationCorps(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationPieds(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationJambes(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationShirt(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationTorse(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationBracelets(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationEpaules(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationCeinture(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
		g.drawAnimation(this.getUneAnimationTete(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);	    
	}
	
	public Animation getUneAnimationBracelets(int i){
		return this.animationsBracelets[i];
	}
	public Animation getUneAnimationShirt(int i){
		return this.animationsShirt[i];
	}
	public Animation getUneAnimationEpaules(int i){
		return this.animationsEpaules[i];
	}
	public Animation getUneAnimationCarquois(int i){
		return this.animationsCarquois[i];
	}
	
	public void setUneAnimation(Animation a, int nomAnimation, int i){
		super.setUneAnimation(a, nomAnimation, i);
		if(nomAnimation == BRACELETS)
			this.animationsBracelets[i] = a;
		else if(nomAnimation == SHIRT)
			this.animationsShirt[i] = a;
		else if(nomAnimation == EPAULES)
			this.animationsEpaules[i] = a;
		else if(nomAnimation == CARQUOIS)
			this.animationsCarquois[i] = a;
	}

}

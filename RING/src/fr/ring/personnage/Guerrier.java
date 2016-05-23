package fr.ring.personnage;

import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import fr.ring.capacites.Capacite;

public class Guerrier extends Personnage {
	/*
	 * Caracteristique:
	 * 			FORCE 	=> 	DEX + 10
	 * 			DEX		=>	INT
	 * 			INT		=>	CON
	 * 
	 * 			30-20-20-10
	 */
	
	
	// COnstructeur a choisir lequel est le  mieux
	
	//par defaut en respectant le minimum de condition
	public static final int MAINS = 6, TORSE_EPAULE = 7, BOUCLIER = 8;
	
	private Animation[] animationsMain = new Animation[8];
	private Animation[] animationsTorseEpaules = new Animation[8];
	private Animation[] animationsBouclier = new Animation[8];
	
	public Guerrier() {
			super("Farida",30 , 20, 20, 10, new ArrayList<Capacite>());
	}
	
	//champs par champs
	public Guerrier(String nom, int FOR, int DEX, int INT, int CON, ArrayList<Capacite> CAP){
		super(nom, FOR, DEX, INT, CON, CAP);
		
	}

	//Methode toString
	public String toString() {
		return "Classe du heros: Guerrier Nom: " + getNom() + ", Force: " + getFOR() + ", Dexeterite: " + getDEX() + ", Intelligence: "
				+ getINT() + ", Concentration: " + getCON() + ", PV: " + getVIT() + ", Experience: " + getEXP();
	}
	
// METHODE ATTRIBUTION DES POINTS DE COMPETENCES
	
	public boolean evolutionFOR(){
		// Force peut �tre augmenter tout le temps car 0 constriction
		return true;
	}
	
	public boolean evolutionDEX(){
		if (getFOR() > getDEX() + 10)
			return true;
		else
			return false;
	}
	
	public boolean evolutionINT(){
		if (getFOR() > getINT()+10 && getDEX()>getINT())
			return true;
		else
			return false;
	}
	
	public boolean evolutionCON(){
		if (getFOR() > getCON() + 10 && getINT()>getCON())
			return true;
		else
			return false;
	}
	
	public void initWalkCycle() throws SlickException {
		spriteSheet = new SpriteSheet[9];
	
		spriteSheet[0] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/HEAD_plate_armor_helmet.png", 64, 64);
		spriteSheet[1] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/BELT_leather.png", 64, 64);
		spriteSheet[2] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/TORSO_plate_armor_torso.png", 64, 64);
		spriteSheet[3] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/LEGS_plate_armor_pants.png", 64, 64);
		spriteSheet[4] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/FEET_plate_armor_shoes.png", 64, 64);
		spriteSheet[5] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/BODY_male.png", 64, 64);
		spriteSheet[6] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/HANDS_plate_armor_gloves.png", 64, 64);
		spriteSheet[7] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/TORSO_plate_armor_arms_shoulders.png", 64, 64);
		spriteSheet[8] = new SpriteSheet("fr/ring/gui/ressources/characters/walkcycle/WEAPON_shield_cutout_body.png", 64, 64);
		this.initSpriteSheet();
	}
	
	public void renderWalkCycle (Graphics g) throws SlickException{
	    g.drawAnimation(this.getUneAnimationCorps(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationPieds(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationJambes(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationTorse(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationTorseEpaules(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
	    g.drawAnimation(this.getUneAnimationCeinture(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
		g.drawAnimation(this.getUneAnimationTete(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
		g.drawAnimation(this.getUneAnimationMains(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
		g.drawAnimation(this.getUneAnimationBouclier(this.getDirection() + (this.isMoving() ? 4 : 0)), this.getX()-32, this.getY()-60);
		    
	}
	public Animation getUneAnimationMains(int i){
		return this.animationsMain[i];
	}
	public Animation getUneAnimationTorseEpaules(int i){
		return this.animationsTorseEpaules[i];
	}
	public Animation getUneAnimationBouclier(int i){
		return this.animationsBouclier[i];
	}
	
	public void setUneAnimation(Animation a, int nomAnimation, int i){
		super.setUneAnimation(a, nomAnimation, i);
		if(nomAnimation == MAINS)
			this.animationsMain[i] = a;
		else if(nomAnimation == TORSE_EPAULE)
			this.animationsTorseEpaules[i] = a;
		else if(nomAnimation == BOUCLIER)
			this.animationsBouclier[i] = a;		
	}
	
	
}

package fr.ring.personnage;

import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

import fr.ring.capacites.Capacite;
import fr.ring.capacites.Epee;
import fr.ring.capacites.ToucheGuerrisseur;

public abstract class Personnage {
	public static final int TETE = 0, CEINTURE = 1, TORSE = 2, JAMBES = 3, PIEDS = 4 ,CORPS = 5;
	
	private String nom;
	
	private int FOR; 	// Force
	private int DEX; 	// Dexterite
	private int INT; 	// Intelligence
	private int CON; 	// Concentration
	
	private int VIT; 	// Vitalite
	private int EXP;	// Experience
	private ArrayList<Capacite> CAP;
	
	protected float x = 300, y = 300;
	private int direction = 0;
	private boolean moving = false;
	protected SpriteSheet [] spriteSheet;
	private Animation[] animationsTete = new Animation[8];
	private Animation[] animationsCeinture = new Animation[8];
	private Animation[] animationsTorse = new Animation[8];
	private Animation[] animationsJambes = new Animation[8];
	private Animation[] animationsPieds = new Animation[8];
	private Animation[] animationsCorps = new Animation[8];
	
	public Personnage() {
	/*	nom = "Farida";
		FOR = 0;
		DEX = 0;
		INT = 0;
		CON = 0;
		VIT = 200 -(FOR + DEX + INT + CON) + EXP*3;
		EXP = 1;
		*/
	}

	public Personnage(String nom, int fOR, int dEX, int iNT, int cON, ArrayList<Capacite> cAP) {
		this.nom = nom;
		FOR = fOR;
		DEX = dEX;
		INT = iNT;
		CON = cON;
		VIT = 200 -(FOR + DEX + INT + CON) + EXP*3;
		EXP = 1;
		CAP = cAP;
		cAP.add(new Epee());
		cAP.add(new ToucheGuerrisseur());
	}
	
	public Personnage( Personnage hero){
		this.nom = hero.getNom();
		this.FOR = hero.getFOR();
		this.DEX = hero.getDEX();
		this.INT = hero.getINT();
		this.CON = hero.getCON();
		this.VIT = getVIT();
		this.EXP = hero.getEXP();
		this.CAP = hero.getCAP();
	}

	// GETTERS SETTERS
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getFOR() {
		return FOR;
	}
	public void setFOR(int fOR) {
		FOR = fOR;
	}
	
	public int getDEX() {
		return DEX;
	}
	public void setDEX(int dEX) {
		DEX = dEX;
	}

	public int getINT() {
		return INT;
	}
	public void setINT(int iNT) {
		INT = iNT;
	}

	public int getCON() {
		return CON;
	}
	public void setCON(int cON) {
		CON = cON;
	}

	public int getVIT() {
		return VIT;
	}
	public void setVIT(int vIT) {
		VIT = vIT;
	}
	
	public int getEXP() {
		return EXP;
	}
	public void setEXP(int eXP) {
		EXP = eXP;
	}
	public ArrayList<Capacite> getCAP() {
		return CAP;
	}

	public void setCAP(ArrayList<Capacite> cAP) {
		CAP = cAP;
	}
	
	public void initSpriteSheet(){
		for(int i=0; i<spriteSheet.length; i++){		
			this.setUneAnimation(loadAnimation(spriteSheet[i], 0, 1, 0),i , 0);
			this.setUneAnimation(loadAnimation(spriteSheet[i], 0, 1, 1),i , 1);
			this.setUneAnimation(loadAnimation(spriteSheet[i], 0, 1, 2),i , 2);
			this.setUneAnimation(loadAnimation(spriteSheet[i], 0, 1, 3),i , 3);
			this.setUneAnimation(loadAnimation(spriteSheet[i], 1, 9, 0),i , 4);
			this.setUneAnimation(loadAnimation(spriteSheet[i], 1, 9, 1),i , 5);
			this.setUneAnimation(loadAnimation(spriteSheet[i], 1, 9, 2),i , 6);
			this.setUneAnimation(loadAnimation(spriteSheet[i], 1, 9, 3),i , 7);
			
		}
	}
	
	// METHODE EVOLUTION DES PERSONNAGES
	public void evolutionXP(){
		int validation = 0;
		Scanner scanner = new Scanner(System.in);
		do{ 
			evolutionPossible();
			System.out.println("Quel competence voulez vous augmenter?");
			int choix = scanner.nextInt();
			
			switch(choix){
			
			case 1:// FORCE 
					if (evolutionFOR()==true){
						setFOR(getFOR()+1);
						validation ++;
						break;
					}
					else{
						System.out.println("Impossible d'augmenter Force");
						break;
					}
			
			case 2:// DEXTERITE
					if (evolutionDEX()==true){
						setDEX(getDEX()+1);
						validation++;
						break;}
					else{
						System.out.println("Impossible d'augmenter Dexterite");
						break;}
			
			case 3: //INTELLIGENCE
					if (evolutionINT()==true){
						setINT(getINT()+1);
						validation++;
						break;}
					else{
						System.out.println("Impossible d'augmenter Intelligence");
						break;}
			case 4: // CONCENTRATION
					if (evolutionCON()==true){
						setCON(getCON()+1);
						validation++;
						break;}
					else{
						System.out.println("Impossible d'augmenter Concentration");
						break;}
			default:
					System.out.println("Saississez une valeur correcte");
					break;
			}
		} while (validation == 0);
	}

	public abstract boolean evolutionFOR();
	public abstract boolean evolutionDEX();
	public abstract boolean evolutionINT();
	public abstract boolean evolutionCON();
	
	public void capituler(){
		
	}
	
	public boolean decede(){
		if(this.VIT <= 0)
			return true;
		return false;
	}
	
	public  void evolutionPossible(){
		if (evolutionFOR()==true)
			System.out.println("1-Force peut etre augmenter");
		
		if (evolutionDEX()==true)
			System.out.println("2-Dexterite peut etre augmenter");
		else System.out.println("Dexterite ne peut pas etre augmenter");
		
		if (evolutionINT()==true)
			System.out.println("3-Intelligence peut etre augmenter");
		else System.out.println("Inteligence ne peut pas etre augmenter");
		
		if (evolutionCON()==true)
			System.out.println("4-Concentration peut etre augmenter");
		else System.out.println("Concentration ne peut pas etre augmenter");
	}
	
	public Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public Animation getUneAnimationTete(int i){
		return this.animationsTete[i];
	}
	public Animation getUneAnimationCeinture(int i){
		return this.animationsCeinture[i];
	}
	public Animation getUneAnimationTorse(int i){
		return this.animationsTorse[i];
	}
	public Animation getUneAnimationJambes(int i){
		return this.animationsJambes[i];
	}
	public Animation getUneAnimationPieds(int i){
		return this.animationsPieds[i];
	}
	public Animation getUneAnimationCorps(int i){
		return this.animationsCorps[i];
	}
	
	public void setUneAnimation(Animation a, int nomAnimation, int i){
		if(nomAnimation == TETE)
			this.animationsTete[i] = a;
		else if(nomAnimation == CEINTURE)
			this.animationsCeinture[i] = a;
		else if(nomAnimation == TORSE)
			this.animationsTorse[i] = a;
		else if(nomAnimation == JAMBES)
			this.animationsJambes[i] = a;
		else if(nomAnimation == PIEDS)
			this.animationsPieds[i] = a;
		else if(nomAnimation == CORPS)
			this.animationsCorps[i] = a;
	}

	public SpriteSheet[] getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(SpriteSheet[] spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
}

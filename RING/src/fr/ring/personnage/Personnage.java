package fr.ring.personnage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import fr.ring.capacites.*;

public abstract class Personnage {
	public static final int TETE = 0, CEINTURE = 1, TORSE = 2, JAMBES = 3, PIEDS = 4 ,CORPS = 5;
	
	private String nom;
	
	private int FOR; 
	private int DEX; 	
	private int INT; 
	private int CON; 	
	
	private double VIT;
	private int EXP;	
	private ArrayList<Capacite> CAP;
	
	protected float x = 300, y = 300;
	private int direction = 0;
	private boolean moving = false;
	protected SpriteSheet [] spriteSheet;
	protected SpriteSheet[] battleSpriteSheet;
	
	private Animation[] animationsTete = new Animation[8];
	private Animation[] animationsCeinture = new Animation[8];
	private Animation[] animationsTorse = new Animation[8];
	private Animation[] animationsJambes = new Animation[8];
	private Animation[] animationsPieds = new Animation[8];
	private Animation[] animationsCorps = new Animation[8];
	
	public Personnage() {
	}

	public Personnage(String nom, int fOR, int dEX, int iNT, int cON, ArrayList<Capacite> cAP) {
		this.nom = nom;
		FOR = fOR;
		DEX = dEX;
		INT = iNT;
		CON = cON;
		setVITMax();
		EXP = 1;
		CAP = cAP;
	}
	
	public Personnage(String nom, int fOR, int dEX, int iNT, int cON, ArrayList<Capacite> cAP, int eXP, float x, float y){
		this.nom = nom;
		FOR = fOR;
		DEX = dEX;
		INT = iNT;
		CON = cON;
		CAP = cAP;
		EXP = eXP;
		this.x = x;
		this.y = y;
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

	public double getVIT() {
		return VIT;
	}
	public void setVIT(double vIT) {
		VIT = vIT;
	}
	public double getVITMax(){
		return 200.0 -(FOR + DEX + INT + CON) + EXP*3.0;
	}
	public void setVITMax(){
		VIT = 200.0 -(FOR + DEX + INT + CON) + EXP*3.0;
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
	
	public abstract void initWalkCycle() throws SlickException;
	public abstract void renderWalkCycle(Graphics g) throws SlickException;
	public abstract void initBattle() throws SlickException;

	public abstract boolean evolutionFOR();
	public abstract boolean evolutionDEX();
	public abstract boolean evolutionINT();
	public abstract boolean evolutionCON();
	
	public boolean isDead(){
		if(this.VIT <= 0)
			return true;
		return false;
	}
	
	public Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}
	public void sauvergarderHero() throws IOException{
		File f = new File(new File("Sauvegarde"), nom + ".txt");
		PrintWriter w = new PrintWriter(f);
		if (this instanceof Mage)
			w.println("Mage");
		if (this instanceof Chasseur)
			w.println("Chasseur");
		if (this instanceof Guerrier)
			w.println("Guerrier");
		w.println(getFOR());
		w.println(getDEX());
		w.println(getINT());
		w.println(getCON());
		w.println(getEXP());
		w.println(getX());
		w.println(getY());
		for(int i = 0; i<CAP.size();i++){
			w.println(CAP.get(i).getClass().getSimpleName());
		}
		w.close();
		System.out.println("Sauvegarde reussi");
		System.out.println(f.getAbsolutePath());
	}
	
	public void supprimerHero(){
		File f = new File(new File("Sauvegarde"), nom + ".txt");
		f.delete();
	}

	public static Personnage chargerHero(String nom) throws FileNotFoundException{
		File f = new File(new File ("Sauvegarde"),nom+".txt");
		Scanner sc = new Scanner(f);
		ArrayList <Capacite> chargedCap = new ArrayList<Capacite>();
		String type = sc.nextLine();
		int fOR= Integer.parseInt(sc.nextLine());
		int dEX= Integer.parseInt(sc.nextLine());
		int iNT= Integer.parseInt(sc.nextLine());
		int cON= Integer.parseInt(sc.nextLine());
		int eXP= Integer.parseInt(sc.nextLine());
		Float xL= Float.parseFloat(sc.nextLine());
		Float yL= Float.parseFloat(sc.nextLine());
		do{
			chargedCap.add(Capacite.getCapA(sc.nextLine()));
		}while(sc.hasNextLine());
		sc.close();
		
		if (type.equals("Mage"))
			return new Mage (nom,fOR,dEX,iNT,cON, chargedCap, eXP, xL, yL);
		if (type.equals("Guerrier"))
			return new Guerrier (nom,fOR,dEX,iNT,cON, chargedCap, eXP, xL, yL);
		if (type.equals("Chasseur"))
			return new Chasseur (nom,fOR,dEX,iNT,cON, chargedCap, eXP, xL, yL);	
		return null;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CAP == null) ? 0 : CAP.hashCode());
		result = prime * result + CON;
		result = prime * result + DEX;
		result = prime * result + EXP;
		result = prime * result + FOR;
		result = prime * result + INT;
		long temp;
		temp = Double.doubleToLongBits(VIT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + direction;
		result = prime * result + (moving ? 1231 : 1237);
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personnage other = (Personnage) obj;
		if (CAP == null) {
			if (other.CAP != null)
				return false;
		} else if (!CAP.equals(other.CAP))
			return false;
		if (CON != other.CON)
			return false;
		if (DEX != other.DEX)
			return false;
		if (EXP != other.EXP)
			return false;
		if (FOR != other.FOR)
			return false;
		if (INT != other.INT)
			return false;
		if (Double.doubleToLongBits(VIT) != Double.doubleToLongBits(other.VIT))
			return false;
		if (direction != other.direction)
			return false;
		if (moving != other.moving)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Personnage [nom=" + nom + ", FOR=" + FOR + ", DEX=" + DEX
				+ ", INT=" + INT + ", CON=" + CON + ", VIT=" + VIT + ", EXP="
				+ EXP + ", CAP=" + CAP + ", x=" + x + ", y=" + y + "]";
	}

	public SpriteSheet[] getBattleSpriteSheet() {
		return battleSpriteSheet;
	}

	public void setBattleSpriteSheet(SpriteSheet[] battleSpriteSheet) {
		this.battleSpriteSheet = battleSpriteSheet;
	}
	
}

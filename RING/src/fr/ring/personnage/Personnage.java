package fr.ring.personnage;

public class Personnage {
	
	private String nom;
	
	private int FOR; 	// Force
	private int DEX; 	// Dexterite
	private int INT; 	// Intelligence
	private int CON; 	// Concentration
	
	private int VIT; 	// Vitalite
	private int EXP;	// Experience
	
	public Personnage() {
		nom = "Farida";
		FOR = 0;
		DEX = 0;
		INT = 0;
		CON = 0;
		VIT = 200 -(FOR + DEX + INT + CON) + EXP*3;
		EXP = 1;
	}

	public Personnage(String nom, int fOR, int dEX, int iNT, int cON) {
		this.nom = nom;
		FOR = fOR;
		DEX = dEX;
		INT = iNT;
		CON = cON;
		VIT = 200 -(FOR + DEX + INT + CON) + EXP*3;
		EXP = 1;
	}
	
	public Personnage( Personnage hero){
		this.nom = hero.nom;
		this.FOR= hero.FOR;
		this.DEX= hero.DEX;
		this.INT= hero.INT;
		this.CON= hero.CON;
		this.VIT= 200 -(hero.FOR + hero.DEX + hero.INT + hero.CON) + EXP*3;
		this.EXP= hero.EXP;
	}

	// GETTERS SETTERS + Divers methodes
	
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

}

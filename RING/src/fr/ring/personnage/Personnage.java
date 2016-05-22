package fr.ring.personnage;

import java.util.Scanner;

public abstract class Personnage {
	
	private String nom;
	
	private int FOR; 	// Force
	private int DEX; 	// Dexterite
	private int INT; 	// Intelligence
	private int CON; 	// Concentration
	
	private int VIT; 	// Vitalite
	private int EXP;	// Experience
	
	public Personnage(String nom) {
		this.nom =nom;
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
	
	public Personnage(Personnage hero){
		this.nom = hero.nom;
		this.FOR= hero.FOR;
		this.DEX= hero.DEX;
		this.INT= hero.INT;
		this.CON= hero.CON;
		this.VIT= 200 -(hero.FOR + hero.DEX + hero.INT + hero.CON) + EXP*3;
		this.EXP= hero.EXP;
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
	
}

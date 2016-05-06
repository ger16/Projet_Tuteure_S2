package fr.ring.personnage;
public class Chasseur extends Personnage {
	/*
	 * Caracteristique:
	 * 			FORCE 	>= 20
	 * 			DEX 	>= 20
	 * 			INT		>= 20
	 */
	public Chasseur() {
		super("Farida",  20, 20, 20, 20);
	}
	
	public Chasseur(String nom, int FOR, int DEX, int INT, int CON){
		super(nom, FOR, DEX, INT, CON);
		
	}
	
	
	public String toString() {
		return "Classe du heros: Chasseur \n Nom: " + getNom() + ", Force: " + getFOR() + ", Dexeterites" + getDEX() + ", Intelligence: "
				+ getINT() + ", Concentration: " + getCON() + ", PV: " + getVIT() + ", Experience: " + getEXP();
	}

}

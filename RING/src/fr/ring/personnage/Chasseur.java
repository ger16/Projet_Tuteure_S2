package fr.ring.personnage;

public class Chasseur extends Personnage {
	/*
	 * Caracteristique:
	 * 			FORCE 	>= 20
	 * 			DEX 	>= 20
	 * 			INT		>= 20
	 */
	public Chasseur(String nom) {
		super(nom,  20, 20, 20, 20);
	}
	
	
	public Chasseur(String nom, int FOR, int DEX, int INT, int CON){
		super(nom, FOR, DEX, INT, CON);
	}
	public Chasseur(Chasseur hero){
		super(hero);
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

}

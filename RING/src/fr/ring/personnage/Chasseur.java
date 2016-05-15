package fr.ring.personnage;

import java.util.ArrayList;

import fr.ring.capacites.Capacite;

public class Chasseur extends Personnage {
	/*
	 * Caracteristique:
	 * 			FORCE 	>= 20
	 * 			DEX 	>= 20
	 * 			INT		>= 20
	 */
	public Chasseur() {
		super("Farida",  20, 20, 20, 20, new ArrayList<Capacite>());
	}
	
	public Chasseur(String nom, int FOR, int DEX, int INT, int CON, ArrayList<Capacite> CAP){
		super(nom, FOR, DEX, INT, CON, CAP);
		
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

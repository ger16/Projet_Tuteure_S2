package fr.ring.personnage;

import java.util.Scanner;

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
	public Guerrier() {
			super("Farida",30 , 20, 20, 10);
	}
	
	//champs par champs
	public Guerrier(String nom, int FOR, int DEX, int INT, int CON){
		super(nom, FOR, DEX, INT, CON);
	}

	//Methode toString
	public String toString() {
		return "Classe du heros: Guerrier Nom: " + getNom() + ", Force: " + getFOR() + ", Dexeterite: " + getDEX() + ", Intelligence: "
				+ getINT() + ", Concentration: " + getCON() + ", PV: " + getVIT() + ", Experience: " + getEXP();
	}
	
// METHODE ATTRIBUTION DES POINTS DE COMPETENCES
	
	public boolean evolutionFOR(){
		// Force peut être augmenter tout le temps car 0 constriction
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
	
	
}

package fr.ring.personnage;

public class Guerrier extends Personnage {
	/*
	 * Caracteristique:
	 * 			FORCE 	=> 	DEX + 10
	 * 			DEX 	=>	INT + 10
	 * 			INT		=>	CON + 10
	 * 
	 * 		Donc pour 80 points de competences à gagner.
	 */
	public Guerrier() {
			super("Farida",  35, 25, 15, 5);
		}
	
	public Guerrier(String nom, int FOR, int DEX, int INT, int CON){
		super(nom, FOR, DEX, INT, CON);
	}

	public String toString() {
		return "Classe du heros: Guerrier \n Nom: " + getNom() + ", Force: " + getFOR() + ", Dexeterites" + getDEX() + ", Intelligence: "
				+ getINT() + ", Concentration: " + getCON() + ", PV: " + getVIT() + ", Experience: " + getEXP();
	}
	
// ATTRIBUTION DES POINTS DE COMPETENCES
	public Evolution(){
		if (getEXP() = 20){
			break;
		
		}
	}
}
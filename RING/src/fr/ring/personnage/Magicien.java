package fr.ring.personnage;

public class Magicien extends Personnage {
/*
 * Caracteristique:
 * INT >= max(FOR, DEX) + 15
 * CON >= max(FOR, DEX) + 15
 * 
 * Valeur la plus grande entre FOR et DEX est selectionner.
 * 80 points de competences à repartir par default:
 * INT: 30
 * CON: 30
 * FOR: 10
 * DEX: 10
 */
	public Magicien() {
		super("Farida",  10, 10, 30, 30);
	}

	public Magicien(String nom, int FOR, int DEX, int INT, int CON){
	super(nom, FOR, DEX, INT, CON);
	}

	public String toString() {
	return "Classe du heros: Magicien\n Nom: " + getNom() + ", Force: " + getFOR() + ", Dexeterites" + getDEX() + ", Intelligence: "
			+ getINT() + ", Concentration: " + getCON() + ", PV: " + getVIT() + ", Experience: " + getEXP();
			}
}

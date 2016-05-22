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
	public Magicien(String nom) {
		super(nom,  10, 10, 30, 30);
	}

	public Magicien(String nom, int FOR, int DEX, int INT, int CON){
	super(nom, FOR, DEX, INT, CON);
	}
	
	public Magicien(Magicien hero){
		super(hero);
	}

	public String toString() {
	return "Classe du heros: Magicien Nom: " + getNom() + ", Force: " + getFOR() + ", Dexeterites" + getDEX() + ", Intelligence: "
			+ getINT() + ", Concentration: " + getCON() + ", PV: " + getVIT() + ", Experience: " + getEXP();
			}

	//Methode EVOLUTION //Meme si on peut augmenter l'intelligence/Concentration tt le temps,
	//On laisse les conditions dans le cas d'une extention
	
	
	public boolean evolutionFOR() {
		if (getFOR() < getDEX())
			return true;
		
		else if(getINT() > getFOR()+15 && getCON() >getFOR()+15)
			return true;
		else
			return false;
	}

	public boolean evolutionDEX() {
		if (getDEX() < getFOR())
			return true;
		else if(getINT() > getDEX()+15 && getCON() > getDEX()+15)
			return true;
		else
			return false;
	}

	public boolean evolutionINT() {
			return true;
	}

	public boolean evolutionCON() {	
			return true;
			//Pas de contrainte
	}
}

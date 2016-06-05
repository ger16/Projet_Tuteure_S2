package fr.ring.capacites;

import fr.ring.personnage.Chasseur;


public class TestCapacite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Chasseur c = new Chasseur();
		Arme a = new Arme();
		System.out.println(c.toString());
		System.out.println(a.attaquer(c));
		System.out.println(a.efficaciteAttaque(c));
		System.out.println(a.containInterfaces("Attaque"));
		System.out.println(a.containInterfaces("Defense"));
		System.out.println(a.containInterfaces("Soin"));
	}

}

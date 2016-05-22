package fr.ring.menu;

import java.util.*;
import fr.ring.capacites.*;
import fr.ring.personnage.*;

	public class Menu {
		public Menu(){
		}
	
	public Personnage menuCreationPersonnage(){
		menuDebut();
		int choix = menuChoixPerso();
		Personnage p = choixPersonnage(choix);
		return p;
	}
		
	public void menuDebut(){
		System.out.println("Bienvenue dans ce jeu de Farida! Farida est dieu ici!\n");
		System.out.println("Creer un personnage");
		System.out.println("Charger un personnage");
	}
	
	public int menuChoixPerso(){
		int choix;
		int validation = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Tout d'abord nous devons commencer par choisir un héros");
		do{
			System.out.println("Choisies un hero entre le Chasseur(1), le Guerrier(2), ou le Magicien(3)");
			choix = sc.nextInt();
			if (choix == 1 || choix == 2 || choix == 3)
				validation++;
		}
		while (validation == 0);
		return choix;
	}

	public Personnage choixPersonnage(int choix){
		String nom;
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel est le nom de votre héro?");
		nom = sc.nextLine();
		switch(choix){
		case 1: Chasseur heroC = new Chasseur(nom);
				return heroC; 
		case 2: Guerrier heroG = new Guerrier(nom);
				return heroG;
		case 3: Magicien heroM = new Magicien(nom);
				return heroM;
		}
		System.out.println("Valeur invalide");
		return null;
	}
	
}

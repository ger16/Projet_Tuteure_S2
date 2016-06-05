package fr.ring.personnage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import fr.ring.capacites.Capacite;

public class TestSauvegarde {

	public static void main(String[] args) throws FileNotFoundException {
		Personnage p = new Chasseur();
		p.sauvergarderHero(p);
		String s = p.toString();
		System.out.println(s);	
		System.out.println(""+p.getClass().getSimpleName());
		
		
		System.out.println("Debut: Chargement de compétence");
		p.setFOR(85);
		p.setCON(85);
		p.setDEX(85);
		p.setINT(85);
		s=p.toString();
		System.out.println(s);
		System.out.println("\n");
		
		System.out.println("Quel est le nom de votre héros?");
		Scanner sc= new Scanner(System.in);
		String nom = sc.next();
		System.out.println(nom);
		
		
		p=p.chargerHero(nom);
		System.out.println("Charegement fin");
		
	}

}

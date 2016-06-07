package fr.ring.personnage;
import java.util.*;

import fr.ring.capacites.Capacite;

public class TestHeros{

	public static void main(String[] args) {
		
		Guerrier heroG = new Guerrier ("GerryBoy", 50, 40, 29, 18, new ArrayList<Capacite>(), 0, 1, 300, 300);
		Mage heroM = new Mage ("Jaina", 10, 15, 35, 30, new ArrayList<Capacite>() ,0, 1, 300, 300);
		Chasseur heroC = new Chasseur ("Huntard", 20, 20, 20, 20, new ArrayList<Capacite>(), 0, 1, 300, 300);
	
		// test du toString
		System.out.println("Test du toString\n");
		
		String testG = heroG.toString();
		String testM = heroM.toString();
		String testC = heroC.toString();
		
		System.out.println(testG);
		System.out.println(testM);
		System.out.println(testC);
		System.out.println("\n");

		//test EvolutionFOR/DEXINT/CON
		System.out.println("Test de EvolutionFOR/DEXINT/CON() \n");
		
		boolean aG= heroG.evolutionFOR();
		boolean bG= heroG.evolutionDEX();
		boolean cG= heroG.evolutionINT();
		boolean dG= heroG.evolutionCON();
		
		boolean aM= heroM.evolutionFOR();
		boolean bM= heroM.evolutionDEX();
		boolean cM= heroM.evolutionINT();
		boolean dM= heroM.evolutionCON();
		
		boolean aC= heroC.evolutionFOR();
		boolean bC= heroC.evolutionDEX();
		boolean cC= heroC.evolutionINT();
		boolean dC= heroC.evolutionCON();
		
		System.out.println("\nGuerrier");
		System.out.println(aG);
		System.out.println(bG);
		System.out.println(cG);
		System.out.println(dG);
		
		
		System.out.println("\nMagicien");
		System.out.println(aM);
		System.out.println(bM);
		System.out.println(cM);
		System.out.println(dM);
		
		System.out.println("\nChasseur");
		System.out.println(aC);
		System.out.println(bC);
		System.out.println(cC);
		System.out.println(dC);
		System.out.println("\n");
		
		
		//RESTE EVOLUTION XP CHASSEUR
		System.out.println("Test de evolutionXP()");
		System.out.println("\nGuerrier");
		//EVOLUTION GUERRIER
		System.out.println(testG);
		System.out.println("\n");
		//heroG.evolutionXP();
		System.out.println("\n");
		testG = heroG.toString();
		System.out.println(testG);
		
		//EVOLUTION MAGE
		System.out.println("Magicien\n");
		System.out.println(testM);
		System.out.println("\n");
		//heroM.evolutionXP();
		System.out.println("\n");
		testM = heroM.toString();
		System.out.println(testM);
	}

}

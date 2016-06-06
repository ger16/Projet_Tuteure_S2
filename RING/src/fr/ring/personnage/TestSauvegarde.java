package fr.ring.personnage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSauvegarde {

	public static void main(String[] args) throws FileNotFoundException {
		Personnage p = new Chasseur();
		p.setNom("GerryLethug");
		Personnage p2 = new Chasseur();
		try {
			p.sauvergarderHero();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String s = p.toString();
		System.out.println(s);	
		System.out.println(""+p.getClass().getSimpleName());
		
		
		System.out.println("Debut: Chargement de comp�tence");
		p.setFOR(85);
		p.setCON(85);
		p.setDEX(85);
		p.setINT(85);
		System.out.println(p);
		System.out.println("\n");
		p2=Personnage.chargerHero(p.getNom());
		for(int i=0; i<p2.getCAP().size(); i++){
			System.out.println(p2.getCAP().get(i));
		}
		System.out.println(p2);
		System.out.println("Charegement fin");
	}

}

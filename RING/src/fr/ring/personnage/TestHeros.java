package fr.ring.personnage;
import java.util.*;

public class TestHeros{

	public static void main(String[] args) {
		
		Guerrier heroA = new Guerrier();
		Guerrier heroB = new Guerrier("Aminn", 50, 45, 80, 20);

		String sa, sb;
		sa = heroA.toString();
		sb = heroB.toString();
		
		int resultat = heroA.max(FOR, DEX) + 15;
		
		
		
		System.out.println(sa);
		System.out.println(sb);
		
	}

}

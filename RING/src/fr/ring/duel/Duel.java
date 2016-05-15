package fr.ring.duel;

import java.util.Random;

import fr.ring.personnage.*;

public class Duel {
	
	private Personnage p1;
	private Personnage p2;
	
	public Duel() {
	}
	
	public Duel(Personnage p1, Personnage p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Duel(Duel d){
		p1 = d.getP1();
		p2 = d.getP2();
	}
	
	public void initialise(){
		if(p2.getEXP()>p1.getEXP()){
			Personnage p1Temp = p1;
			p1 = p2;
			p2 = p1Temp;
		}
		else if(p1.getEXP() == p2.getEXP()){
			Random rand = new Random();
			if(rand.nextInt(2) == 0){
				Personnage p1Temp = p1;
				p1 = p2;
				p2 = p1Temp;
			}
		}
		if(p1 instanceof Guerrier)
			p1 = (Guerrier)p1;
		else if(p1 instanceof Chasseur)
			p1 = (Chasseur)p1;
		else 
			p1 = (Mage)p1;
		if(p2 instanceof Guerrier)
			p2 = (Guerrier)p2;
		else if(p1 instanceof Chasseur)
			p2 = (Chasseur)p2;
		else
			p2 = (Mage)p2;
	}

	public Personnage getP1() {
		return p1;
	}

	public void setP1(Personnage p1) {
		this.p1 = p1;
	}

	public Personnage getP2() {
		return p2;
	}

	public void setP2(Personnage p2) {
		this.p2 = p2;
	}

}

package fr.ring.jouerPartie;

import java.util.Random;

import fr.ring.capacites.*;
import fr.ring.personnage.*;

public class Duel {
	
	public static final int DEFENSE_IMMEDIATE = 0, DEFENSE_CALCULEE = 1, DEFENSE_ENCAISSE = 2;
	
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
	
	public void tourDeJeu(Personnage p, Capacite c){
		if(c instanceof Attaque){
			this.resolutionAttaque(p, c);
		}
		
		else if(c instanceof Defense){
			
		}
		
		else if(c instanceof Soin){
			this.resolutionSoin(p, c);
		}
		else
			p.capituler();
			
	}
	
	public double resolutionAttaque(Personnage p, Capacite c){
		double EFF = 0;
		if(c instanceof Arme){
			Arme a = new Arme((Arme)c);
			if(a.attaquer(p)){
				EFF = a.efficaciteAttaque(p);
			}
		}
		else if(c instanceof SortAttaque){
			SortAttaque s = new SortAttaque((SortAttaque)c);
			if(s.attaquer(p)){
				EFF = s.efficaciteAttaque(p);
			}
		}
		else if(c instanceof Bouclier){
			Bouclier b = new Bouclier((Bouclier)c);
			if(b.attaquer(p)){
				EFF = b.efficaciteAttaque(p);
			}
		}
		return EFF;		
	}
	
	public double resolutionDefense(Personnage p,double EFF_A, Capacite c, int decision){
		double EFF_D = 0;
		
		if(decision == DEFENSE_IMMEDIATE){
			if(c instanceof Bouclier){
				Bouclier b = new Bouclier((Bouclier)c);
				if(b.seDefendre(p)){
					EFF_D = b.efficaciteDefense(p);
				}
				if(p == this.p1)
					this.p2.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.p1.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
			}
			else if(c instanceof SortDefense){
				SortDefense s = new SortDefense((SortDefense)c);
				if(s.seDefendre(p)){
					EFF_D = s.efficaciteDefense(p);
				}
				if(p == this.p1)
					this.p2.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.p1.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
			}
			else if(c instanceof Arme){
				Arme a = new Arme((Arme)c);
				if(a.seDefendre(p)){
					EFF_D = a.efficaciteDefense(p);
				}
				if(p == this.p1)
					this.p2.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.p1.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
			}
		}
		else if(decision == DEFENSE_CALCULEE){
			if(c instanceof Bouclier){
				Bouclier b = new Bouclier((Bouclier)c);
				b.seDefendre(p);
				b.setPBA(b.getPBA() - b.getPBA()/4);
				if(b.seDefendre(p)){
					EFF_D = b.efficaciteDefense(p);
					EFF_D -= EFF_D/4;
				}
				if(p == this.p1)
					this.p2.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.p1.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
			}
			else if(c instanceof SortDefense){
				SortDefense s = new SortDefense((SortDefense)c);
				s.seDefendre(p);
				s.setPBA(s.getPBA() - s.getPBA()/4);
				if(s.seDefendre(p)){
					EFF_D = s.efficaciteDefense(p);
					EFF_D -= EFF_D/4;
				}
				if(p == this.p1)
					this.p2.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.p1.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
			}
			else if(c instanceof Arme){
				Arme a = new Arme((Arme)c);
				a.seDefendre(p);
				a.setPBA(a.getPBA() - a.getPBA()/4);
				if(a.seDefendre(p)){
					EFF_D = a.efficaciteDefense(p);
					EFF_D -= EFF_D/4;
				}
				if(p == this.p1)
					this.p2.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
				else
					this.p1.setVIT(this.p2.getVIT() - ((int)EFF_A - (int)EFF_D));
			}
		}
		else{
			if(p == this.p1)
				this.p2.setVIT(this.p2.getVIT() - (int)EFF_A);
			else
				this.p1.setVIT(this.p2.getVIT() - (int)EFF_A);
		}
		return EFF_D;
	}
	
	public double resolutionSoin(Personnage p, Capacite c){
		double EFF = 0;
		if(c instanceof SortSoin){
			SortSoin s = new SortSoin((SortSoin)c);
			if(s.seSoigner(p)){
				EFF = s.efficaciteSoin(p);
				p.setVIT(p.getVIT() + (int)EFF);
				if(p.getVIT()> 200 -(p.getFOR()+ p.getDEX() + p.getINT() + p.getCON()) + p.getEXP()*3)
					p.setVIT(200 -(p.getFOR()+ p.getDEX() + p.getINT() + p.getCON()) +  p.getEXP()*3);
			}
		}
		else if(c instanceof Remede){
			Remede r = new Remede((Remede)c);
			if(r.seSoigner(p)){
				EFF = r.efficaciteSoin(p);
				p.setVIT(p.getVIT() + (int)EFF);
			}
		}
		return EFF;
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

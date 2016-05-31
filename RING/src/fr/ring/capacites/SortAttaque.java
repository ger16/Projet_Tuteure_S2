package fr.ring.capacites;

import java.util.Random;

import fr.ring.personnage.Personnage;

public class SortAttaque extends Capacite implements Attaque {
	
	protected int PUI;
	protected int FAC;
	
	public SortAttaque() {
		super();
		PUI = 50;
		FAC = 50;
	}

	public SortAttaque(String nom, double pBA, double eFF, int PUI, int FAC) {
		super(nom, pBA, eFF);
		this.PUI = PUI;
		this.FAC = FAC;
	}

	public SortAttaque(SortAttaque s) {
		super(s);
		this.PUI = s.PUI;
		this.FAC = s.FAC;
	}


	@Override
	public boolean attaquer(Personnage attaquant) {
		PBA = attaquant.getINT() * FAC / 10000.0;
		double random = Math.random();
		if(random <= PBA)
			return true;
		return false;
	}

	@Override
	public double efficaciteAttaque(Personnage attaquant) {
		Random rand = new Random();
		EFF = attaquant.getCON() * PUI / 100.0;
		double effRand = EFF * 25 / 100.0;
		if(rand.nextInt(2) == 0)
			EFF -= Math.random() * effRand;
		else 
			EFF += Math.random() * effRand;
		return EFF;
	}
	
	@Override
	public boolean seDefendre(Personnage defenseur) {return false;}

	@Override
	public double efficaciteDefense(Personnage defenseur) {return 0;}

	@Override
	public boolean seSoigner(Personnage soigneur) {return false;}

	@Override
	public double efficaciteSoin(Personnage soigneur) {return 0;}

	public int getPUI() {
		return PUI;
	}

	public void setPUI(int pUI) {
		PUI = pUI;
	}

	public int getFAC() {
		return FAC;
	}

	public void setFAC(int fAC) {
		FAC = fAC;
	}

	@Override
	public String toString() {
		return "SortAttaque [PUI=" + PUI + ", FAC=" + FAC + ", nom=" + nom
				+ ", PBA=" + PBA + ", EFF=" + EFF + "]";
	}

}

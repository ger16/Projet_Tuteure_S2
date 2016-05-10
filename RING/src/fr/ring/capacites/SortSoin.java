package fr.ring.capacites;

import java.util.Random;

import fr.ring.personnage.Personnage;

public class SortSoin extends Capacite implements Soin {
	
	protected int PUI;
	protected int FAC;
	
	public SortSoin() {
		super();
		PUI = 50;
		FAC = 50;
	}

	public SortSoin(String nom, double pBA, double eFF, int PUI, int FAC) {
		super(nom, pBA, eFF);
		this.PUI = PUI;
		this.FAC = FAC;
	
	}

	public SortSoin(SortSoin s) {
		super(s);
		this.PUI = s.PUI;
		this.FAC = s.FAC;	
	}

	@Override
	public boolean seSoigner(Personnage soigneur) {
		PBA = soigneur.getINT() * FAC / 10000.0;
		double random = Math.random();
		if(random <= PBA)
			return true;
		return false;
	}

	@Override
	public double efficaciteSoin(Personnage soigneur) {
		Random rand = new Random();
		EFF = soigneur.getCON() * PUI / 100.0;
		double effRand = EFF * 25 / 100.0;
		if(rand.nextInt(2) == 0)
			EFF -= Math.random() * effRand;
		else 
			EFF += Math.random() * effRand;
		return EFF;
	}

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
		return "SortSoin [PUI=" + PUI + ", FAC=" + FAC + ", nom=" + nom
				+ ", PBA=" + PBA + ", EFF=" + EFF + "]";
	}

}

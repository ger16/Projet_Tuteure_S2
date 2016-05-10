package fr.ring.capacites;

import java.util.Random;

import fr.ring.personnage.Personnage;

public class Bouclier extends Capacite implements Defense, Attaque {
	protected int PRO;
	protected int MAN;
	
	public Bouclier() {
		super();
		PRO = 50;
		MAN = 50;
	}

	public Bouclier(String nom, double pBA, double eFF, int PRO, int MAN) {
		super(nom, pBA, eFF);
		this.PRO = PRO;
		this.MAN = MAN;
		
	}
	
	public Bouclier(Bouclier b) {
		super(b);
		this.PRO = b.PRO;
		this.MAN = b.MAN;
	}

	@Override
	public boolean attaquer(Personnage attaquant) {
		PBA = attaquant.getDEX() * MAN / 5000.0;
		double random = Math.random();
		if(random <= PBA)
			return true;
		return false;
	}

	@Override
	public double efficaciteAttaque(Personnage attaquant) {
		Random rand = new Random();
		EFF = attaquant.getFOR() * PRO / 50.0;
		double effRand = EFF * 25 / 100.0;
		if(rand.nextInt(2) == 0)
			EFF -= Math.random() * effRand;
		else 
			EFF += Math.random() * effRand;
		return EFF;
	}

	@Override
	public boolean seDefendre(Personnage defenseur) {
		PBA = defenseur.getDEX() * MAN / 10000.0;
		double random = Math.random();
		if(random <= PBA)
			return true;
		return false;
	}

	@Override
	public double efficaciteDefense(Personnage defenseur) {
		Random rand = new Random();
		EFF = defenseur.getFOR() * PRO / 100.0;
		double effRand = EFF * 25 / 100.0;
		if(rand.nextInt(2) == 0)
			EFF -= Math.random() * effRand;
		else 
			EFF += Math.random() * effRand;
		return EFF;
	}

	public int getPRO() {
		return PRO;
	}

	public void setPRO(int pRO) {
		PRO = pRO;
	}

	public int getMAN() {
		return MAN;
	}

	public void setMAN(int mAN) {
		MAN = mAN;
	}

	@Override
	public String toString() {
		return "Bouclier [PRO=" + PRO + ", MAN=" + MAN + ", nom=" + nom
				+ ", PBA=" + PBA + ", EFF=" + EFF + "]";
	}
}

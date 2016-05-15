package fr.ring.capacites;

import java.util.Random;

import fr.ring.personnage.Personnage;

public class Arme extends Capacite implements Attaque, Defense{
	protected int IMP;
	protected int MAN;
	
	public Arme() {
		super();
		IMP = 50;
		MAN = 50;
	}

	public Arme(String nom, double pBA, double eFF, int IMP, int MAN) {
		super(nom, pBA, eFF);
		this.IMP = IMP;
		this.MAN = MAN;
		
	}

	public Arme(Arme a) {
		super(a);
		this.IMP = a.IMP;
		this.MAN = a.MAN;		
	}

	@Override
	public boolean attaquer(Personnage attaquant) {
		PBA = attaquant.getDEX() * MAN / 10000.0;
		double random = Math.random();
		if(random <= PBA)
			return true;
		
		return false;
	}

	@Override
	public double efficaciteAttaque(Personnage attaquant) {
		Random rand = new Random();
		EFF = attaquant.getFOR() * IMP / 100.0;
		System.out.println(EFF);
		double effRand = EFF * 25 / 100.0;
		System.out.println(effRand);
		if(rand.nextInt(2) == 0)
			EFF -= Math.random() * effRand;
		else
			EFF += Math.random() * effRand;
		
		return EFF;
	}

	@Override
	public boolean seDefendre(Personnage defenseur) {
		PBA = defenseur.getDEX() * MAN / 5000.0;
		double random = Math.random();
		if(random <= PBA)
			return true;
		return false;
	}

	@Override
	public double efficaciteDefense(Personnage defenseur) {
		Random rand = new Random();
		EFF = defenseur.getFOR() * IMP / 50.0;
		double effRand = EFF * 25 / 100.0;
		if(rand.nextInt(2) == 0)
			EFF -= Math.random() * effRand;
		else 
			EFF += Math.random() * effRand;
		return EFF;
	}

	public int getIMP() {
		return IMP;
	}

	public void setIMP(int iMP) {
		IMP = iMP;
	}

	public int getMAN() {
		return MAN;
	}

	public void setMAN(int mAN) {
		MAN = mAN;
	}
	
	@Override
	public String toString() {
		return "Arme [IMP=" + IMP + ", MAN=" + MAN + ", nom=" + nom + ", PBA="
				+ PBA + ", EFF=" + EFF + "]";
	}

}

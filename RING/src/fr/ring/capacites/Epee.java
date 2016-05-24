package fr.ring.capacites;

public class Epee extends Arme {

	public Epee() {
		super();
	}

	public Epee(String nom, double pBA, double eFF, int IMP, int MAN) {
		super(nom, pBA, eFF, IMP, MAN);
	}

	public Epee(Arme a) {
		super(a);
	}

}

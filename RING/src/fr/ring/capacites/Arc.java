package fr.ring.capacites;

public class Arc extends Arme {

	public Arc() {
	}

	public Arc(String nom, double pBA, double eFF, int IMP, int MAN) {
		super(nom, pBA, eFF, IMP, MAN);
		}

	public Arc(Arc a) {
		super(a);
	}

}

package fr.ring.capacites;

public class Arc extends Arme {

	public static final int IMP_ARC = 60, MAN_ARC = 40;
	public static final String NOM_ARC = "Arc";
	
	public Arc() {
		nom = NOM_ARC;
		IMP = IMP_ARC;
		MAN = MAN_ARC;
	}

	public Arc(String nom, double pBA, double eFF, int IMP, int MAN) {
		super(nom, pBA, eFF, IMP, MAN);
		}

	public Arc(Arc a) {
		super(a);
	}

}

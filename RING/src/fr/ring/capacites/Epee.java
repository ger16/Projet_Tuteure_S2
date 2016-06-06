package fr.ring.capacites;

public class Epee extends Arme {
	public static final int IMP_EPEE = 60, MAN_EPEE = 40;
	public static final String NOM_EPEE = "Epee";
	
	public Epee() {
		this.nom = NOM_EPEE;
		this.IMP = IMP_EPEE;
		this.MAN = MAN_EPEE;
	}

	public Epee(String nom, double pBA, double eFF, int IMP, int MAN) {
		super(nom, pBA, eFF, IMP, MAN);
	}

	public Epee(Epee e) {
		super(e);
	}
	


}

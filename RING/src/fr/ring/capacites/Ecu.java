package fr.ring.capacites;

public class Ecu extends Bouclier {
	
	public static final int PRO_ECU = 70, MAN_ECU = 30;
	public static final String NOM_ECU = "Ecu";
	
	public Ecu() {
		this.nom = "Ecu";
		this.PRO = PRO_ECU;
		this.MAN = MAN_ECU;
	}

	public Ecu(String nom, double pBA, double eFF, int PRO, int MAN) {
		super(nom, pBA, eFF, PRO, MAN);
	}

	public Ecu(Bouclier b) {
		super(b);
	}

}

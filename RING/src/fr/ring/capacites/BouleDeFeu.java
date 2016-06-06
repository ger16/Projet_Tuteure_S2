package fr.ring.capacites;

public class BouleDeFeu extends SortAttaque {

	public static final int PUI_BOULE_DE_FEU = 70, FAC_BOULE_DE_FEU = 30;	
	public static final String NOM_BOULE_DE_FEU = "BouleDeFeu";
	
	public BouleDeFeu() {
		this.nom = NOM_BOULE_DE_FEU;
		this.PUI = PUI_BOULE_DE_FEU;
		this.FAC = FAC_BOULE_DE_FEU;
	}

	public BouleDeFeu(String nom, double pBA, double eFF, int PUI, int FAC) {
		super(nom, pBA, eFF, PUI, FAC);
	}

	public BouleDeFeu(BouleDeFeu b) {
		super(b);
	}

}

package fr.ring.capacites;

public class ToucheGuerrisseur extends SortSoin {
	
	public static final int PUI_TOUCHE_GUERRISSEUR = 60, FAC_TOUCHE_GUERRISSEUR = 40;
	
	public ToucheGuerrisseur() {
		this.nom = "Touche guerrisseur";
		this.PUI = PUI_TOUCHE_GUERRISSEUR;
		this.FAC = FAC_TOUCHE_GUERRISSEUR;
	}

	public ToucheGuerrisseur(String nom, double pBA, double eFF, int PUI,
			int FAC) {
		super(nom, pBA, eFF, PUI, FAC);
	}

	public ToucheGuerrisseur(ToucheGuerrisseur t) {
		super(t);
	}


}

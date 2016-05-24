package fr.ring.capacites;

public class ToucheGuerrisseur extends SortSoin {

	public ToucheGuerrisseur() {
		super();
	}

	public ToucheGuerrisseur(String nom, double pBA, double eFF, int PUI,
			int FAC) {
		super(nom, pBA, eFF, PUI, FAC);
	}

	public ToucheGuerrisseur(SortSoin s) {
		super(s);
	}

}

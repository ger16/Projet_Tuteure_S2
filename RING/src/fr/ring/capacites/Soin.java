package fr.ring.capacites;

import fr.ring.personnage.Personnage;

public interface Soin {
	public boolean seSoigner(Personnage soigneur);
	public double efficaciteSoin(Personnage soigneur);
}

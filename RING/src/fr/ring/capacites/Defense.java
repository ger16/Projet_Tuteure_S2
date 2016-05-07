package fr.ring.capacites;

import fr.ring.personnage.Personnage;

public interface Defense {
	public boolean seDefendre(Personnage defenseur);
	public double efficaciteDefense(Personnage defenseur);
}

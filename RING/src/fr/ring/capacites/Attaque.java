package fr.ring.capacites;

import fr.ring.personnage.Personnage;

public interface Attaque {
	public boolean attaquer(Personnage attaquant);
	public double efficaciteAttaque(Personnage attaquant);
}

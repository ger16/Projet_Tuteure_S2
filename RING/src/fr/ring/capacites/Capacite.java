package fr.ring.capacites;

import fr.ring.personnage.Personnage;

public abstract class Capacite{
	
	public static final double PBA_MIN = 0, PBA_MAX = 1;
	
	protected String nom;
	protected double PBA;
	protected double EFF;
	
	public Capacite(){
		nom = "Unnamed";
		PBA = 0;
		EFF = 0;
	}
	public Capacite(String nom, double pBA, double eFF) {
		this.nom = nom;
		PBA = pBA;
		EFF = eFF;
	}
	
	public Capacite(Capacite c){
		this.nom = c.getNom();
		this.PBA = c.PBA;
		this.EFF = c.EFF;
	}
	
	public abstract boolean attaquer(Personnage attaquant);
	public abstract double efficaciteAttaque(Personnage attaquant);
	public abstract boolean seDefendre(Personnage defenseur);
	public abstract double efficaciteDefense(Personnage defenseur);
	public abstract boolean seSoigner(Personnage soigneur);
	public abstract double efficaciteSoin(Personnage soigneur);
	
	public boolean containInterfaces(String name){
		for(int i=0; i < this.getClass().getSuperclass().getInterfaces().length; i++){
			if(this.getClass().getSuperclass().getInterfaces()[i].getName().contains(name))
				return true;
		}
		return false;
	}
	
	public Capacite getCap(String name){
		Capacite c;
		if(name == "Epee")
			c = new Epee();
		else if(name == "Ecu")
			c = new Ecu();
		else if(name == "Boule de feu")
			c =  new BouleDeFeu();
		else if(name == "Potion de soins mineure")
			c = new PotionSoinsMineure();
		else if(name == "Arc")
			c = new Arc();
		else if(name == "Protect")
			c = new Protect();
		else if(name == "Touche guerrisseur")
			c = new ToucheGuerrisseur();
		else
			c = new Arme();
			return c;
	}
	
	public double getPBA() {
		return PBA;
	}
	public void setPBA(double pBA) {
		PBA = pBA;
	}
	public double getEFF() {
		return EFF;
	}
	public void setEFF(double eFF) {
		EFF = eFF;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(EFF);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(PBA);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Capacite other = (Capacite) obj;
		if (Double.doubleToLongBits(EFF) != Double.doubleToLongBits(other.EFF))
			return false;
		if (Double.doubleToLongBits(PBA) != Double.doubleToLongBits(other.PBA))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Capacite [nom=" + nom + ", PBA=" + PBA + ", EFF=" + EFF + "]";
	}	
}

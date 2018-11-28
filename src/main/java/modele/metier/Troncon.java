package modele.metier;

public class Troncon {
	
	protected IntersectionNormal destination;
	protected double longueur;
	protected String nomRue;
	protected IntersectionNormal origine;
	
	public Troncon(IntersectionNormal destination, IntersectionNormal origine, double longueur, String nomRue) {
		this.destination = destination;
		this.longueur = longueur;
		this.nomRue = nomRue;
		this.origine = origine;
	}

	@Override
	public String toString() {
		return "Troncon [destination=" + destination + ", longueur=" + longueur + ", nomRue=" + nomRue + ", origine="
				+ origine + "]";
	}

	public IntersectionNormal getDestination() {
		return destination;
	}

	public void setDestination(IntersectionNormal destination) {
		this.destination = destination;
	}

	public double getLongueur() {
		return longueur;
	}

	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	public String getNomRue() {
		return nomRue;
	}

	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	public IntersectionNormal getOrigine() {
		return origine;
	}

	public void setOrigine(IntersectionNormal origine) {
		this.origine = origine;
	}
	
	
	
	
}

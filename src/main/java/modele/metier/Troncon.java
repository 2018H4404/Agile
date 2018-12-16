package modele.metier;

/** 
 * La classe du troncon.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class Troncon {
	
	protected IntersectionNormal destination;
	protected double longueur;
	protected String nomRue;
	protected IntersectionNormal origine;
	
	/**
	 * Constructeur parametre du troncon.
	 * @param destination : la destination du troncon.
	 * @param origine : l'origine du troncon.
	 * @param longueur : la longueur du troncon.
	 * @param nomRue : le nom de la rue du troncon.
	 */
	public Troncon(IntersectionNormal destination, IntersectionNormal origine, double longueur, String nomRue) {
		this.destination = destination;
		this.longueur = longueur;
		this.nomRue = nomRue;
		this.origine = origine;
	}

	/**
	 * Surcharge de la methode toString.
	 */
	@Override
	public String toString() {
		return "Troncon [destination=" + destination + ", longueur=" + longueur + ", nomRue=" + nomRue + ", origine="
				+ origine + "]";
	}

	/**
	 * Methode pour obtenir la destination de troncon.
	 * @return IntersectionNormal qui est la destination du troncon.
	 */
	public IntersectionNormal getDestination() {
		return destination;
	}

	
	/**
	 * Methode pour obtenir la longeur du troncon.
	 * @return la longueur du troncon.
	 */
	public double getLongueur() {
		return longueur;
	}

	
	/**
	 * Methode pour obtenir le nom de rue du troncon.
	 * @return le nom de rue.
	 */
	public String getNomRue() {
		return nomRue;
	}

	
	/**
	 * Methode pour obtenir l'origine de troncon.
	 * @return IntersectionNormal qui est l'origine du troncon.
	 */
	public IntersectionNormal getOrigine() {
		return origine;
	}

	
	
}

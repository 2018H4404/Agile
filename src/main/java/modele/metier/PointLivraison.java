package modele.metier;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.Interval;

/**
 * La classe point de livraison.
 * 
 * @author H4404
 * @version 1.0
 * @since 1.0
 */

public class PointLivraison extends Intersection {
	protected DateTime heureDepart;
	protected DateTime heureArrivee;
	private int duree;

	/**
	 * Constructeur parametre du point de livraison sans duree.
	 * 
	 * @param id : l'identifiant du point de livraison.
	 * @param latitude :  la latitude du point de livraison.
	 * @param longitude : la longitude du point de livraison.
	 */
	public PointLivraison(long id, double latitude, double longitude) {
		super(id, latitude, longitude);
		this.heureDepart = null;
		this.heureArrivee = null;
	}

	/**
	 * Constructeur parametre du point de livraison avec une duree.
	 * 
	 * @param id : l'identifiant du point de livraison.
	 * @param latitude :  la latitude du point de livraison.
	 * @param longitude : la longitude du point de livraison.
	 * @param uneDuree :  la duree du point de livraison.
	 */
	public PointLivraison(long id, double latitude, double longitude, int uneDuree) {
		super(id, latitude, longitude);
		this.heureDepart = null;
		this.heureArrivee = null;
		duree = uneDuree;
	}

	/**
	 * Methode pour obtenir la duree de ce point de livraison.
	 * @see Intersection.
	 */
	@Override
	public int getDuree() {
		return duree;
	}

	/**
	 * Surcharge de la methode toString de la classe Intersection.
	 */
	@Override
	public String toString() {
		return "PointLivraison " + " duree=" + duree + "s, id=" + id + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

}
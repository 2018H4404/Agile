package modele.metier;

/** 
 * La classe demande d'une intersection normale et non abstraite.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/


public class IntersectionNormal extends Intersection{
	
	/**
	 * Constructeur d'une intersection normale avec identifiant.
	 * @param id identifiant de l'intersection.
	 */
	public IntersectionNormal(long id) {
		super(id);
	}

	/**
	 * Constructeur parametre d'une intersection normale.
	 * @param id identifiant de l'intersection.
	 * @param latitude latitude de l'intersection.
	 * @param longitude longitude de l'intersection.
	 */
	public IntersectionNormal(long id, double latitude, double longitude) {
		super(id,latitude,longitude);
	}

	/**
	 * Surcharge de la methode toString de la classe Intersection.
	 * @see Intersection.
	 */
	@Override
	public String toString() {
		return "IntersectionNormal [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	/**
	 * Methode pour obtenir la duree de l'intersection correspondante.
	 * @see Intersection.
	 */
	public int getDuree() {return 0;}
	
}

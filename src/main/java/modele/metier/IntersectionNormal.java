package modele.metier;

/** 
 * La classe demande d'une intersection normale et non abstraite.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/


public class IntersectionNormal extends Intersection{
	
	/**
	 * Constructeur d'une intersection normal avec identifiant.
	 * @param id identifiant de l'intersection.
	 */
	public IntersectionNormal(long id) {
		super(id);
	}

	/**
	 * Constructeur parametre d'une intersection normal.
	 * @param id identifiant de l'intersection.
	 * @param latitude latitude de l'intersection.
	 * @param longitude longitude de l'intersection.
	 */
	public IntersectionNormal(long id, double latitude, double longitude) {
		super(id,latitude,longitude);
	}

	@Override
	public String toString() {
		return "IntersectionNormal [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	public int getDuree() {return 0;}
	
}

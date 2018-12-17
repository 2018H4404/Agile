package modele.metier;

/** 
 * La classe de l'intersection.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public abstract class Intersection {
	protected long id;
	protected double latitude;
	protected double longitude;
	
	/**
	 * Constructeur d'intersection par identifiant(sans latitude et longitude).
	 * @param id : identifiant de l'intersection.
	 */
	public Intersection(long id) {
		this.id = id;
	}
	
	/**
	 * Constructeur parametre de l'intersection(avec latitude et longitude).
	 * @param id : identifiant de l'intersection.
	 * @param latitude : latitude de l'intersection.
	 * @param longitude  :longitude de l'intersection.
	 */
	public Intersection(long id, double latitude, double longitude) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Surcharge de la methode toString de la classe Intersection.
	 */
	@Override
	public abstract String toString();
	
	/**
	 * Methode pour obtenir la duree de l'intersection correspondante.
	 * @return la duree de cette intersection.
	 */
	public abstract int getDuree();

	/**
	 * Methode pour obtenir l'id de l'intersection.
	 * @return id.
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Methode pour mettre l'id de l'intersection.
	 * @param id : une id.
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Methode pour obtenir la latitude de l'intersection.
	 * @return latitude de l'intersection.
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * Methode pour mettre la latitude de l'intersection.
	 * @param latitude : une latitude.
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * Methode pour obtenir la longitude de l'intersection.
	 * @return longitude de l'intersection.
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * Methode pour mettre la longitude de l'intersection.
	 * @param longitude : une longitude.
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Surcharge de la methode equals entre intersections.
	 * @param obj : l'intersection a comparer
	 * @return l'egalite entre deux intersections
	 */
	@Override
	public boolean equals(Object obj) {
		Intersection unInter = (Intersection)obj;
		boolean retour = false;
		if(this.id == unInter.getId()) {
			retour = true;
		}
		return retour;
	}
	
	/**
	 * Surcharge de la methode hashCode de la classe Intersection.
	 */
	@Override
	public int hashCode() {
		return Long.hashCode(id);
	}

	/**
	 * Surcharge de la methode equals entre intersections sur l'identifiant.
	 * @param unId : Id a comparer
	 * @return : l'egalite entre deux intersections
	 */
	public boolean equals(long unId) {
		boolean retour = false;
		if(this.id == unId) {
			retour = true;
		}
		return retour;	
	}
}

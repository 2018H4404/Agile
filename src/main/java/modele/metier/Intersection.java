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
	 * Constructeur d'intersection par identifiant.
	 * @param id identifiant de l'intersection.
	 */
	public Intersection(long id) {
		this.id = id;
	}
	
	/**
	 * Constructeur parametre de l'intersection.
	 * @param id identifiant de l'intersection.
	 * @param latitude latitude de l'intersection.
	 * @param longitude longitude de l'intersection.
	 */
	public Intersection(long id, double latitude, double longitude) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public abstract String toString();
	
	public abstract int getDuree();

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Surcharge de la methode equals entre intersections.
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
	
	@Override
	public int hashCode() {
		return (int)id;
	}

	/**
	 * Surcharge de la methode equals entre intersections sur l'identifiant.
	 */
	public boolean equals(long unId) {
		boolean retour = false;
		if(this.id == unId) {
			retour = true;
		}
		return retour;	
	}
}

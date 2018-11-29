package modele.metier;

import org.joda.time.DateTime;

/** 
 * La classe de l'entrepôt.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class Entrepot extends Intersection {
	
	private DateTime heureDepart;
	private DateTime heureArrivee;
	
	/**
	 * 
	 * @param id identifiant de l'entrepôt.
	 * @param latitude latitude de l'entrepôt.
	 * @param longitude longitude de l'entrepôt.
	 * @param heure heure du premier passage par l'entrepôt.
	 * @param minute minutes du premier passage par l'entrepôt.
	 * @param seconde secondes du premier passage par l'entrepôt.
	 */
	public Entrepot(long id, double latitude, double longitude, int heure, int minute, int seconde) {
		super(id,latitude,longitude);
		this.heureDepart = new DateTime(2018,11,30,heure,minute,seconde);
		this.heureArrivee = null;
	}

	@Override
	public String toString() {
		return "Entrepot [heureDepart=" + heureDepart + ", heureArrivee=" + heureArrivee + ", id=" + id + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}	
	
	public int getDuree() {return 0;}
	
}

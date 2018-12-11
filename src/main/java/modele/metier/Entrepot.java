package modele.metier;

import java.util.Date;

import org.joda.time.DateTime;

/** 
 * La classe de l'entrepôt.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class Entrepot extends Intersection {
	
	private DateTime heureDepart;
	
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
		Date sys = new Date();
		this.heureDepart = new DateTime(sys.getYear()+1900,sys.getMonth()+1,sys.getDate(),heure,minute,seconde);
	}

	@Override
	public String toString() {
		return "Entrepot [heureDepart=" + heureDepart + ", id=" + id + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
	
	/**
	 * Methode qui retourne l'heure du depart
	 */
	public DateTime getHeureDeaprt() {
		return heureDepart;
	}
	
	public int getDuree() {return 0;}
	
}

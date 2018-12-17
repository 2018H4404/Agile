package modele.metier;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * La classe de l'entrepot.
 * 
 * @author H4404
 * @version 1.0
 * @since 1.0
 */

public class Entrepot extends Intersection {

	private DateTime heureDepart;

	/**
	 * Constructeur de l'Entrepot.
	 * 
	 * @param id        : identifiant de l'entrepot.
	 * @param latitude  : latitude de l'entrepot.
	 * @param longitude : longitude de l'entrepot.
	 * @param heure     : heure du premier passage par l'entrepot.
	 * @param minute    : minute du premier passage par l'entrepot.
	 * @param seconde   : secondesdu premier passage par l'entrepot.
	 */
	@SuppressWarnings("deprecation")
	public Entrepot(long id, double latitude, double longitude, int heure, int minute, int seconde) {
		super(id, latitude, longitude);
		Date sys = new Date();
		this.heureDepart = new DateTime(sys.getYear() + 1900, sys.getMonth() + 1, sys.getDate(), heure, minute,
				seconde);

	}

	@Override
	public String toString() {
		return "Entrepot [heureDepart=" + heureDepart + ", id=" + id + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

	/**
	 * Methode qui retourne l'heure du depart
	 * @return l'heure depart
	 */
	public DateTime getHeureDeaprt() {
		return heureDepart;
	}

	/**
	 * @see modele.metier.Intersection
	 * @return duree
	 */
	public int getDuree() {
		return 0;
	}

}

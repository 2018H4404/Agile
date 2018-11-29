package modele.metier;


import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.Interval;

/** 
 * La classe point de livraison.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class PointLivraison extends Intersection{
	private IntersectionNormal monInter;
	protected DateTime heureDepart;
	protected DateTime heureArrivee;
	private int duree;
	
	/**
	 * Constructeur paramétré du point de livraison avec une durée.
	 * @param id l'identifiant du point de livraison.
	 * @param latitude la latitude du point de livraison.
	 * @param longitude la longitude du point de livraison.
	 * @param uneDuree la durée du point de livraison.
	 */
	public PointLivraison(long id, double latitude, double longitude, int uneDuree) {
		super(id,latitude,longitude);
		this.heureDepart = null;
		this.heureArrivee = null;
		duree = uneDuree;
	}
	
	/**
	 * Constructeur paramétré du point de livraison avec une heure de départ et une heure d'arrivée.
	 * @param id l'identifiant du point de livraison.
	 * @param latitude la latitude du point de livraison.
	 * @param longitude la longitude du point de livraison.
	 * @param heureDepart l'heure de départ du point de livraison.
	 * @param heureArrivee l'heure d'arrivée du point de livraison.
	 */
	public PointLivraison(long id, double latitude, double longitude, DateTime heureDepart,DateTime heureArrivee) {
		super(id,latitude,longitude);
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
		Period period = new Interval(heureDepart, heureArrivee).toPeriod();

		duree = period.getMinutes();
	}
	
	@Override
	public int getDuree() {
		return duree;
	}

	@Override
	public String toString() {
		return "PointLivraison [monInter=" + monInter + ", heureDepart=" + heureDepart + ", heureArrivee="
				+ heureArrivee + ", duree=" + duree + ", id=" + id + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
}

package modele.metier;


import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.Interval;

public class PointLivraison extends Intersection{
	private IntersectionNormal monInter;
	protected DateTime heureDepart;
	protected DateTime heureArrivee;
	private int duree;
	
	public PointLivraison(long id, double latitude, double longitude, int uneDuree) {
		super(id,latitude,longitude);
		this.heureDepart = null;
		this.heureArrivee = null;
		duree = uneDuree;
	}
	
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

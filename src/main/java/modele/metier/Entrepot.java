package modele.metier;

import org.joda.time.DateTime;

public class Entrepot extends Intersection{
	
	private DateTime heureDepart;
	private DateTime heureArrivee;
	
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

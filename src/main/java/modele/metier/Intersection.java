package modele.metier;

public abstract class Intersection {
	protected long id;
	protected double latitude;
	protected double longitude;
	
	public Intersection(long id) {
		this.id = id;
	}
	
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

	/*
	public boolean equals(Intersection unInter) {
		boolean retour = false;
		if(this.id == unInter.getId() && this.latitude == unInter.getLatitude() && this.longitude == unInter.getLongitude()) {
			retour = true;
		}
		return retour;	
	}*/
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
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

	public boolean equals(long unId) {
		boolean retour = false;
		if(this.id == unId) {
			retour = true;
		}
		return retour;	
	}
}

package modele.metier;

public class IntersectionNormal extends Intersection{
	
	public IntersectionNormal(long id) {
		super(id);
	}

	public IntersectionNormal(long id, double latitude, double longitude) {
		super(id,latitude,longitude);
	}

	@Override
	public String toString() {
		return "IntersectionNormal [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	public int getDuree() {return 0;}
	
}

package modele.metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

public class Plan extends Observable{

	private HashMap<Long,IntersectionNormal> intersectionNormals;
	private HashMap<Long,ArrayList<Troncon>> troncons;
	private double maxLong;
	private double minLong;
	private double maxLat;
	private double minLat;
	
	
	public Plan() {
		super();
		intersectionNormals = null;
		troncons = null;
	}
	
	public void initialiserPlan(HashMap<Long, IntersectionNormal> intersections, HashMap<Long,ArrayList<Troncon>> troncons, double maxLong, double minLong,
			double maxLat, double minLat) {
		if(this.intersectionNormals != null && this.troncons != null) {
			clear();
			System.out.println("Clear Plan");
		}
		this.intersectionNormals = intersections;
		this.troncons = troncons;
		this.maxLong = maxLong;
		this.minLong = minLong;
		this.maxLat = maxLat;
		this.minLat = minLat;
		setChanged();
		notifyObservers("Plan");
	}

	public void clear() {
		this.intersectionNormals.clear();
		this.troncons.clear();
	}
	
	public IntersectionNormal getIntersectionNormal(long id) {
		return this.intersectionNormals.get(id);
	}
	
	public ArrayList<Troncon> getTronconsParOrigine(long origine) {
		return this.troncons.get(origine);
	}

	public HashMap<Long, IntersectionNormal> getIntersectionNormals() {
		return intersectionNormals;
	}
	
	public Collection<IntersectionNormal> getAllIntersectionNormals() {
		return intersectionNormals.values();
	}
	
	public Collection<ArrayList<Troncon>> getAllTroncons() {
		return troncons.values();
	}

	public void setIntersectionNormals(HashMap<Long, IntersectionNormal> intersectionNormals) {
		this.intersectionNormals = intersectionNormals;
	}
	
	public HashMap<Long,ArrayList<Troncon>> getTroncons() {
		return troncons;
	}

	public void setTroncons(HashMap<Long,ArrayList<Troncon>> troncons) {
		this.troncons = troncons;
	}

	public double transformLongitude(double longitude, double largeur) {
		return (longitude - minLong) * largeur / (maxLong - minLong);
	}
	
	public double transformLatitude(double latitude, double hauteur) {
		return (maxLat - latitude) * hauteur / (maxLat - minLat);
	}
	
	
	
	
	
}

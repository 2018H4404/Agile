package modele.metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

/**
 * La classe du Plan.
 * 
 * @author H4404
 * @version 1.0
 * @since 1.0
 */

public class Plan extends Observable {

	private HashMap<Long, IntersectionNormal> intersectionNormals;
	private HashMap<Long, ArrayList<Troncon>> troncons;
	private double maxLong;
	private double minLong;
	private double maxLat;
	private double minLat;

	/**
	 * Constructeur par defaut du Plan.
	 */
	public Plan() {
		super();
		intersectionNormals = new HashMap<Long, IntersectionNormal>();
		troncons = new HashMap<Long, ArrayList<Troncon>>();
	}

	/**
	 * Methode permettant l'initialisation d'un plan.
	 * 
	 * @param intersections les intersections du plan.
	 * @param troncons      les troncons du plan.
	 * @param maxLong       la longitude maximale du plan.
	 * @param minLong       la longitude minimale du plan.
	 * @param maxLat        la latitude maximale du plan.
	 * @param minLat        la latitude minimale du plan.
	 */
	public void initialiserPlan(HashMap<Long, IntersectionNormal> intersections,
			HashMap<Long, ArrayList<Troncon>> troncons, double maxLong, double minLong, double maxLat, double minLat) {
		if (this.intersectionNormals != null && this.troncons != null) {
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

	/**
	 * Methode permettant d'effacer le plan.
	 */
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

	public HashMap<Long, ArrayList<Troncon>> getTroncons() {
		return troncons;
	}

	public double transformLongitude(double longitude, double largeur) {
		return (longitude - minLong) * largeur / (maxLong - minLong);
	}

	public double transformLatitude(double latitude, double hauteur) {
		return (maxLat - latitude) * hauteur / (maxLat - minLat);
	}

	public double reverseTransformLongitude(double longitudeTransforme, double largeur) {
		return longitudeTransforme * (maxLong - minLong) / largeur + minLong;
	}

	public double reverseTransformLatitude(double latitudeTransforme, double hauteur) {
		return maxLat - (latitudeTransforme * (maxLat - minLat) / hauteur);
	}
}

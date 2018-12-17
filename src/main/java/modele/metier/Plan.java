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
	 * @param intersections : les intersections d'un plan.
	 * @param troncons      : les troncons d'un plan.
	 * @param maxLong       : la longitude maximale d'un plan.
	 * @param minLong       : la longitude minimale d'un plan.
	 * @param maxLat        : la latitude maximale d'un plan.
	 * @param minLat        : la latitude minimale d'un plan.
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
	 * Methode permettant de liberer les intersections et les troncons dans le plan.
	 */
	public void clear() {
		this.intersectionNormals.clear();
		this.troncons.clear();
	}

	/**
	 * Methode pour obtenir une intersection dans le plan par Id.
	 * 
	 * @param id : l'id de l'intersection voulue.
	 * @return IntersectionNormal trouvee.
	 */
	public IntersectionNormal getIntersectionNormal(long id) {
		return this.intersectionNormals.get(id);
	}

	/**
	 * Methode pour obtenir un troncon selon l'id de l'origine.
	 * 
	 * @param origine : l'id de l'intersection etant l'origine.
	 * @return la liste des troncons trouves.
	 */
	public ArrayList<Troncon> getTronconsParOrigine(long origine) {
		return this.troncons.get(origine);
	}

	/**
	 * Methode pour obtenir le map des intersections.
	 * 
	 * @return le map des intersections.
	 */
	public HashMap<Long, IntersectionNormal> getIntersectionNormals() {
		return intersectionNormals;
	}

	/**
	 * Methode pour obtenir la Collection des intersections.
	 * 
	 * @return la Collection des intersections.
	 */
	public Collection<IntersectionNormal> getAllIntersectionNormals() {
		return intersectionNormals.values();
	}

	/**
	 * Methode pour obtenir la Collection des troncons.
	 * 
	 * @return la Collection des troncons.
	 */
	public Collection<ArrayList<Troncon>> getAllTroncons() {
		return troncons.values();
	}

	/**
	 * Methode pour obtenir le map des troncons.
	 * 
	 * @return le map des troncons.
	 */
	public HashMap<Long, ArrayList<Troncon>> getTroncons() {
		return troncons;
	}

	/**
	 * Methode pour transformer la longitude en une coordonnee Y selon ce plan.
	 * 
	 * @param longitude : une longitude.
	 * @param largeur   : la largeur de la vue graphique.
	 * @return la coordonnee Y calculee.
	 */
	public double transformLongitude(double longitude, double largeur) {
		return (longitude - minLong) * largeur / (maxLong - minLong);
	}

	/**
	 * Methode pour transformer la latitude en une coordonnee X selon ce plan.
	 * 
	 * @param latitude : une lattitude.
	 * @param hauteur  : la hauteur de la vue graphique.
	 * @return la coordonnee X calculee.
	 */

	public double transformLatitude(double latitude, double hauteur) {
		return (maxLat - latitude) * hauteur / (maxLat - minLat);
	}

	/**
	 * Methode pour reverser le processus de la methode transformerLongitude.
	 * 
	 * @param longitudeTransforme : une coordonnee X.
	 * @param largeur             : la largeur de la vue graphique.
	 * @return la longitude calculee.
	 */
	public double reverseTransformLongitude(double longitudeTransforme, double largeur) {
		return longitudeTransforme * (maxLong - minLong) / largeur + minLong;
	}

	/**
	 * Methode pour reverser le processus de la methode transformerLatitude.
	 * 
	 * @param latitudeTransforme : la coordonne Y.
	 * @param hauteur            : la hauteur de la vue graphique.
	 * @return la latitude calculee.
	 */
	public double reverseTransformLatitude(double latitudeTransforme, double hauteur) {
		return maxLat - (latitudeTransforme * (maxLat - minLat) / hauteur);
	}
}

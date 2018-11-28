package modele.metier;

import java.util.ArrayList;

/** 
 * La classe Chemin.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class Chemin {
	
	private ArrayList <Intersection> listeIntersections;
	private ArrayList <Troncon> listeTroncons;
	
	/**
	 * Constructeur paramétré de chemin.
	 * @param uneListeIntersections une liste d'intersections.
	 * @param uneListeTroncons 
	 */
	public Chemin(ArrayList <Intersection> uneListeIntersections, ArrayList <Troncon> uneListeTroncons) {
		this.listeIntersections = uneListeIntersections;
		this.listeTroncons = uneListeTroncons;
	}
	
	public ArrayList<Intersection> getListeIntersections() {
		return listeIntersections;
	}
	
	public ArrayList<Troncon> getListeTroncons(){
		return listeTroncons;
	}
	
	/**
	 * Retourne le cout du chemin.
	 * @return un double qui représente le cout du chemin.
	 */
	public double getCout() {
		double somme = 0.0;
		for(Troncon troncon : listeTroncons) {
			somme += troncon.getLongueur();
		}
		return somme;
	}
}

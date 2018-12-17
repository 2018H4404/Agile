package modele.metier;

import java.util.ArrayList;

/** 
 * La classe du chemin.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class Chemin {
	
	private ArrayList <Intersection> listeIntersections;
	private ArrayList <Troncon> listeTroncons;
	private int duree;
	
	/**
	 * Constructeur parametre du chemin.
	 * @param uneListeIntersections : une liste d'Intersections.
	 * @param uneListeTroncons : une liste des Troncons.
	 */
	public Chemin(ArrayList <Intersection> uneListeIntersections, ArrayList <Troncon> uneListeTroncons) {
		this.listeIntersections = uneListeIntersections;
		this.listeTroncons = uneListeTroncons;
	}
	
	//Getter et Setter
	public ArrayList<Intersection> getListeIntersections() {
		return listeIntersections;
	}
	
	public ArrayList<Troncon> getListeTroncons(){
		return listeTroncons;
	}
	
	public int getDuree() {
		return duree;
	}
	
	public void setDuree(int uneDuree) {
		this.duree = uneDuree;
	}
	
	/**
	 * Methode pour obtenir l'origine du chemin.
	 * @return Intersection qui est l'origine du chemin.
	 */
	public Intersection getIntersectionDepart() {
		return listeIntersections.get(0);
	}
	
	/**
	 * Methode pour obtenir la destination du chemin.
	 * @return Intersection qui est la destination du chemin.
	 */
	public Intersection getIntersectionDest() {
		return listeIntersections.get(listeIntersections.size()-1);
	}
	
	/**
	 * Methode pour obtenir le cout du chemin.
	 * @return un double(la distance) qui represente le cout du chemin.
	 */
	public double getCout() {
		double somme = 0.0;
		for(Troncon troncon : listeTroncons) {
			somme += troncon.getLongueur();
		}
		return somme;
	}
}


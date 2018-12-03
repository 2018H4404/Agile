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
	 * Constructeur paramétré du chemin.
	 * @param uneListeIntersections une liste d'intersections.
	 * @param uneListeTroncons 
	 */
	public Chemin(ArrayList <Intersection> uneListeIntersections, ArrayList <Troncon> uneListeTroncons) {
		this.listeIntersections = uneListeIntersections;
		this.listeTroncons = uneListeTroncons;
	}
	
	/**
	 * Constructeur paramétré du chemin.
	 * @param uneListeIntersections une liste d'intersections.
	 * @param uneListeTroncons 
	 * @param uneDuree la duree utilisee pour parcourir ce chemin
	 */
	public Chemin(ArrayList <Intersection> uneListeIntersections, ArrayList <Troncon> uneListeTroncons, int uneDuree) {
		this.listeIntersections = uneListeIntersections;
		this.listeTroncons = uneListeTroncons;
		this.duree = uneDuree;
	}
	
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
	 * Méthode pour avoir l'origine du chemin.
	 * @return Intersection qui est l'origine du chemin.
	 */
	public Intersection getIntersectionDepart() {
		return listeIntersections.get(0);
	}
	
	/**
	 * Méthode pour avoir la destination du chemin.
	 * @return Intersection qui est la destination du chemin.
	 */
	public Intersection getIntersectionDest() {
		return listeIntersections.get(listeIntersections.size()-1);
	}
	
	/**
	 * Méthode pour avoir le cout du chemin.
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


package modele.algo;

import java.util.ArrayList;
import java.util.Collection;

import modele.metier.Chemin;
import modele.metier.DemandeLivraison;
import modele.metier.Entrepot;
import modele.metier.Plan;
import modele.metier.PointLivraison;
import modele.metier.Troncon;
import modele.metier.Intersection;

public class OutilTSP {
	
	/**
	 * Méthode qui retourne toutes les intersections des demandes.
	 * @param demande les demande de livraison.
	 * @return la liste des demandes de livraison.
	 */
	public static ArrayList<Intersection> getAllIntersectionDemande(DemandeLivraison demande){
		Collection<Entrepot> entrepots = demande.getAllEntrepots();
		Collection<PointLivraison> livraisons = demande.getAllPointLivraisons();
		ArrayList<Intersection> retour = new ArrayList<Intersection>();
		for(Entrepot e : entrepots) {
			retour.add(e);
		}
		for(PointLivraison p : livraisons) {
			retour.add(p);
		}
		return retour;
	}
	
	/**
	 * Méthode pour initialiser le cout de la demande par TSP.
	 * @param unPlan le plan de la ville.
	 * @param intersectionsDemande les intersections de la demande.
	 * @param cout le cout du chemin.
	 * @param pccs le plus court chemin.
	 * @param length la longueur du chemin.
	 */
	public static void initialisationTabCoutEtChemin(Plan unPlan, ArrayList<Intersection> intersectionsDemande, int[][] cout, Chemin[][] pccs, int length){
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length; j++) {
				if(i == j) {
					cout[i][j] = 0;
					pccs[i][j] = null;
				}else {
					ArrayList<Intersection> pcc = AEtoile.getInstance().algoAEtoile(intersectionsDemande.get(i), intersectionsDemande.get(j), unPlan);
					ArrayList<Troncon> pccTroncons = AEtoile.getInstance().traductionTrajet(pcc, unPlan);
					Chemin tempChemin = new Chemin(pcc,pccTroncons);
					cout[i][j] = (int)(tempChemin.getCout()/15000 * 60 * 60);
					pccs[i][j] = tempChemin;
				}
			}
		}
	}
	
	/**
	 * Méthode pour initialiser la durée de la demande par TSP.
	 * @param intersectionsDemande la liste des intersections des demandes.
	 * @param duree la durée des demandes.
	 * @param length la longueur des demandes.
	 */
	//Initialisation du tableau de duree demande par TSP
	public static void intialisationTabDuree( ArrayList<Intersection> intersectionsDemande, int[] duree, int length) {
		duree[0] = 0;
		for(int i = 1; i < length; i++) {
			duree[i] = intersectionsDemande.get(i).getDuree();
		}
	}
}

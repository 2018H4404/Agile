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
	 * Methode qui retourne toutes les intersections d'une demande de livraison.
	 * @param demande : la demande de livraison.
	 * @return la liste qui contient l'entrepot et les points de livraison dans la demande passee.
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
	 * Methode pour initialiser les couts(en secondes) des chemins 
	 * entre l'entrepot et les points de livraison de la demande pour TSP.
	 * @param unPlan : le plan de la ville.
	 * @param intersectionsDemande : les intersections de la demande.
	 * @param cout : les couts calcules des chemins.
	 * @param pccs : les plus courts chemins trouves.
	 * @param length : le nombre de intersections dans la demande(entrepot et point de livraison).
	 */
	public static void initialisationTabCoutEtChemin(Plan unPlan, ArrayList<Intersection> intersectionsDemande, int[][] cout, Chemin[][] pccs, int length) throws Exception{
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
					tempChemin.setDuree(cout[i][j]);
					pccs[i][j] = tempChemin;
				}
			}
		}
	}
	
	/**
	 * Methode pour initialiser la duree de la demande de livraison pour TSP.
	 * @param intersectionsDemande : la liste des intersections des demandes.
	 * @param duree : les durees trouvees pour la demande passee.
	 * @param length : le nombre de intersections dans la demande(entrepot et point de livraison).
	 */
	public static void intialisationTabDuree( ArrayList<Intersection> intersectionsDemande, int[] duree, int length) {
		duree[0] = 0;
		for(int i = 1; i < length; i++) {
			duree[i] = intersectionsDemande.get(i).getDuree();
		}
	}
}

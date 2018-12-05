package modele;


import modele.metier.Chemin;
import modele.metier.DemandeLivraison;
import modele.metier.Intersection;
//import modele.metier.IntersectionNormal;
import modele.metier.Plan;
import modele.metier.Tournee;
import modele.algo.OutilTSP;
import modele.algo.TSPSimple;

import java.util.Observable;
import java.util.ArrayList;

/** 
 * La classe du tournée manager.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class TourneeManager extends Observable{
	
	private ArrayList <Tournee> listeTournees;
	private static final int TIME_LIMITE = 10000;
	
	public TourneeManager() {
		listeTournees = new ArrayList<Tournee>();
	}
	
	public void calculTournees(Plan plan){
		listeTournees = Algo(plan);
		
	}
	
	public ArrayList<Tournee> Algo(Plan plan){
		ArrayList<Tournee> res = null;
		return res;
	}
	
	public void clear() {
		this.listeTournees.clear();
	}
	
	/**
	 * Méthode pour calculer les tournées d'un seul livreur.
	 * @param demande les demandes de livraison.
	 * @param unPlan le plan de la ville.
	 */
	public void calculerLesTournees(DemandeLivraison demande, Plan unPlan, int nbLivreur) {
		clear();
		ArrayList<Intersection> intersectionsDemande = OutilTSP.getAllIntersectionDemande(demande);
		//Initialisation des parametres importants
		int length = intersectionsDemande.size();
		int[][] cout = new int[length][length];
		Chemin[][] pccs = new Chemin[length][length];
		int[] duree = new int[length];
		OutilTSP.initialisationTabCoutEtChemin(unPlan, intersectionsDemande, cout, pccs, length);
		OutilTSP.intialisationTabDuree(intersectionsDemande, duree, length);
		TSPSimple tsp = new TSPSimple();
		Integer[] meilleureSolution = new Integer[length+nbLivreur];
		tsp.chercheSolution(TIME_LIMITE, length, cout, duree,nbLivreur);
		if(tsp.getTempsLimiteAtteint()) {
			//Prendre la meilleureSolution calculee
			for(int i = 0; i < length+nbLivreur-1; i++) {
				meilleureSolution[i] = tsp.getMeilleureSolution(i);
			}
			meilleureSolution[length+nbLivreur-1] = 0;
			
			for(int i = 0; i < length+nbLivreur; i ++) {
				System.out.println(meilleureSolution[i]);
			}
			ArrayList<Integer> positionEntrepots = trouverPositionsEntrepot(meilleureSolution);	
			int nbPositionEntrepots = positionEntrepots.size()-1;
			for(int i = 0; i < nbPositionEntrepots; i++) {
				int positionStart = positionEntrepots.get(i);
				int positionEnd = positionEntrepots.get(i+1);
				ArrayList<Chemin> listeSolution = new ArrayList<Chemin>();
				for(int j = positionStart; j < positionEnd; j++) {
					listeSolution.add(pccs[meilleureSolution[j]][meilleureSolution[j+1]]);
				}
				Tournee solution = new Tournee(listeSolution);
				this.listeTournees.add(solution);
			}
			System.out.println(tsp.getCoutMeilleureSolution());
			setChanged(); 
			notifyObservers("Alert Temps");
		}else {
			//Prendre la meilleureSolution calculee
			for(int i = 0; i < length+nbLivreur-1; i++) {
				meilleureSolution[i] = tsp.getMeilleureSolution(i);
			}
			meilleureSolution[length+nbLivreur-1] = 0;
			
			for(int i = 0; i < length+nbLivreur; i ++) {
				System.out.println(meilleureSolution[i]);
			}
			ArrayList<Integer> positionEntrepots = trouverPositionsEntrepot(meilleureSolution);	
			int nbPositionEntrepots = positionEntrepots.size()-1;
			for(int i = 0; i < nbPositionEntrepots; i++) {
				int positionStart = positionEntrepots.get(i);
				int positionEnd = positionEntrepots.get(i+1);
				ArrayList<Chemin> listeSolution = new ArrayList<Chemin>();
				for(int j = positionStart; j < positionEnd; j++) {
					listeSolution.add(pccs[meilleureSolution[j]][meilleureSolution[j+1]]);
				}
				Tournee solution = new Tournee(listeSolution);
				this.listeTournees.add(solution);
			}
			System.out.println(tsp.getCoutMeilleureSolution());
			setChanged();
			notifyObservers("Tournees");
		}
		
	}
	
	private ArrayList<Integer> trouverPositionsEntrepot(Integer[] meilleureSolution){
		ArrayList<Integer> retour = new ArrayList<Integer>();
		for(Integer i = 0; i < meilleureSolution.length; i++) {
			if(meilleureSolution[i] == 0) {
				retour.add(i);
			}
		}
		return retour;
	}

	public ArrayList<Tournee> getListeTournees() {
		return listeTournees;
	}

	public void setListeTournees(ArrayList<Tournee> listeTournees) {
		this.listeTournees = listeTournees;
	}
	
	
}

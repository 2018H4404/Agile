package modele;


import modele.metier.Chemin;
import modele.metier.DemandeLivraison;
import modele.metier.Entrepot;
import modele.metier.Intersection;
import modele.metier.IntersectionNormal;
//import modele.metier.IntersectionNormal;
import modele.metier.Plan;
import modele.metier.PointLivraison;
import modele.metier.Tournee;
import modele.metier.Troncon;
import modele.algo.AEtoile;
import modele.algo.OutilTSP;
import modele.algo.TSPSimple;

import java.util.Observable;

import controleur.Controleur;

import java.util.ArrayList;

/** 
 * La classe du tourn��e manager.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class TourneeManager extends Observable{
	
	private ArrayList <Tournee> listeTournees;
	private static final int TIME_LIMITE = 10000;
	private int tourneeChangedIndex;
	private int tourneeSupprimerIndex;
	private int tourneeAjouterIndex;
	
	public TourneeManager() {
		listeTournees = new ArrayList<Tournee>();
		tourneeChangedIndex = 0;
		tourneeSupprimerIndex = 0;
		tourneeAjouterIndex = 0;
	}
	
	public void calculTournees(Plan plan){
		listeTournees = Algo(plan);
		
	}
	
	public ArrayList<Tournee> Algo(Plan plan){
		ArrayList<Tournee> res = null;
		return res;
	}
	
	public Tournee getDernierTournee() {
		return listeTournees.get(listeTournees.size()-1);
	}
	
	public void clear() {
		this.listeTournees.clear();
	}
	
	/**
	 * M��thode pour retourner l'index de la tourn��e chang��e
	 */
	public int getTourneeChangedIndex() {
		return tourneeChangedIndex;
	}
	
	/**
	 * M��thode pour retourner l'index de la tourn��e o�� nous avons ajout�� un point de livraison apr��s le d��placement
	 */
	public int getTourneeAjouterIndex() {
		return tourneeAjouterIndex;
	}
	/**
	 * M��thode pour retourner l'index de la tourn��e o�� nous avons supprim�� un point de livraison apr��s le d��placement
	 */
	public int getTourneeSupprimerIndex() {
		return tourneeSupprimerIndex;
	}

	/**
	 * M��thode pour calculer les tourn��es selon le nombre de livreur (Version sans clustering)
	 * @param demande les demandes de livraison.
	 * @param unPlan le plan de la ville.
	 */
	public void calculerLesTournees(DemandeLivraison demande, Plan unPlan, int nbLivreur) throws Exception {
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
	
	/**
	 * M��thode pour calculer les tourn��es selon le nombre de livreur (Version clustering).
	 * @param demande les demandes de livraison.
	 * @param unPlan le plan de la ville.
	 */
	public void calculerLesTourneesClustering(DemandeLivraison demande, Plan unPlan, int nbLivreur) throws Exception{
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
		int[] nbPointLivraisonParLivreur = tsp.clusteringNbPointLivraisonParLivreurNaive(nbLivreur, length-1);
		ArrayList<int[]> groupes = tsp.clusteringPointLivraisonNaive(length, cout, nbPointLivraisonParLivreur);
		for(int i = 0; i < groupes.size(); i++) {
			int[] temp = groupes.get(i);
			for(int j = 0; j < temp.length; j++) {
				System.out.println(temp[j]);
			}
			System.out.println("");
		}
		int nbGroupes = groupes.size();
		boolean alert = false;
		for(int i = 0; i < nbGroupes; i++) {
			ArrayList<Intersection> temp = new ArrayList<Intersection>();
			temp.add(intersectionsDemande.get(0));
			int[] tempGroupes = groupes.get(i);
			for(int j = 0; j < tempGroupes.length; j++) {
				temp.add(intersectionsDemande.get(tempGroupes[j]));
			}
			int tempLength = temp.size();
			int[][] tempCout = new int[tempLength][tempLength];
			Chemin[][] tempPccs = new Chemin[tempLength][tempLength];
			int[] tempDuree = new int[tempLength];
			OutilTSP.initialisationTabCoutEtChemin(unPlan, temp, tempCout, tempPccs, tempLength);
			OutilTSP.intialisationTabDuree(temp, tempDuree, tempLength);
			TSPSimple tempTsp = new TSPSimple();
			Integer[] meilleureSolution = new Integer[tempLength+1];
			tempTsp.chercheSolution(TIME_LIMITE, tempLength, tempCout, tempDuree,1);
			if(tempTsp.getTempsLimiteAtteint()) {
				alert = true;
				for(int p = 0; p < tempLength; p++) {
					meilleureSolution[p] = tempTsp.getMeilleureSolution(p);
				}
				meilleureSolution[tempLength] = 0;
				
				for(int p = 0; p < tempLength+1; p++) {
					System.out.println(meilleureSolution[p]);
				}
				ArrayList<Integer> positionEntrepots = trouverPositionsEntrepot(meilleureSolution);	
				int nbPositionEntrepots = positionEntrepots.size()-1;
				for(int p = 0; p < nbPositionEntrepots; p++) {
					int positionStart = positionEntrepots.get(p);
					int positionEnd = positionEntrepots.get(p+1);
					ArrayList<Chemin> listeSolution = new ArrayList<Chemin>();
					for(int j = positionStart; j < positionEnd; j++) {
						listeSolution.add(tempPccs[meilleureSolution[j]][meilleureSolution[j+1]]);
					}
					Tournee solution = new Tournee(listeSolution);
					this.listeTournees.add(solution);
				}
			}else {
				for(int p = 0; p < tempLength; p++) {
					meilleureSolution[p] = tempTsp.getMeilleureSolution(p);
				}
				meilleureSolution[tempLength] = 0;
				
				for(int p = 0; p < tempLength+1; p++) {
					System.out.println(meilleureSolution[p]);
				}
				ArrayList<Integer> positionEntrepots = trouverPositionsEntrepot(meilleureSolution);	
				int nbPositionEntrepots = positionEntrepots.size()-1;
				for(int p = 0; p < nbPositionEntrepots; p++) {
					int positionStart = positionEntrepots.get(p);
					int positionEnd = positionEntrepots.get(p+1);
					ArrayList<Chemin> listeSolution = new ArrayList<Chemin>();
					for(int j = positionStart; j < positionEnd; j++) {
						listeSolution.add(tempPccs[meilleureSolution[j]][meilleureSolution[j+1]]);
					}
					Tournee solution = new Tournee(listeSolution);
					this.listeTournees.add(solution);
				}
			}
			
		}
		if(alert) {
			setChanged();
			notifyObservers("Alert Temps");
		}else {
			setChanged();
			notifyObservers("Tournees");
		}
		
	}
	
	private ArrayList<Integer> trouverPositionsEntrepot(Integer[] meilleureSolution) throws Exception{
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

	public void setListeTournees(ArrayList<Tournee> listeTournees)  {
		this.listeTournees = listeTournees;
	}
	
	/**
	 * M��thode pour ajouter un point de livraison dans une tourn��e sp��cifi��e.
	 * @param idDepart : id du point de livraison .
	 * @param unPlan le plan de la ville.
	 */
	public void ajouterPointLivraison(long idDepart, long idNouvelle, int duree) throws Exception{
		int find = 0;
		int index = 0;
		int posChemin = 0;
		for(Tournee t : listeTournees) {
			if(find == 0) {
				posChemin = 0;
				ArrayList<Chemin> tempChemin = t.getListeChemins();
				for(Chemin c : tempChemin) {
					Intersection depart = c.getIntersectionDepart();
					Intersection dest = c.getIntersectionDest();
					if(depart.equals(idDepart)) {
						find = 1;
						break;
					}
					if(dest.equals(idDepart)) {
						find = 1;
						break;
					}
					posChemin++;
				}
			}else {
				break;
			}
			index++;
		}
		if(find == 1) {
			index--;
			posChemin++;
			Intersection depart = listeTournees.get(index).getListeChemins().get(posChemin).getIntersectionDepart();
			Intersection oldDest = listeTournees.get(index).getListeChemins().get(posChemin).getIntersectionDest();
			IntersectionNormal tempNouvellePoint = Controleur.getInstance().getMonPlan().getIntersectionNormal(idNouvelle);
			Controleur.getInstance().getMaDemande().ajouterPointLivraisonMetier(idNouvelle,tempNouvellePoint.getLatitude(),
					tempNouvellePoint.getLongitude(),duree);
			Intersection newDest = Controleur.getInstance().getMaDemande().getPointLivraisonParId(idNouvelle);
			ArrayList<Intersection> interdepartNewDest = AEtoile.getInstance().algoAEtoile(depart, newDest, Controleur.getInstance().getMonPlan());
			ArrayList<Intersection> inetrnewDestOldDest = AEtoile.getInstance().algoAEtoile(newDest, oldDest, Controleur.getInstance().getMonPlan());
			ArrayList<Troncon> tronDepartNewDest = AEtoile.getInstance().traductionTrajet(interdepartNewDest, Controleur.getInstance().getMonPlan());
			ArrayList<Troncon> tronNewDestOldDest = AEtoile.getInstance().traductionTrajet(inetrnewDestOldDest, Controleur.getInstance().getMonPlan());
			Chemin departNewDest = new Chemin(interdepartNewDest,tronDepartNewDest);
			Chemin newDestOldDest = new Chemin(inetrnewDestOldDest,tronNewDestOldDest);
			int newDestDuree = (int)(departNewDest.getCout()/15000 * 60 * 60);
			int oldDestDuree = (int)(newDestOldDest.getCout()/15000 * 60 * 60);
			departNewDest.setDuree(newDestDuree);
			newDestOldDest.setDuree(oldDestDuree);
			listeTournees.get(index).getListeChemins().remove(posChemin);
			listeTournees.get(index).getListeChemins().add(posChemin, departNewDest);
			listeTournees.get(index).getListeChemins().add(posChemin+1, newDestOldDest);
			tourneeChangedIndex = index;
			setChanged();
			notifyObservers("UniqueTournee");
		}else {
			System.out.println("Point Livraison Introuvable");
			Exception e = new Exception();
			throw e;
		}
	}
	
	public void supprimerPointLivraison(long id) throws Exception{
		int find = 0;
		int index = 0;
		int posChemin = 0;
		for(Tournee t : listeTournees) {
			if(find == 0) {
				posChemin = 0;
				ArrayList<Chemin> tempChemin = t.getListeChemins();
				for(Chemin c : tempChemin) {
					Intersection depart = c.getIntersectionDepart();
					Intersection dest = c.getIntersectionDest();
					if(depart.equals(id)) {
						find = 1;
						break;
					}
					if(dest.equals(id)) {
						find = 1;
						break;
					}
					posChemin++;
				}
			}else {
				break;
			}
			index++;
		}
		if(find == 1) {
			index--;
			int posOneEnleve = posChemin;
			int posTwoEnleve = posChemin + 1;
			Intersection newDepart = listeTournees.get(index).getListeChemins().get(posOneEnleve).getIntersectionDepart();
			Intersection newDest = listeTournees.get(index).getListeChemins().get(posTwoEnleve).getIntersectionDest();
			ArrayList<Intersection> internewDepartNewDest = AEtoile.getInstance().algoAEtoile(newDepart, newDest, Controleur.getInstance().getMonPlan());
			ArrayList<Troncon> tronNewDestNewDest = AEtoile.getInstance().traductionTrajet(internewDepartNewDest, Controleur.getInstance().getMonPlan());
			Chemin newDepartNewDest = new Chemin(internewDepartNewDest,tronNewDestNewDest);
			int newDuree = (int)(newDepartNewDest.getCout()/15000 * 60 * 60);
			newDepartNewDest.setDuree(newDuree);
			Controleur.getInstance().getMaDemande().supprimerPointLivraisonMetier(id);
			if(newDepart instanceof Entrepot && newDest instanceof Entrepot) {
				System.out.println("Empty");
				listeTournees.remove(index);
				tourneeChangedIndex = index;
				setChanged();
				notifyObservers("SupprimerTournee");
			}else {
				listeTournees.get(index).getListeChemins().remove(posTwoEnleve);
				listeTournees.get(index).getListeChemins().remove(posOneEnleve);
				listeTournees.get(index).getListeChemins().add(posChemin, newDepartNewDest);
				tourneeChangedIndex = index;
				setChanged();
				notifyObservers("UniqueTournee");
			}
		}else {
			System.out.println("Point Livraison Introuvable");
			Exception e = new Exception();
			throw e;
		}
	}

	public void deplacerPointLivraison(long idADeplacer, long idApresDeplacer) throws Exception{
		//Trouver o�� se situe le point �� d��placer dans la liste des tourn��es
		int findADplacer = 0;
		int indexADeplacer = 0;
		int posCheminADeplacer = 0;
		for(Tournee t : listeTournees) {
			if(findADplacer == 0) {
				posCheminADeplacer = 0;
				ArrayList<Chemin> tempChemin = t.getListeChemins();
				for(Chemin c : tempChemin) {
					Intersection depart = c.getIntersectionDepart();
					Intersection dest = c.getIntersectionDest();
					if(depart.equals(idADeplacer)) {
						findADplacer = 1;
						break;
					}
					if(dest.equals(idADeplacer)) {
						findADplacer = 1;
						break;
					}
					posCheminADeplacer++;
				}
			}else {
				break;
			}
			indexADeplacer++;
		}
		//Trouver o�� se situe le point apr��s lequel nous voulons mettre le point trouv�� au-dessus dans la liste des tourn��es
		int findApresDplacer = 0;
		int indexApresDeplacer = 0;
		int posCheminApresDeplacer = 0;
		for(Tournee t : listeTournees) {
			if(findApresDplacer == 0) {
				posCheminApresDeplacer = 0;
				ArrayList<Chemin> tempChemin = t.getListeChemins();
				for(Chemin c : tempChemin) {
					Intersection depart = c.getIntersectionDepart();
					Intersection dest = c.getIntersectionDest();
					if(depart.equals(idApresDeplacer)) {
						findApresDplacer = 1;
						break;
					}
					if(dest.equals(idApresDeplacer)) {
						findApresDplacer = 1;
						break;
					}
					posCheminApresDeplacer++;
				}
			}else {
				break;
			}
			indexApresDeplacer++;
		}
		if(indexADeplacer == indexApresDeplacer) {
			System.out.println("Deplacement irrealisable");
			Exception e = new Exception();
			throw e;
		}else {
		//Effectuer le d��placement
		if(findADplacer == 1 && findApresDplacer == 1) {
			//Supprimer le point de livraison �� d��placer 
			boolean supprimerTourneeADeplacer = false;
			indexADeplacer--;
			int posOneEnleve = posCheminADeplacer;
			int posTwoEnleve = posCheminADeplacer + 1;
			Intersection newDepart = listeTournees.get(indexADeplacer).getListeChemins().get(posOneEnleve).getIntersectionDepart();
			Intersection newDest = listeTournees.get(indexADeplacer).getListeChemins().get(posTwoEnleve).getIntersectionDest();
			ArrayList<Intersection> internewDepartNewDest = AEtoile.getInstance().algoAEtoile(newDepart, newDest, Controleur.getInstance().getMonPlan());
			ArrayList<Troncon> tronNewDestNewDest = AEtoile.getInstance().traductionTrajet(internewDepartNewDest, Controleur.getInstance().getMonPlan());
			Chemin newDepartNewDest = new Chemin(internewDepartNewDest,tronNewDestNewDest);
			int newDuree = (int)(newDepartNewDest.getCout()/15000 * 60 * 60);
			newDepartNewDest.setDuree(newDuree);
			if(newDepart instanceof Entrepot && newDest instanceof Entrepot) {
				System.out.println("Empty");
				supprimerTourneeADeplacer = true;
				listeTournees.remove(indexADeplacer);
				tourneeSupprimerIndex = indexADeplacer;
			}else {
				listeTournees.get(indexADeplacer).getListeChemins().remove(posTwoEnleve);
				listeTournees.get(indexADeplacer).getListeChemins().remove(posOneEnleve);
				listeTournees.get(indexADeplacer).getListeChemins().add(posCheminADeplacer, newDepartNewDest);
				tourneeSupprimerIndex = indexADeplacer;
			}
			
			//Ajouter le point de livraison �� d��placer dans la nouvelle tourn��e
			indexApresDeplacer--;
			posCheminApresDeplacer++;
			Intersection depart = listeTournees.get(indexApresDeplacer).getListeChemins().get(posCheminApresDeplacer).getIntersectionDepart();
			Intersection oldDest = listeTournees.get(indexApresDeplacer).getListeChemins().get(posCheminApresDeplacer).getIntersectionDest();
			Intersection nouveauDest = Controleur.getInstance().getMaDemande().getLivraisons().get(idADeplacer);
			ArrayList<Intersection> interdepartNouveauDest = AEtoile.getInstance().algoAEtoile(depart, nouveauDest, Controleur.getInstance().getMonPlan());
			ArrayList<Intersection> inetrnouveauDestOldDest = AEtoile.getInstance().algoAEtoile(nouveauDest, oldDest, Controleur.getInstance().getMonPlan());
			ArrayList<Troncon> tronDepartNouveauDest = AEtoile.getInstance().traductionTrajet(interdepartNouveauDest, Controleur.getInstance().getMonPlan());
			ArrayList<Troncon> tronNouveauDestOldDest = AEtoile.getInstance().traductionTrajet(inetrnouveauDestOldDest, Controleur.getInstance().getMonPlan());
			Chemin departNouveauDest = new Chemin(interdepartNouveauDest,tronDepartNouveauDest);
			Chemin nouveauDestOldDest = new Chemin(inetrnouveauDestOldDest,tronNouveauDestOldDest);
			int nouveauDestDuree = (int)(departNouveauDest.getCout()/15000 * 60 * 60);
			int oldDestDuree = (int)(nouveauDestOldDest.getCout()/15000 * 60 * 60);
			departNouveauDest.setDuree(nouveauDestDuree);
			nouveauDestOldDest.setDuree(oldDestDuree);
			listeTournees.get(indexApresDeplacer).getListeChemins().remove(posCheminApresDeplacer);
			listeTournees.get(indexApresDeplacer).getListeChemins().add(posCheminApresDeplacer, departNouveauDest);
			listeTournees.get(indexApresDeplacer).getListeChemins().add(posCheminApresDeplacer+1, nouveauDestOldDest);
			tourneeAjouterIndex = indexApresDeplacer;
			
			if(supprimerTourneeADeplacer) {
				setChanged();
				notifyObservers("DeplacementSupprimerTournee");
			}else {
				setChanged();
				notifyObservers("DeplacementSansSupprimerTournee");
			}
		}else {
			System.out.println("Point Livraison Introuvable");
			Exception e = new Exception();
			throw e;
		}
		}
	}
}

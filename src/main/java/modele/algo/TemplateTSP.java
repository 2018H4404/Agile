package modele.algo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import modele.metier.Intersection;

/**
 * La classe de la template du TSP.
 * 
 * @author H4404
 * @version 1.0
 * @since 1.0
 */

public abstract class TemplateTSP implements TSP {

	private Integer[] meilleureSolution;
	private int coutMeilleureSolution = 0;
	private Boolean tempsLimiteAtteint;

	public Boolean getTempsLimiteAtteint() {
		return tempsLimiteAtteint;
	}

	/**
	 * Methode qui calcule une repartition des points de livraisons sur les
	 * livreurs.
	 * 
	 * @param nbLivreur         : nombre de livreur.
	 * @param nbPointLivraisons : nombre de points de livraisons total.
	 * @return le clustering des points de livraison par livreur.
	 */
	public int[] clusteringNbPointLivraisonParLivreurNaive(int nbLivreur, int nbPointLivraisons) {
		int[] resultat = new int[nbLivreur];
		int nbDeBase = nbPointLivraisons / nbLivreur;

		if (nbPointLivraisons % nbDeBase == 0 && nbPointLivraisons / nbDeBase == nbLivreur && nbDeBase != 1) {
			for (int i = 0; i < nbLivreur; i++) {
				resultat[i] = nbDeBase;
			}
		} else {
			int nbBasePlusOne = nbDeBase + 1;
			int nbPartieMaxBasePlusOne = nbPointLivraisons / nbBasePlusOne;
			int nbActuelBasePlusOne = nbPartieMaxBasePlusOne;
			int somme = nbActuelBasePlusOne * (nbBasePlusOne) + (nbLivreur - nbActuelBasePlusOne) * nbDeBase;
			while (somme != nbPointLivraisons) {
				nbActuelBasePlusOne--;
				somme = nbActuelBasePlusOne * nbBasePlusOne + (nbLivreur - nbActuelBasePlusOne) * nbDeBase;
			}
			for (int i = 0; i < nbActuelBasePlusOne; i++) {
				resultat[i] = nbBasePlusOne;
			}
			for (int i = nbActuelBasePlusOne; i < nbLivreur; i++) {
				resultat[i] = nbDeBase;
			}
		}

		return resultat;
	}

	/**
	 * Methode qui calcule un clustering des points de livraisons
	 * 
	 * @param nbSommets    : nombre de sommets totals
	 * @param nbParLivreur : liste des nombres de points de livraison par livreur
	 * @param cout         : le cout entre differents points de livraisons
	 * @retour liste des groupes des points de livraison
	 */
	public ArrayList<int[]> clusteringPointLivraisonNaive(int nbSommets, int[][] cout, int[] nbParLivreur) {
		ArrayList<int[]> retour = new ArrayList<int[]>();
		ArrayList<Integer> lesIntersections = new ArrayList<Integer>();
		for (int i = 1; i < nbSommets; i++) {
			lesIntersections.add(i);
		}
		for (int i = 0; i < nbParLivreur.length; i++) {
			int nbTotal = nbParLivreur[i];
			int[] tempGroupe = new int[nbTotal];
			Integer interCourant = lesIntersections.get(0);
			lesIntersections.remove(interCourant);
			 PriorityQueue<Paire> queue = new PriorityQueue<Paire>(1,  
		                new Comparator<Paire>() {  
		                  public int compare(Paire p1, Paire p2) {  
		                    if(p1.getCout() < p2.getCout()) {  
		                    	return -1;
		                    }
		                    if (p1.getCout() > p2.getCout()) { return 1; }
		                    return 0;
		                  }  
		                });  
			int length = lesIntersections.size();
			for(int j = 0; j < length; j++) {
				Paire temp = new Paire(cout[interCourant][lesIntersections.get(j)],lesIntersections.get(j));
				queue.add(temp);
			}
			tempGroupe[0] = interCourant;
			for(int j = 1; j < nbTotal; j++) {
				Paire paireCourant = queue.poll();
				tempGroupe[j] = paireCourant.getNumeroLivraison();
				lesIntersections.remove((Integer)paireCourant.getNumeroLivraison());
			}
			retour.add(tempGroupe);
		}
		return retour;
	}

	public void chercheSolution(int tpsLimite, int nbSommets, int[][] cout, int[] duree, int nbLivreur) {
		tempsLimiteAtteint = false;
		coutMeilleureSolution = Integer.MAX_VALUE;
		meilleureSolution = new Integer[nbSommets + nbLivreur - 1];
		ArrayList<Integer> nonVus = new ArrayList<Integer>();
		for (int i = 1; i < nbSommets; i++)
			nonVus.add(i);
		ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
		vus.add(0); // le premier sommet visite est 0
		int[] nbPointLivraisonParLivreur = clusteringNbPointLivraisonParLivreurNaive(nbLivreur, nbSommets - 1);
		branchAndBound(0, nonVus, vus, 0, cout, duree, System.currentTimeMillis(), tpsLimite,
				nbPointLivraisonParLivreur, 0, nbLivreur - 1, 0);
	}

	public Integer getMeilleureSolution(int i) {
		if ((meilleureSolution == null) || (i < 0) || (i >= meilleureSolution.length))
			return null;
		return meilleureSolution[i];
	}

	public int getCoutMeilleureSolution() {
		return coutMeilleureSolution;
	}

	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP.
	 * 
	 * @param                            sommetCourant.
	 * @param nonVus                     : tableau des sommets restant a visiter.
	 * @param cout                       : cout[i][j] = duree pour aller de i a j.
	 * @param duree                      : duree[i] = duree pour visiter le sommet
	 *                                   i.
	 * @param nbTourneeAvantDest         : nombre de tournees qui doivent etre
	 *                                   faites avant la derniere tournee.
	 * @param tourneeFaite               : nombre de tournees deja parcouru.
	 * @param nbPointLivraisonParLivreur :tableau de nombre de point de livraison
	 *                                   par livreur.
	 * @oaram compteurNbLivraisonsActuels : nombre de livraison deja faite par le
	 *        livreur actuel.
	 * @return une borne inferieure du cout des permutations commencant par
	 *         sommetCourant. contenant chaque sommet de nonVus exactement une fois
	 *         et terminant par le sommet 0.
	 */
	protected abstract int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int[][] cout, int[] duree,
			int nbTourneeAvantDest, int tourneeFaite, int[] nbPointLivraisonParLivreur,
			int compteurNbLivraisonsActuels);

	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * 
	 * @param sommetCrt
	 * @param nonVus    : tableau des sommets restant a visiter
	 * @param cout      : cout[i][j] = duree pour aller de i a j, avec 0 <= i <
	 *                  nbSommets et 0 <= j < nbSommets
	 * @param duree     : duree[i] = duree pour visiter le sommet i, avec 0 <= i <
	 *                  nbSommets
	 * @return un iterateur permettant d'iterer sur tous les sommets de nonVus
	 */
	protected abstract Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int[][] cout,
			int[] duree);

	/**
	 * Methode definissant le patron (template) d'une resolution par separation et
	 * evaluation (branch and bound) du TSP
	 * 
	 * @param sommetCrt le dernier sommet visite
	 * @param nonVus    la liste des sommets qui n'ont pas encore ete visites
	 * @param vus       la liste des sommets visites (y compris sommetCrt)
	 * @param coutVus   la somme des couts des arcs du chemin passant par tous les
	 *                  sommets de vus + la somme des duree des sommets de vus
	 * @param cout      : cout[i][j] = duree pour aller de i a j, avec 0 <= i <
	 *                  nbSommets et 0 <= j < nbSommets
	 * @param duree     : duree[i] = duree pour visiter le sommet i, avec 0 <= i <
	 *                  nbSommets
	 * @param tpsDebut  : moment ou la resolution a commence
	 * @param tpsLimite : limite de temps pour la resolution
	 */
	void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, int coutVus, int[][] cout,
			int[] duree, long tpsDebut, int tpsLimite, int[] nbPointLivraisonParLivreur,
			int compteurNbLivraisonsActuels, int nbTourneeAvantDest, int tourneeFaite) {
		if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
			tempsLimiteAtteint = true;
			return;
		}
		if (nonVus.size() == 0) { // tous les sommets ont ete visites
			coutVus += cout[sommetCrt][0];
			if (coutVus < coutMeilleureSolution) { // on a trouve une solution meilleure que meilleureSolution
				vus.toArray(meilleureSolution);
				coutMeilleureSolution = coutVus;
			}
		} else if (coutVus + bound(sommetCrt, nonVus, cout, duree, nbTourneeAvantDest, tourneeFaite,
				nbPointLivraisonParLivreur, compteurNbLivraisonsActuels) < coutMeilleureSolution) {
			if (tourneeFaite < nbTourneeAvantDest) {
				if (compteurNbLivraisonsActuels < nbPointLivraisonParLivreur[tourneeFaite]) {
					Iterator<Integer> it = iterator(sommetCrt, nonVus, cout, duree);
					while (it.hasNext()) {
						Integer prochainSommet = it.next();
						vus.add(prochainSommet);
						nonVus.remove(prochainSommet);
						branchAndBound(prochainSommet, nonVus, vus,
								coutVus + cout[sommetCrt][prochainSommet] + duree[prochainSommet], cout, duree,
								tpsDebut, tpsLimite, nbPointLivraisonParLivreur, compteurNbLivraisonsActuels + 1,
								nbTourneeAvantDest, tourneeFaite);
						vus.remove(prochainSommet);
						nonVus.add(prochainSommet);
					}
				} else {
					Integer entrepot = 0;
					vus.add(entrepot);
					branchAndBound(0, nonVus, vus, coutVus + cout[sommetCrt][0] + duree[0], cout, duree, tpsDebut,
							tpsLimite, nbPointLivraisonParLivreur, 0, nbTourneeAvantDest, tourneeFaite + 1);
					vus.remove(vus.size() - 1);
				}
			} else {
				Iterator<Integer> it = iterator(sommetCrt, nonVus, cout, duree);
				while (it.hasNext()) {
					Integer prochainSommet = it.next();
					vus.add(prochainSommet);
					nonVus.remove(prochainSommet);
					branchAndBound(prochainSommet, nonVus, vus,
							coutVus + cout[sommetCrt][prochainSommet] + duree[prochainSommet], cout, duree, tpsDebut,
							tpsLimite, nbPointLivraisonParLivreur, compteurNbLivraisonsActuels + 1, nbTourneeAvantDest,
							tourneeFaite);
					vus.remove(prochainSommet);
					nonVus.add(prochainSommet);
				}
			}
		}
	}
}
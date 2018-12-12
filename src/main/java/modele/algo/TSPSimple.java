package modele.algo;

import java.util.ArrayList;
import java.util.Iterator;

/** 
 * La classe du simple TSP.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class TSPSimple extends TemplateTSP {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int[][] cout, int[] duree) {
		return new IteratorSeq(nonVus, sommetCrt);
	}
	
	/**
	 * Methode pour calculer une heuristique afin de faire des coupes sur les branches.
	 * @param sommetCourant : sommet actuel que nous sommes en train de explorer.
	 * @param nonVus : la liste des intersections pas encore exploitees.
	 * @param duree : la liste des durees des points de livraison
	 * @param nbTourneeAvantDest : le nombre de tournee qui doit etre fait avant atteindre la destination(Entrepot).
	 * @param tourneeFaite : le nombre de tournee deja fait
	 * @param nbPointLivraisonParLivreur : le tableau qui contient le nombre des points de livraison pour chaque livreur.
	 * @param compteurNbLivraisonsActuels : le nombre des points de livraison deja livres dans la tournee actuelle.
	 */
	@Override
	protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int[][] cout, int[] duree, int nbTourneeAvantDest, int tourneeFaite, int[] nbPointLivraisonParLivreur, int compteurNbLivraisonsActuels) {
		int min = Integer.MAX_VALUE;
		int nbNonVu = nonVus.size();
		int minVersDest = Integer.MAX_VALUE;
		for(Integer nonVu : nonVus) {
			if(cout[nonVu][0] < minVersDest) {
				minVersDest = cout[nonVu][0];
			}
		}
		for(Integer nonVu : nonVus) {
			if(cout[sommetCourant][nonVu] < min) {
				min = cout[sommetCourant][nonVu];
			}
		}
		for(int i = 0; i < nbNonVu; i++) {
			for(int j = 0; j < nbNonVu; j++) {
				if(i != j) {
					if(cout[nonVus.get(i)][nonVus.get(j)] < min) {
						min = cout[nonVus.get(i)][nonVus.get(j)];
					}
				}
			}
		}
		if(tourneeFaite < nbTourneeAvantDest) {
			if(compteurNbLivraisonsActuels == nbPointLivraisonParLivreur[tourneeFaite]) {
				if(cout[sommetCourant][0] < min) {
					min = cout[sommetCourant][0];
				}
			}
			if(minVersDest < min) {
				min = minVersDest;
			}
		}
		min = min * (nbNonVu + nbTourneeAvantDest - tourneeFaite) ;
		for(Integer nonVu : nonVus) {
			min += duree[nonVu];
		}
		min += duree[0] * (nbTourneeAvantDest - tourneeFaite);
		min += minVersDest;
		return min;
	}

}


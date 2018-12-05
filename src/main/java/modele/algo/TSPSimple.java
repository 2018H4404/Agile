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


package modele.metier;

import java.util.ArrayList;

/** 
 * La classe de la tournee.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class Tournee {
	
	
	
	private ArrayList<Chemin> listeChemins;
	
	/**
	 * Constructeur parametre de la Tournee en utilisant une liste des chemins.
	 * @param uneListeChemins : une liste de chemins d'une tournee.
	 */
	public Tournee(ArrayList<Chemin> uneListeChemins) {
		this.listeChemins = uneListeChemins;
	}
	
	/**
	 * Constructeur parametre de la Tournee en utilisant une tournee existant.
	 * @param t : une tournee existant.
	 */
	public Tournee(Tournee t) {
		listeChemins = t.listeChemins;
	}

	/**
	 * Methode pour obtenir les chemins de cette tournee.
	 * @return une liste des chemins.
	 */
	public ArrayList<Chemin> getListeChemins() {
		return listeChemins;
	}

	
}

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
	 * Constructeur parametre de la tournee.
	 * @param uneListeChemins la liste de chemins de la tournee.
	 */
	public Tournee(ArrayList<Chemin> uneListeChemins) {
		this.listeChemins = uneListeChemins;
	}
	
	public Tournee(Tournee t) {
		listeChemins = t.listeChemins;
	}

	public ArrayList<Chemin> getListeChemins() {
		return listeChemins;
	}

	public void setListeChemins(ArrayList<Chemin> listeChemins) {
		this.listeChemins = listeChemins;
	}	
	
}

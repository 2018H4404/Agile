package modele.metier;

import java.util.ArrayList;

/** 
 * La classe de la tournée.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class Tournee {
	
	private ArrayList<Chemin> listeChemins;
	
	/**
	 * Constructeur paramétré de la tournée.
	 * @param uneListeChemins la liste de chemins de la tournée.
	 */
	public Tournee(ArrayList<Chemin> uneListeChemins) {
		this.listeChemins = uneListeChemins;
	}

	public ArrayList<Chemin> getListeChemins() {
		return listeChemins;
	}

	public void setListeChemins(ArrayList<Chemin> listeChemins) {
		this.listeChemins = listeChemins;
	}	
	
}

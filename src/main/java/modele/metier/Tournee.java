package modele.metier;

import java.util.ArrayList;


public class Tournee {
	
	private ArrayList<Chemin> listeChemins;
	
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

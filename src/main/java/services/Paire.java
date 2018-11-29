package services;

import modele.metier.Intersection;

/** 
 * La classe de la paire .
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class Paire {
	
	public Intersection monInter;
	public double valeurF;
	
	/**
	 * Constructeur paramétré de la paire.
	 * @param unInter l'intersection de la paire.
	 * @param uneValeur la valeur de l'intersection de la paire.
	 */
	public Paire(Intersection unInter, double uneValeur) {
		this.monInter = unInter;
		this.valeurF = uneValeur;
	}
	
	/*
	public long monInter;
	public double valeurF;
	
	public Paire(long unInter, double uneValeur) {
		this.monInter = unInter;
		this.valeurF = uneValeur;
	}*/
	
}

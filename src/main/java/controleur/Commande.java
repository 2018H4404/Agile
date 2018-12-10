package controleur;

/**
 * La classe commande.
 * @author H4404
 */
public interface Commande {

	/**
	 * Réaliser la commande.
	 * @param controleur le controleur.
	 */
	 void doCmd(Controleur controleur);
	
	 /**
	  * Annuler la commande.
	  * @param controleur le controleur.
	  */
	 void undoCmd(Controleur controleur);
}
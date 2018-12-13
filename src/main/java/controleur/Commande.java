package controleur;

/**
 * L'interface commande.
 * @author H4404
 */
public interface Commande {

  /**
	 * Realiser la commande.
	 */
	 void doCmd();
	
	 /**
	  * Annuler la commande.
	  */
	 void undoCmd();
  
}

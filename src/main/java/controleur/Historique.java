package controleur;
import java.util.LinkedList;

/**
 * La classe de l'historique.
 * @author H4404
 */
public class Historique {
	protected LinkedList <Commande> listeDeCommande;
	protected int indice;
	
	public Historique() {
		listeDeCommande = new LinkedList();
		indice = -1;	
	}	
}

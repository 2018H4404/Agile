package controleur;
import java.util.LinkedList;


public class Historique {
	protected LinkedList <Commande> listeDeCommande;
	protected int indice;
	
	public Historique() {
		listeDeCommande = new LinkedList();
		indice = -1;
		
	}
	

	
}

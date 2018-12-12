package controleur;
import java.util.LinkedList;

/**
 * La classe de l'historique du commande.
 * @author H4404
 */
public class Historique {
	protected LinkedList <Commande> listeDeCommande;
	protected int indice;
	
	public Historique() {
		listeDeCommande = new LinkedList();
		indice = -1;
		
	}
	
	/**
	 * reset le historique
	 */
	public void clear(){
		listeDeCommande.clear();
		indice = -1;
	}
	
	
	public int getIndice(){
		return indice;
	}
	
	/**
	 * ajoute la commande dans l'historique
	 * @param cmd  commande a ajouter
	 */
	public void ajouteCmd(Commande cmd) {
		int i = indice +1;
		if(i<listeDeCommande.size()) {
			listeDeCommande.remove(i);
		}
		indice++;
		listeDeCommande.add(cmd);
		System.out.println("cmd list length:"+ listeDeCommande.size());
	}
	
	/**
	 * Methode pour annuelr le commande courant
	 */
	public void undo() {
		if(indice>=0) {
			Commande cmd = listeDeCommande.get(indice);
			indice--;
			cmd.undoCmd();
		}
	}
	/**
	 * Methode pour effctuer le commande courant
	 */
	public void redo() {
		if(indice<listeDeCommande.size()-1) {
			indice++;
			Commande cmd = listeDeCommande.get(indice);
			cmd.doCmd();
		}
	}
	
	/**
	 * Methode pour reinitialiser l'historique
	 */
	public void reset() {
		this.listeDeCommande = new LinkedList();
		this.indice = -1;
	}
	
	/**
	 * Methode pour retourner le length de l'historique
	 * @return
	 */
	public int getLength() {
		return listeDeCommande.size();
	}
	

}

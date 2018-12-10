package controleur;
import java.util.LinkedList;


public class Historique {
	protected LinkedList <Commande> listeDeCommande;
	protected int indice;
	
	public Historique() {
		listeDeCommande = new LinkedList();
		indice = -1;
		
	}
	
	public void ajouteCmd(Commande cmd) {
		int i = indice +1;
		if(i<listeDeCommande.size()) {
			listeDeCommande.remove(i);
		}
		indice++;
		listeDeCommande.add(cmd);
		System.out.println("cmd list length:"+ listeDeCommande.size());
//		cmd.doCmd();
	}
	
	public void undo() {
		if(indice>=0) {
			Commande cmd = listeDeCommande.get(indice);
			indice--;
			cmd.undoCmd();
		}
	}
	
	public void redo() {
		if(indice<listeDeCommande.size()-1) {
			indice++;
			Commande cmd = listeDeCommande.get(indice);
			cmd.doCmd();
		}
	}
	
	public void reset() {
		
	}
	
}

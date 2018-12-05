package controleur;

public interface Commande {

	 void doCmd(Controleur controleur);
	
	 void undoCmd(Controleur controleur);
}

package controleur;

import modele.metier.DemandeLivraison;
import modele.metier.Tournee;

public class CommandeAjouterLivraison implements Commande{
	private Tournee tournee;
	private Tournee preTournee;
	private DemandeLivraison livraison;
	private boolean succes,dejaAjout;
	
	
	public CommandeAjouterLivraison(Tournee tournee) {
		this.tournee = tournee;
		preTournee = tournee;
		dejaAjout = false;
	}

	@Override
	public void doCmd() {
		if(!dejaAjout) {
			try {
				Controleur.getInstance().getMonManager().ajouterPointLivraison(Controleur.getInstance().getIdAjoutDepart(),Controleur.getInstance().getIdAjoutNouvellePoint(),Controleur.getInstance().getAjoutDuree());
				Controleur.getInstance().getGraph().getLivraisonGroup().getChildren().add(Controleur.getInstance().getVueSelectionne());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void undoCmd() {
			try {
				Controleur.getInstance().getMonManager().supprimerPointLivraison(Controleur.getInstance().getIdAjoutNouvellePoint());
				Controleur.getInstance().getGraph().getLivraisonGroup().getChildren().remove(Controleur.getInstance().getVueSelectionne());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}

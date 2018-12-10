package controleur;

import javafx.scene.paint.Color;
import modele.metier.Tournee;

public class CommandeSupprimeLivraison implements Commande {
	
	private long supprimeId;
	
	public CommandeSupprimeLivraison(long id) {
		supprimeId = id;
	}

	@Override
	public void doCmd() {
		try {
			Controleur.getInstance().getMonManager().supprimerPointLivraison(Controleur.getInstance().getIdAjoutNouvellePoint());
			Controleur.getInstance().getGraph().getLivraisonGroup().getChildren().remove(Controleur.getInstance().getVueSelectionne());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void undoCmd() {
		try {
			Controleur.getInstance().getMonManager().ajouterPointLivraison(Controleur.getInstance().getIdAjoutDepart(),Controleur.getInstance().getIdAjoutNouvellePoint(),Controleur.getInstance().getAjoutDuree());
			Controleur.getInstance().getVueSelectionne().setRadius(5);
			Controleur.getInstance().getVueSelectionne().setFill(Color.BLUE);
			Controleur.getInstance().getGraph().getLivraisonGroup().getChildren().add(Controleur.getInstance().getVueSelectionne());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

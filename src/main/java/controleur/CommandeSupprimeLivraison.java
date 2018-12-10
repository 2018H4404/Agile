package controleur;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import modele.metier.PointLivraison;
import modele.metier.Tournee;
import vue.element.PointLivraisonVue;

public class CommandeSupprimeLivraison implements Commande {
	private long idCommandePointLivraisonPrece;
	private long idCommandePointLivraison;
	private PointLivraisonVue vue;
	private int dureeCommandePointLivraison;
	
	public CommandeSupprimeLivraison(long idCommandePointLivraisonPrece,long idCommandePointLivraison,PointLivraisonVue vue,int dureeCommandePointLivraison) {
		this.idCommandePointLivraisonPrece = idCommandePointLivraisonPrece;
		this.idCommandePointLivraison = idCommandePointLivraison;
		this.vue = vue;
		this.dureeCommandePointLivraison = dureeCommandePointLivraison;
	}

	@Override
	public void doCmd() {
		try {
			Controleur.getInstance().getMonManager().supprimerPointLivraison(idCommandePointLivraison);
			Controleur.getInstance().getGraph().getLivraisonGroup().getChildren().remove(vue);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void undoCmd() {
		try {
			vue.setRadius(5);
			vue.setFill(Color.BLUE);
			Controleur.getInstance().getMonManager().ajouterPointLivraison(idCommandePointLivraisonPrece,idCommandePointLivraison,dureeCommandePointLivraison);
			Controleur.getInstance().getGraph().getLivraisonGroup().getChildren().add(vue);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

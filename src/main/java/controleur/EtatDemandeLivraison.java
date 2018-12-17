package controleur;

import javafx.scene.Node;
import vue.element.IntersectionNormalVue;

/**
 * La classe de l'etat de demande de livraison.
 * 
 * @author H4404
 */
public class EtatDemandeLivraison extends EtatDefaut {

	/**
	 * Methode pour lancer la calcul de tournees selon le nombre de livreur et le mode passe.
	 * @see Etat
	 */
	@Override
	public void CalculerLesTournees(int nbLivreur, int mode) throws Exception {

		Controleur.getInstance().getMonManager().calculerLesTourneesSelonMode(Controleur.getInstance().getMaDemande(),
				Controleur.getInstance().getMonPlan(), nbLivreur, mode);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
	}

	/**
	 * Methode pour obtenir le nombre de livreur maximum.
	 * @see Etat
	 */
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}

	/**
	 * Methode pour ajouter le listener qui permet a l'utilisateur d'ajouter des points de livraison avant le calcul des tournees.
	 * @see Etat
	 */
	public void ajouterListenerOnClick() {
		for (Node vue : Controleur.getInstance().getGraph().getNoeudGroup().getChildren()) {
			if (vue instanceof IntersectionNormalVue) {

				IntersectionNormalVue temp = (IntersectionNormalVue) vue;
				temp.ajouterListenerOnClick();

			}
		}
	}

}

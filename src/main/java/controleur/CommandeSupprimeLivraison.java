package controleur;

import modele.metier.Intersection;
import modele.metier.PointLivraison;


/**
 * la classe de Commande pour supprimer une livraison
 * @author H4404
 *
 */
public class CommandeSupprimeLivraison implements Commande {
	private PointLivraison livraisonSupprime;
	private Intersection prePoint;
	private boolean supprime;

	public CommandeSupprimeLivraison(PointLivraison livraisonSupprime, Intersection prePoint) {
		this.livraisonSupprime = livraisonSupprime;
		this.prePoint = prePoint;
		supprime = false;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	/**
	 * Methode pour faire une commande
	 */
	@Override
	public void doCmd() {
		try {
			Controleur.getInstance().getMonManager().supprimerPointLivraisonMetier(livraisonSupprime.getId());
			Controleur.getInstance().getMonManager().notifyVue();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Methode pour annuler une commande
	 */
	@Override
	public void undoCmd() {
		try {
			if(supprime == true) {
				Controleur.getInstance().getMonManager().creerTourneeJusteUnLivraison(livraisonSupprime, prePoint);
			}else {
				Controleur.getInstance().getMonManager().ajouterPointLivraisonMetier(prePoint.getId(), livraisonSupprime.getId(), livraisonSupprime.getDuree());
			}
			Controleur.getInstance().getMonManager().notifyVue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

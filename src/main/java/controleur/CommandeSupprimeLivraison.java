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
	
	/**
	 * Constructeur d'une commande qui supprime un point de livraison
	 * @param livraisonSupprime : le point de livraison a supprimer
	 * @param prePoint : le point de livraison qui est avant le point de livraison a supprimer dans la tournee
	 */
	public CommandeSupprimeLivraison(PointLivraison livraisonSupprime, Intersection prePoint) {
		this.livraisonSupprime = livraisonSupprime;
		this.prePoint = prePoint;
		supprime = false;
	}
	
	//Setter
	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	/**
	 * Methode pour faire cette commande qui supprime un point de livraison
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
	 * Methode pour annuler cette commande qui supprime un point de livraison
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

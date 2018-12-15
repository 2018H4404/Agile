package controleur;


/**
 * la classe de commande pour ajouter une livraison
 * @author H4404
 *
 */
public class CommandeAjouterLivraison implements Commande {
	private long prePoint;
	private long nouvellePoint;
	private int duree;

	public CommandeAjouterLivraison(long prePoint, long nouvellePoint, int duree) {
		this.prePoint = prePoint;
		this.nouvellePoint = nouvellePoint;
		this.duree = duree;
	}

	/**
	 * Methode pour effecuter une commande
	 */
	@Override
	public void doCmd() {
		try {
			Controleur.getInstance().getMonManager().ajouterPointLivraisonMetier(prePoint, nouvellePoint, duree);
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
			Controleur.getInstance().getMonManager().supprimerPointLivraisonMetier(nouvellePoint);
			Controleur.getInstance().getMonManager().notifyVue();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

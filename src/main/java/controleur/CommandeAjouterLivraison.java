package controleur;


/**
 * la classe de commande pour ajouter un point de livraison
 * @author H4404
 *
 */
public class CommandeAjouterLivraison implements Commande {
	private long prePoint;
	private long nouvellePoint;
	private int duree;
	
	/**
	 * Contstructeur d'une commande qui ajoute un point de livraison
	 * @param prePoint : le point de livraison qui est avant le point de livraison a ajouter
	 * @param nouvellePoint : le nouveau point de livraison a ajouter
	 * @param duree : la duree de ce point de livraison a ajouter
	 */
	public CommandeAjouterLivraison(long prePoint, long nouvellePoint, int duree) {
		this.prePoint = prePoint;
		this.nouvellePoint = nouvellePoint;
		this.duree = duree;
	}

	/**
	 * Methode pour faire cette commande qui ajoute un point de livraison
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
	 * Methode pour annuler cette commande qui ajoute un point de livraison
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

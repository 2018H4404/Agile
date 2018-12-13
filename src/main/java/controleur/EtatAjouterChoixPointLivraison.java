package controleur;

/**
 * La classe de l'etat ajouter le choix de livraison
 * @author H4404
 */
public class EtatAjouterChoixPointLivraison extends EtatDefaut{
	/**
	 * Methode pour passer la prochain etat  EtatAjouterChoixNouvellePointLivraison
	 */
	@Override
	public void choixNouveauPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatAjouterChoixNouvellePointLivraison());
	}
	
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

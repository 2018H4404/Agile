package controleur;

/**
 * La classe de l'etat ajouter le choix de livraison
 * @author H4404
 */
public class EtatAjouterChoixPointLivraison extends EtatDefaut{
	/**
	 * Methode pour passer a l'etat suivant pour choisir le point de livraison a ajouter.
	 * @see Etat
	 */
	@Override
	public void choixNouveauPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatAjouterChoixNouvellePointLivraison());
	}
	
	/**
	 * Methode pour obtenir le nombre de livreur maximum.
	 * @see Etat
	 */
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

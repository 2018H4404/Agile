package controleur;


/**
 * la classe de l'etat pour supprimer le point de livraison choisi
 * @author H4404
 *
 */
public class EtatSupprimerChoixPointLivraison extends EtatDefaut{
	/**
	 * Methode pour supprimer un point de livraison selon l'id passe.
	 * @see Etat.
	 */
	@Override
	public void effectuerSupprimerPointLivraison(long id) throws Exception {
		boolean supprime = Controleur.getInstance().getMonManager().supprimerPointLivraison(id);
		Controleur.getInstance().getTempSupprimer().setSupprime(supprime);
		Controleur.getInstance().getHistorique().ajouteCmd(Controleur.getInstance().getTempSupprimer());
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
	}
	
	/**
	 * Methode pour obtenir le nombre de livreur maximum.
	 * @see Etat.
	 */
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

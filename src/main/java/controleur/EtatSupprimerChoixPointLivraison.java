package controleur;


/**
 * la classe de l'etat pour supprimer le point de livraison choisi
 * @author H4404
 *
 */
public class EtatSupprimerChoixPointLivraison extends EtatDefaut{
	/**
	 * Methode pour passer a l'etat suivant en suipprimant le pouint livraison choisi
	 */
	@Override
	public void effectuerSupprimerPointLivraison(long id) throws Exception {
		boolean supprime = Controleur.getInstance().getMonManager().supprimerPointLivraison(id);
		Controleur.getInstance().getTempSupprimer().setSupprime(supprime);
		Controleur.getInstance().getHistorique().ajouteCmd(Controleur.getInstance().getTempSupprimer());
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
	}
	
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

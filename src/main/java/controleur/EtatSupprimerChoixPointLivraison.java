package controleur;

public class EtatSupprimerChoixPointLivraison extends EtatDefaut{
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

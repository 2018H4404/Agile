package controleur;

public class EtatSupprimerChoixPointLivraison extends EtatDefaut{
	@Override
	public void effectuerSupprimerPointLivraison(long id) throws Exception {
		Controleur.getInstance().getMonManager().supprimerPointLivraison(id);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
		/*CommandeSupprimeLivraison cmd = new CommandeSupprimeLivraison(Controleur.getInstance().getMonManager().getDernierTournee());
		Controleur.getInstance().getHistorique().ajouteCmd(cmd);*/
	}
	
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

package controleur;

public class EtatSupprimerChoixPointLivraison extends EtatDefaut{
	@Override
	public void effectuerSupprimerPointLivraison(long id) throws Exception {
		Controleur.getInstance().getMonManager().supprimerPointLivraison(id);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
		CommandeSupprimeLivraison cmd = new CommandeSupprimeLivraison();
		Controleur.getInstance().getHistorique().ajouteCmd(cmd);
	}
	
	@Override
	public int getNbLivreurMaximum() throws Exception {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

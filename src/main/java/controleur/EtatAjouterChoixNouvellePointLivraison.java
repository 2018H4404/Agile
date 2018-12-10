package controleur;

public class EtatAjouterChoixNouvellePointLivraison extends EtatDefaut{
	@Override
	public void effectuerAjoutPointLivraison(long idDepart, long idNouvelle, int duree) throws Exception {
		Controleur.getInstance().getMonManager().ajouterPointLivraison(idDepart, idNouvelle, duree);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
		CommandeAjouterLivraison cmd = new CommandeAjouterLivraison(Controleur.getInstance().getMonManager().getDernierTournee());
		Controleur.getInstance().getHistorique().ajouteCmd(cmd);
	}
	
	@Override
	public int getNbLivreurMaximum() throws Exception {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

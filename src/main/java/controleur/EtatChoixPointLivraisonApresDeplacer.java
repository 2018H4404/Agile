package controleur;

public class EtatChoixPointLivraisonApresDeplacer extends EtatDefaut{
	@Override
	public void effectuerDeplacement(long idADeplacer, long idApres) throws Exception {
		Controleur.getInstance().getMonManager().deplacerPointLivraison(idADeplacer, idApres);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
	}
	
	@Override
	public int getNbLivreurMaximum() throws Exception {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

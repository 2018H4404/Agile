package controleur;

public class EtatAjouterChoixPointLivraison extends EtatDefaut{
	@Override
	public void choixNouvellePointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatChoixNouvellePointLivraison());
	}
	
	@Override
	public int getNbLivreurMaximum() throws Exception {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

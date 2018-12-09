package controleur;

public class EtatChoixPointLivraisonADeplacer extends EtatDefaut{
	@Override
	public void choixPointLivraisonApresDeplacer() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatChoixPointLivraisonApresDeplacer());
	}
	
	@Override
	public int getNbLivreurMaximum() throws Exception {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

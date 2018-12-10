package controleur;

/**
 * La classe de l'etat du choix du point de livraison a deplacer.
 * @author H4404
 */
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

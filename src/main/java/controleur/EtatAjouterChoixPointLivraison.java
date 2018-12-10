package controleur;

/**
 * La classe de l'etat ajouter le choix de livraison
 * @author H4404
 */
public class EtatAjouterChoixPointLivraison extends EtatDefaut{
	@Override
	public void choixNouvellePointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatAjouterChoixNouvellePointLivraison());
	}
	
	@Override
	public int getNbLivreurMaximum() throws Exception {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

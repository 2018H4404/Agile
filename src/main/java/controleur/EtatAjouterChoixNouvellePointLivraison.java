package controleur;

/**
 * La classe de l'etat de l'ajout du choix de nouvelle point de livraison.
 * 
 * @author H4404
 */
public class EtatAjouterChoixNouvellePointLivraison extends EtatDefaut {
	/**
	 * Methode de la classe Etat pour ajouter un point de livraison 
	 * et passer le controleur dans l'etat EtatPosteCalcul .
	 * @see Etat
	 */
	@Override
	public void effectuerAjoutPointLivraison(long idDepart, long idNouvelle, int duree) throws Exception {
		Controleur.getInstance().getMonManager().ajouterPointLivraison(idDepart, idNouvelle, duree);
		Controleur.getInstance().getHistorique().ajouteCmd(Controleur.getInstance().getTempAjout());
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
	}

	/**
	 * Methode pour obtenir le nombre de livreur maximum.
	 * @see Etat
	 */
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

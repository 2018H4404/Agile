package controleur;

/**
 * La classe de l'etat du choix du point de livraison a deplacer.
 * @author H4404
 */
public class EtatChoixPointLivraisonADeplacer extends EtatDefaut{
	/**
	 * Methode pour passer a l'etat suivant pour choisir le point de livraison 
	 * apres lequel nous voulons mettre le point de livraison a deplacer.
	 * @see Etat
	 */
	@Override
	public void choixPointLivraisonApresDeplacer() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatChoixPointLivraisonApresDeplacer());
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

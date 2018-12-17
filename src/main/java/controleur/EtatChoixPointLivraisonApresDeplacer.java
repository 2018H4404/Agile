package controleur;

/**
 * La classe de l'etat du choix du point de livraison a appeler apres deplacer.
 * @author H4404
 */
public class EtatChoixPointLivraisonApresDeplacer extends EtatDefaut{
	
	/**
	 * Methode pour effectuer le deplacement et revenir a l'etat EtatPosteCalcul.
	 * @see Etat
	 */
	@Override
	public void effectuerDeplacement(long idADeplacer, long idApres) throws Exception {
		Controleur.getInstance().getMonManager().deplacerPointLivraison(idADeplacer, idApres);
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

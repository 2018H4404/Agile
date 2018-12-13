package controleur;

/**
 * La classe de l'etat du choix du point de livraison a appeler apres deplacer.
 * @author H4404
 */
public class EtatChoixPointLivraisonApresDeplacer extends EtatDefaut{
	
	/**
	 * Methode pour effectuer le deplacement et passer a l'etat prochain
	 */
	@Override
	public void effectuerDeplacement(long idADeplacer, long idApres) throws Exception {
		Controleur.getInstance().getMonManager().deplacerPointLivraison(idADeplacer, idApres);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
	}
	
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

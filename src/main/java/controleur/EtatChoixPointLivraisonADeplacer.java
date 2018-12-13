package controleur;

/**
 * La classe de l'etat du choix du point de livraison a deplacer.
 * @author H4404
 */
public class EtatChoixPointLivraisonADeplacer extends EtatDefaut{
	/**
	 * Methode pour passer a la prochaine etat EtatChoixPointLivraisonApresDeplacer
	 */
	@Override
	public void choixPointLivraisonApresDeplacer() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatChoixPointLivraisonApresDeplacer());
	}
	
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

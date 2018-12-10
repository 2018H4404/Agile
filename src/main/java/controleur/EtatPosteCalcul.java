package controleur;

/**
 * La classe de l'etat poste calcul.
 * @author H4404
 */
public class EtatPosteCalcul extends EtatDefaut {
	/*public void effaceListenerOnClick() {
		for(Node vue : Controleur.getInstance().getGraph().getLivraisonGroup().getChildren()) {
			if(vue instanceof PointLivraisonVue) {
				PointLivraisonVue temp = (PointLivraisonVue) vue;
				temp.effaceListenerOnClick();
			}			
		}
	for(Node vue : Controleur.getInstance().getGraph().getNoeudGroup().getChildren()) {
		if(vue instanceof IntersectionNormalVue) {
			
			IntersectionNormalVue temp = (IntersectionNormalVue) vue;
			temp.effaceListenerOnClick();
			
		}
	}
	}*/
	
	@Override
	public void ajouterPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatAjouterChoixPointLivraison());
	}
	
	@Override
	public void deplacerPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatChoixPointLivraisonADeplacer());
	}
	
	@Override
	public void supprimerPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatSupprimerChoixPointLivraison());
	}
	
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
	
	@Override
	public void CalculerLesTournees(int nbLivreur) throws Exception{
		
		//Controleur.getInstance().getMonManager().calculerLesTournees(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur);
		Controleur.getInstance().getMonManager().calculerLesTourneesClustering(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
	}
	
	public void undo() {
		Controleur.getInstance().getHistorique().undo();
	}
	
	public void redo() {
		Controleur.getInstance().getHistorique().redo();

	}
	
	
}

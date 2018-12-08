package controleur;

import javafx.scene.Node;
import vue.element.IntersectionNormalVue;
import vue.element.PointLivraisonVue;

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
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatChoixPointLivraison());
	}
	
	@Override
	public int getNbLivreurMaximum() throws Exception {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
	
	@Override
	public void CalculerLesTournees(int nbLivreur) throws Exception{
		
		//Controleur.getInstance().getMonManager().calculerLesTournees(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur);
		Controleur.getInstance().getMonManager().calculerLesTourneesClustering(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
	}
	
	
}

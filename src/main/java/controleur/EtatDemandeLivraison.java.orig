package controleur;

import javafx.scene.Node;
import vue.element.IntersectionNormalVue;
import vue.element.PointLivraisonVue;

public class EtatDemandeLivraison extends EtatDefaut {

	@Override
	public void CalculerLesTournees(int nbLivreur) {
		
<<<<<<< HEAD
		Controleur.getInstance().getMonManager().calculerLesTournees(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
=======
		Controleur.getInstance().getMonManager().calculerLesTourneesClustering(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur);
>>>>>>> d0349b729a1e5cc51607684aa97121bf8a494224

	}
	
	@Override
	public int getNbLivreurMaximum() throws Exception {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
	
	public void ajouterListenerOnClick() {
		for(Node vue : Controleur.getInstance().getGraph().getNoeudGroup().getChildren()) {
			if(vue instanceof IntersectionNormalVue) {
				
				IntersectionNormalVue temp = (IntersectionNormalVue) vue;
				temp.ajouterListenerOnClick();
				
			}
		}
	}
	

	
	@Override
	public void modifierPointLivraison() throws Exception{
		
	}
	
}

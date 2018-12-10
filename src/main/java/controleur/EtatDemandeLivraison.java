package controleur;

import javafx.scene.Node;
import vue.element.IntersectionNormalVue;
import vue.element.PointLivraisonVue;

/**
 * La classe de l'etat de demande de livraison.
 * @author H4404
 */
public class EtatDemandeLivraison extends EtatDefaut {

	@Override
	public void CalculerLesTournees(int nbLivreur) throws Exception{
		
		//Controleur.getInstance().getMonManager().calculerLesTournees(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur);
		Controleur.getInstance().getMonManager().calculerLesTourneesClustering(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
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

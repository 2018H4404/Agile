package controleur;

import javafx.scene.Node;
import vue.element.IntersectionNormalVue;
import vue.element.PointLivraisonVue;

public class EtatPosteCalcul extends EtatDefaut {
	public void effaceListenerOnClick() {
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

	}
}

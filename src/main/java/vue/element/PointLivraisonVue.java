package vue.element;

import controleur.Controleur;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** 
 * La classe de la vue du point de livraison.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/
@SuppressWarnings("restriction")
public class PointLivraisonVue extends Circle{
	
	private long idPointLivraison;
	private boolean selectionnee;
	private boolean synchronisee;
	private boolean activeChangerCouleurSelectionne;
	private Color originalColor;
	
	
	/**
	 * Constructeur de la vue du point de livraison.
	 * @param x
	 * @param y
	 * @param radius
	 * @param unId
	 */
	public PointLivraisonVue(double x, double y, double radius, long unId) {
		super(x,y,radius);
		this.setFill(Color.BLUE);
		this.originalColor = Color.BLUE;
		this.idPointLivraison = unId;
		this.selectionnee = false;
		this.synchronisee = false;
		activeChangerCouleurSelectionne = true;
		ajouterListener();
	}
	
	public void setActiveChangerCouleurSelectionne(boolean activeChangerCouleurSelectionne) {
		this.activeChangerCouleurSelectionne = activeChangerCouleurSelectionne;
	}

	public long getIntersectionId() {
		return idPointLivraison;
	}

	/**
	 * Methode pour verifier si le PointLivraisonVue est selectionne
	 * @return
	 */
	public boolean isSelectionnee() {
		return selectionnee;
	}
	/**
	 * Methode pour verifier si le PointLivraisonVue est synchronisee
	 * @return
	 */
	public boolean isSynchronisee() {
		return synchronisee;
	}

	public void setSynchronisee(boolean bool) {
		synchronisee = bool;;
	}
	
	/**
	 * Change la valeur de radius de ce PointLivraisonVue
	 * @param radius  la nouvelle valeur du radius
	 */
	public void changeRadius(double radius) {
		this.setRadius(radius);
	}
	
	public void setSelectionnee(boolean selectionnee) {
		this.selectionnee = selectionnee;
	}
	
	public void changerFormeSynchronise() {
		synchronisee = true;
		this.setRadius(8);
		this.setFill(Color.ORANGE);
		this.originalColor = Color.ORANGE;
	}
	
	public void setOriginalColor(Color couleur) {
		this.originalColor = couleur;
	}
	
	/**
	 * Méthode pour ajouter un listner.
	 */
	public void ajouterListener() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
                changerCouleurSelectionnee();
            }
        });
		this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
            	changerCouleurNonSelectionnee();
            }
        });	
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
	        public void handle(final MouseEvent event) {
	            System.out.println(idPointLivraison);
	            try {
	            	if(Controleur.getInstance().getEtatCourant().getClass().getSimpleName().equals("EtatDemandeLivraison")) {
	            		//Controleur.getInstance().getMaDemande().supprimerPoint(idPointLivraison);
	            	}else {
	            		
	            	}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}  
		});
	}
	
	
	
	/**
	 * Méthode pour changer la couleur du point de livraison sélectionné.
	 */
	public void changerCouleurSelectionnee() {
		if(activeChangerCouleurSelectionne == true) {
			this.setFill(Color.YELLOW);
		}
	}
	
	/**
	 * Méthode pour changer la couleur du point de livraison non sélectionné.
	 */
	public void changerCouleurNonSelectionnee() {
		if(activeChangerCouleurSelectionne == true) {
			this.setFill(originalColor);
		}
	}

	/*
	public void effaceListenerOnClick() {
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(final MouseEvent event) {
				
			}
		});
		
	}*/

	
	
}

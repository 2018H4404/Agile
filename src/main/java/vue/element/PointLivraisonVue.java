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
	 * @param x : coordonnee X de ce cercle.
	 * @param y : coordonnee Y de ce cercle.
	 * @param radius : rayon de ce cercle.
	 * @param unId : l'id de l'objet PointLivraison auquel ce cercle correspond.
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
	
	/**
	 * Methode pour mettre la valeur du boolean activeChangerCouleurSelectionne
	 * (le boolean qui decide si le changement de couleur est active)
	 * @param activeChangerCouleurSelectionne : un boolean.
	 */
	public void setActiveChangerCouleurSelectionne(boolean activeChangerCouleurSelectionne) {
		this.activeChangerCouleurSelectionne = activeChangerCouleurSelectionne;
	}

	/**
	 * Methode pour obtenir l'id du point de livraison auquel cette vue correspond.
	 * @return l'id du point de livraison auquel cette vue correspond.
	 */
	public long getIntersectionId() {
		return idPointLivraison;
	}

	/**
	 * Methode pour verifier si le PointLivraisonVue est selectionne
	 * @return true si selectionnee, sinon false.
	 */
	public boolean isSelectionnee() {
		return selectionnee;
	}
	/**
	 * Methode pour verifier si le PointLivraisonVue est synchronisee
	 * @return true si synchronisee, sinon false.
	 */
	public boolean isSynchronisee() {
		return synchronisee;
	}

	/**
	 * Methode pour mettre la valeur du boolean synchronisee de cette vue.
	 * @param selectionnee : un boolean.
	 */
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
	
	/**
	 * Methode pour mettre la valeur du boolean selectionnee de cette vue.
	 * @param selectionnee : un boolean.
	 */
	public void setSelectionnee(boolean selectionnee) {
		this.selectionnee = selectionnee;
	}
	
	/**
	 * Methode pour mettre cette vue qui represente un point de livraison en mode synchronise.
	 */
	public void changerFormeSynchronise() {
		synchronisee = true;
		this.setRadius(8);
		this.setFill(Color.ORANGE);
		this.originalColor = Color.ORANGE;
	}
	
	/**
	 * Methode pour mettre la couleur originale de cette TourneeVue.
	 * @param c : une couleur.
	 */
	public void setOriginalColor(Color couleur) {
		this.originalColor = couleur;
	}
	
	/**
	 * Methode pour ajouter des listners.
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
	 * Methode pour changer la couleur de ce cercle qui correspond a un point de livraison en mode selectionnee.
	 */
	public void changerCouleurSelectionnee() {
		if(activeChangerCouleurSelectionne == true) {
			this.setFill(Color.YELLOW);
		}
	}
	
	/**
	 * Methode pour changer la couleur de ce cercle qui correspond a un point de livraison en mode non selectionnee.
	 */
	public void changerCouleurNonSelectionnee() {
		if(activeChangerCouleurSelectionne == true) {
			this.setFill(originalColor);
		}
	}

	
	
}

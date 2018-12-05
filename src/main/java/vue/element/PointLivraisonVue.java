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
		this.setFill(Color.CORNFLOWERBLUE);
		this.originalColor = Color.CORNFLOWERBLUE;
		this.idPointLivraison = unId;
		this.selectionnee = false;
		
		ajouterListener();
	}
	
	public long getIntersectionId() {
		return idPointLivraison;
	}

	public boolean isSelectionnee() {
		return selectionnee;
	}

	public void setSelectionnee(boolean selectionnee) {
		this.selectionnee = selectionnee;
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
					Controleur.getInstance().getMaDemande().supprimerPoint(idPointLivraison);
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
		this.setFill(Color.YELLOW);
	}
	
	/**
	 * Méthode pour changer la couleur du point de livraison non sélectionné.
	 */
	public void changerCouleurNonSelectionnee() {
		this.setFill(originalColor);
	}
	
}

package vue.element;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** 
 * La classe de la vue de l'intersection normale.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/
@SuppressWarnings("restriction")
public class IntersectionNormalVue extends Circle{
	
	private long idInter;
	private boolean selectionnee;
	private Color originalColor;
	
	/**
	 * Constructeur de la vue de l'intersection normale.
	 * @param x
	 * @param y
	 * @param radius
	 * @param unId
	 */
	public IntersectionNormalVue(double x, double y, double radius, long unId) {
		super(x,y,radius);
		this.setFill(Color.GRAY);
		this.originalColor = Color.GRAY;
		this.idInter = unId;
		this.selectionnee = false;
		ajouterListener();
	}
	
	public long getIntersectionId() {
		return idInter;
	}
	
	public boolean isSelectionnee() {
		return selectionnee;
	}

	public void setSelectionnee(boolean selectionnee) {
		this.selectionnee = selectionnee;
	}

	/**
	 * Méthode du listner.
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
	}
	
	/**
	 * Méthode pour changer la couleur de l'intersection selectionnée.
	 */
	public void changerCouleurSelectionnee() {
		this.setFill(Color.YELLOW);
	}
	
	/**
	 * Méthode pour changer la couleur de l'intersection non sélectionnée.
	 */
	public void changerCouleurNonSelectionnee() {
		this.setFill(originalColor);
	}
}

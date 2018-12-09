package vue.element;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/** 
 * La classe de la vue du troncon.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

@SuppressWarnings("restriction")
public class TronconVue extends Line{
	
	private String rueName;
	private Color originalColor;
	
	/**
	 * constructeur de la vue troncon.
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param name
	 */
	public TronconVue(double startX, double startY, double endX, double endY, String name) {
		super(startX,startY,endX,endY);
		this.rueName = name;
		this.originalColor = Color.web("0xaaaaaa",1.0);
		this.setStroke(Color.web("0xaaaaaa",1.0));
		this.setStrokeWidth(3);
		ajouterListner();
	}
	
	/**
	 * Méthode pour ajouter un listner.
	 */
	public void ajouterListner() {
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
	 * Méthode pour changer la couleur du troncon sélectionné.
	 */
	public void changerCouleurSelectionnee() {
		this.setStroke(Color.ORANGE);
	}
	
	/**
	 * Méthode pour changer la couleur du troncon non séléctionné.
	 */
	public void changerCouleurNonSelectionnee() {
		this.setStroke(originalColor);
	}
	
	public void setOriginalColor(Color c) {
		this.originalColor = c;
	}
	
	public String getNomRue() {
		return rueName;
	}
}

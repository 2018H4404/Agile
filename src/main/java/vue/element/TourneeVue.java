package vue.element;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/** 
 * La classe de la vue de la trounée.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

@SuppressWarnings("restriction")
public class TourneeVue extends Line{
	
	private String rueName;
	private Color originalColor;
	
	/**
	 * Constructeur de la vue tournée.
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param name
	 */
	public TourneeVue(double startX, double startY, double endX, double endY, String name, Color couleur) {
		super(startX,startY,endX,endY);
		this.rueName = name;
		this.originalColor = couleur;
		this.setStroke(couleur);
		this.setStrokeWidth(2);
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
	 * Méthode pour changer la couleur de la tournée sélectionnée.
	 */
	public void changerCouleurSelectionnee() {
		this.setStroke(Color.ORANGE);
	}
	
	/**
	 * Méthode pour changer la couleur de la tournée non sélectionnée.
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

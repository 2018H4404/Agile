package vue.element;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/** 
 * La classe de la vue de la trounee.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

@SuppressWarnings("restriction")
public class TourneeVue extends Line{
	
	private String rueName;
	private Color originalColor;
	
	/**
	 * Constructeur de la vue tournee.
	 * @param startX : coordonnee X du point de debut de cette ligne.
	 * @param startY : coordonnee Y du point de fin de cette ligne.
	 * @param endX : coordonnee X du point de debut de cette ligne.
	 * @param endY : coordonnee Y du point de fin de cette ligne.
	 * @param name : le nom de rue auquel ce troncon d'une tournee correspond.
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
	 * Methode pour ajouter des listners.
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
	 * Methode pour changer la couleur de cette lignequi correspond a un troncon d'une tournee en mode selectionnee.
	 */
	public void changerCouleurSelectionnee() {
		this.setStroke(Color.ORANGE);
	}
	
	/**
	 * Methode pour changer la couleur de cette ligne qui correspond a un troncon d'une tournee en mode non selectionnee.
	 */
	public void changerCouleurNonSelectionnee() {
		this.setStroke(originalColor);
	}
	
	/**
	 * Methode pour mettre la couleur originale de cette TourneeVue.
	 * @param c : une couleur.
	 */
	public void setOriginalColor(Color c) {
		this.originalColor = c;
	}
	
	/**
	 * Methode pour retourner le nom de rue auquel ce troncon d'une tournee correspond.
	 * @return le nom de rue correspondant.
	 */
	public String getNomRue() {
		return rueName;
	}
}

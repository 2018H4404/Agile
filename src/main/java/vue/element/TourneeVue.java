package vue.element;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

@SuppressWarnings("restriction")
public class TourneeVue extends Line{
	
	private String rueName;
	private Color originalColor;
	
	public TourneeVue(double startX, double startY, double endX, double endY, String name) {
		super(startX,startY,endX,endY);
		this.rueName = name;
		this.originalColor = Color.CHARTREUSE;
		this.setStroke(Color.CHARTREUSE);
		this.setStrokeWidth(2);
		ajouterListner();
	}
	
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
	
	public void changerCouleurSelectionnee() {
		this.setStroke(Color.ORANGE);
	}
	
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

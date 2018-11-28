package vue.element;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

@SuppressWarnings("restriction")
public class PointLivraisonVue extends Circle{
	
	private long idPointLivraison;
	private boolean selectionnee;
	private Color originalColor;
	
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
	
	public void changerCouleurSelectionnee() {
		this.setFill(Color.YELLOW);
	}
	
	public void changerCouleurNonSelectionnee() {
		this.setFill(originalColor);
	}
	
}

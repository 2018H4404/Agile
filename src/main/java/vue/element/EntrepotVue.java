package vue.element;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

@SuppressWarnings("restriction")
public class EntrepotVue extends Circle{
	
	private long idEntrepot;
	
	public EntrepotVue(double x, double y, double radius, long unId) {
		super(x,y,radius);
		this.setFill(Color.DARKRED);
		this.idEntrepot = unId;
	}
	
	public long getIntersectionId() {
		return idEntrepot;
	}
	
}

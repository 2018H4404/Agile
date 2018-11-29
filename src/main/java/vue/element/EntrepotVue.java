package vue.element;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** 
 * La classe de la vue de l'entrepôt.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

@SuppressWarnings("restriction")
public class EntrepotVue extends Circle{
	
	private long idEntrepot;
	
	/**
	 * Contructeur de la vue de l'entrepôt.
	 * @param x
	 * @param y
	 * @param radius
	 * @param unId
	 */
	public EntrepotVue(double x, double y, double radius, long unId) {
		super(x,y,radius);
		this.setFill(Color.DARKRED);
		this.idEntrepot = unId;
	}
	
	public long getIntersectionId() {
		return idEntrepot;
	}
	
}

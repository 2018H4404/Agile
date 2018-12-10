package vue.element;

import javafx.scene.control.TitledPane;

/** 
 * La classe de la vue de du panel livraison.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/
@SuppressWarnings("restriction")
public class LivraisonPane extends TitledPane{
	private long id;
	
	public LivraisonPane(long unId) {
		super();
		this.id = unId;
	}
	
	public long getLivraisonId() {
		return this.id;
	}
}

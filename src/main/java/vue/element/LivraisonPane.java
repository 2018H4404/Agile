package vue.element;

import javafx.scene.control.TitledPane;

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

package vue.element;

import controleur.Controleur;
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
	private double copieX;
	private double copieY;
	/**
	 * Constructeur de la vue de l'intersection normale.
	 * @param x
	 * @param y
	 * @param radius
	 * @param unId
	 */
	public IntersectionNormalVue(double x, double y, double radius, long unId) {
		super(x,y,radius);
		this.setFill(Color.BLACK);
		this.originalColor = Color.BLACK;
		this.idInter = unId;
		this.selectionnee = false;
		this.copieX = x;
		this.copieY = y;
		ajouterListener();
	}
	
	public long getIntersectionId() {
		return idInter;
	}
	
	/**
	 * Methode pour verifier si l'IntersectionVue est selectione
	 * @return
	 */
	public boolean isSelectionnee() {
		return selectionnee;
	}

	public void setSelectionnee(boolean selectionnee) {
		this.selectionnee = selectionnee;
	}

	/**
	 * Méthode du listener.
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
	
	public void ajouterListenerOnClick() {
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(final MouseEvent event) {
                try {
                	if(Controleur.getInstance().getEtatCourant().getClass().getSimpleName().equals("EtatDemandeLivraison")) {
                		Controleur.getInstance().getMaDemande().ajouterPoint(idInter,
            					Controleur.getInstance().reverseTransformLatitude(copieY, Controleur.getInstance().getGraph().getLargeur()),
            					Controleur.getInstance().reverseTransformLongitude(copieX, Controleur.getInstance().getGraph().getHauteur())
            					);
                	}else {
                		
                	}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}  
        });
	}
	
	/*
	public void effaceListenerOnClick() {
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(final MouseEvent event) {
				
			}
		});
	}*/
	
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

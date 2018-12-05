package vue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import controleur.Controleur;

import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import modele.TourneeManager;
import modele.metier.Chemin;
import modele.metier.DemandeLivraison;
import modele.metier.Entrepot;
import modele.metier.IntersectionNormal;
import modele.metier.Plan;
import modele.metier.PointLivraison;
import modele.metier.Tournee;
import modele.metier.Troncon;

import vue.element.EntrepotVue;
import vue.element.IntersectionNormalVue;
import vue.element.PointLivraisonVue;
import vue.element.TourneeVue;
import vue.element.TronconVue;
import vue.handler.GroupHandler;

/** 
 * La classe de la vue graphique.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

@SuppressWarnings("restriction")
public class VueGraphique extends Parent implements Observer{
	
	private Group tronconGroup;
	private Group noeudGroup;
	private Group entrepotGroup;
	private Group livraisonGroup;
	private Group tourneesAfficheesGroup;
	private ArrayList<Group> tourneesGroup;
	private VueTextuelle compagnie;
	private  double hauteur = 800;
	private  double largeur = 800;
	private static Color[] couleurs = {Color.CRIMSON,Color.CYAN,Color.FUCHSIA,Color.GREENYELLOW,Color.LIMEGREEN,Color.SKYBLUE};
	
	/**
	 * Constructeur de la vue graphique.
	 */
	public VueGraphique() {
		//Intialisation de sa compagnie par defaut
		compagnie = null;
		
		//Initialisation du contenu
		ScrollPane paneGraphique = new ScrollPane();
		BorderPane container = new BorderPane();
		Slider zoomSlider = new Slider();


		zoomSlider.setMin(1);
		zoomSlider.setMax(3);
		zoomSlider.setValue(1);
		zoomSlider.setBlockIncrement(0.01);
		
		paneGraphique.setPrefViewportHeight(800);
		paneGraphique.setPrefViewportWidth(800);
		noeudGroup = new Group();
		tronconGroup = new Group();
		entrepotGroup = new Group();
		livraisonGroup = new Group();
		tourneesAfficheesGroup = new Group();
		tourneesGroup = new ArrayList<Group>();
		Group rootGroup = new Group();
		GroupHandler handler = new GroupHandler(rootGroup); 
		rootGroup.setOnMousePressed(handler);
		rootGroup.setOnMouseDragged(handler);
		
		//Ajouter les actions suivant les Événements
		

		rootGroup.getChildren().add(tronconGroup);
		rootGroup.getChildren().add(noeudGroup);
		rootGroup.getChildren().add(entrepotGroup);
		rootGroup.getChildren().add(livraisonGroup);
		rootGroup.getChildren().add(tourneesAfficheesGroup);

		rootGroup.scaleXProperty().bind(zoomSlider.valueProperty());
		rootGroup.scaleYProperty().bind(zoomSlider.valueProperty());
		
		paneGraphique.setContent(rootGroup);
		container.setCenter(paneGraphique);

		container.setBottom(zoomSlider);
		
		//Ajouter le EventListner
		ajouterEventListner();
		
		this.getChildren().add(container);
	}
	
	public Group getNoeudGroup() {
		return this.noeudGroup;
		
	}
	
	public Group getLivraisonGroup() {
		return this.livraisonGroup;
	}
	
	/**
	 * Méthode pour ajouter l'event listener.
	 */
	public void ajouterEventListner() {
		tronconGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
                if(event.getTarget() instanceof TronconVue) {
                	TronconVue temp = (TronconVue)event.getTarget();
                	compagnie.setTabNomRue(temp.getNomRue());
                }
            }
        });
	}
	
	public void ajouterListenerRueNom(Group g) {
		g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
                if(event.getTarget() instanceof TourneeVue) {
                	TourneeVue temp = (TourneeVue)event.getTarget();
                	compagnie.setTabNomRue(temp.getNomRue());
                }
            }
        });
	}
	
	public void setCompagnie(VueTextuelle vue) {
		this.compagnie = vue;
	}
	
	public double getHauteur() {
		return hauteur;
		
	}
	
	public double getLargeur() {
		return largeur;
		
	}
	
	/**
	 * Méthode pour déssiner le plan.
	 * @param monPlan mon plan.
	 */
	public void dessinerPlan(Plan monPlan) {
		clearVue();
		Collection<IntersectionNormal> lesIntersections = monPlan.getAllIntersectionNormals();
		Collection<ArrayList<Troncon>> lesTroncons = monPlan.getAllTroncons();
		int counter = 0;
		System.out.println(lesIntersections.size());
		for(ArrayList<Troncon> troncons : lesTroncons) {
			for(Troncon troncon : troncons) {
				IntersectionNormal depart = troncon.getOrigine();
				IntersectionNormal destination = troncon.getDestination();
				TronconVue tempLigne = new TronconVue(monPlan.transformLongitude(depart.getLongitude(), largeur),monPlan.transformLatitude(depart.getLatitude(), hauteur),
														monPlan.transformLongitude(destination.getLongitude(), largeur),monPlan.transformLatitude(destination.getLatitude(), hauteur),troncon.getNomRue());
				tronconGroup.getChildren().add(tempLigne);
				counter++;
			}
		}
			for(IntersectionNormal intersection : lesIntersections) {
				IntersectionNormalVue tempPoint = new IntersectionNormalVue(monPlan.transformLongitude(intersection.getLongitude(), largeur),monPlan.transformLatitude(intersection.getLatitude(), hauteur),4,intersection.getId());
				noeudGroup.getChildren().add(tempPoint);
			}
		
		System.out.println(counter);
		System.out.println("Done Plan");
	}
	
	/**
	 * Méthode pour dessiner les demandes de livraison.
	 * @param maDemande mes demandes de livraison.
	 */
	public void dessinerDemandeLivraison(DemandeLivraison maDemande) {
		clearEntrepotLivraison();
		clearTournees();
		Collection<Entrepot> lesEntrepots = maDemande.getAllEntrepots();
		Collection<PointLivraison> lesPointLivraisons = maDemande.getAllPointLivraisons();
		System.out.println(lesEntrepots.size());
		System.out.println(lesPointLivraisons.size());
		for(Entrepot entrepot : lesEntrepots) {
			EntrepotVue tempEntrepot = new EntrepotVue(Controleur.getInstance().transformerLongitude(entrepot.getLongitude(), largeur),Controleur.getInstance().transformerLatitude(entrepot.getLatitude(), hauteur),6,entrepot.getId());
			entrepotGroup.getChildren().add(tempEntrepot);
		}
		for(PointLivraison livraison : lesPointLivraisons) {
			PointLivraisonVue tempPointLivraison = new PointLivraisonVue(Controleur.getInstance().transformerLongitude(livraison.getLongitude(), largeur),Controleur.getInstance().transformerLatitude(livraison.getLatitude(), hauteur),6,livraison.getId());
			livraisonGroup.getChildren().add(tempPointLivraison);
		}
		compagnie.afficheListLivraison(lesPointLivraisons);
	}
	
	/**
	 * Méthode pour generer des couleurs aleatoires que nous utilisons afin de dessiner les tournees.
	 * @param nbLivreur : nombre des livreurs
	 */
	public Color genererCouleurs(int index) {
		return couleurs[index%couleurs.length];
	}
	
	/**
	 * Méthode pour déssiner les tournées.
	 * @param manager le manager des tournée.
	 */
	public void dessinerTournees(TourneeManager manager) {
		clearTournees();
		ArrayList<Tournee> tournees = manager.getListeTournees();
		int index = 0;
		for(Tournee tournee : tournees) {
			Group tempGroup = new Group();
			Color tourneeCouleur = genererCouleurs(index);
			ArrayList<Chemin> tempChemins = tournee.getListeChemins();
			for(Chemin chemin : tempChemins) {
				ArrayList<Troncon> tempTroncons = chemin.getListeTroncons();
				for(Troncon troncon : tempTroncons) {
					IntersectionNormal depart = troncon.getOrigine();
					IntersectionNormal destination = troncon.getDestination();
					TourneeVue tempTronconTournee = new TourneeVue(Controleur.getInstance().transformerLongitude(depart.getLongitude(), largeur),Controleur.getInstance().transformerLatitude(depart.getLatitude(), hauteur),
							Controleur.getInstance().transformerLongitude(destination.getLongitude(), largeur),Controleur.getInstance().transformerLatitude(destination.getLatitude(), hauteur),troncon.getNomRue(), tourneeCouleur);
					tempGroup.getChildren().add(tempTronconTournee);
				}
			}
			ajouterListenerRueNom(tempGroup);
			tourneesGroup.add(tempGroup);
			tourneesAfficheesGroup.getChildren().add(tempGroup);
			index++;
		}
		System.out.println("Done Tournees");
	}
	
	/**
	 * Méthode pour filtrer les tournees affichees.
	 * @param afficheGroup : les numeros des tournees a afficher
	 */
	public void filtrerTournees(ArrayList<Integer> afficheGroup) {
		clearAfficheeTournees();
		for(Integer i : afficheGroup) {
			tourneesAfficheesGroup.getChildren().add(tourneesGroup.get(i));
		}
	}
	
	/**
	 * Méthode pour effacer la vue.
	 */	
	public void clearVue() {
		clearPlan();
		clearEntrepotLivraison();
		clearTournees();
	}
	
	/**
	 * Méthode pour effacer le plan.
	 */	
	public void clearPlan() {
		tronconGroup.getChildren().clear();
		noeudGroup.getChildren().clear();
	}
	
	/**
	 * Méthode pour effacer les entrepots et les points de livraison.
	 */	
	public void clearEntrepotLivraison() {
		entrepotGroup.getChildren().clear();
		livraisonGroup.getChildren().clear();
	}
	
	/**
	 * Méthode pour effacer les tournees stockees et affichees.
	 */	
	public void clearTournees() {
		tourneesAfficheesGroup.getChildren().clear();
		for(int i = 0; i < tourneesGroup.size(); i++) {
			tourneesGroup.get(i).getChildren().clear();
		}
		tourneesGroup.clear();
	}
	
	/**
	 * Méthode pour effacer juste les tournees affichees.
	 */	
	public void clearAfficheeTournees() {
		tourneesAfficheesGroup.getChildren().clear();
	}
	
	public void synchronisationLivraison(long id) {
		ObservableList<Node> tempLivraisons = livraisonGroup.getChildren();
		for(Node e : tempLivraisons) {
			if(e instanceof PointLivraisonVue) {
				PointLivraisonVue tempReference = (PointLivraisonVue)e;
				tempReference.changerCouleurNonSelectionnee();
				if(tempReference.getIntersectionId() == id) {
					if(!tempReference.isSynchronisee()) {
						tempReference.changerFormeSynchronise();
					}else {
						tempReference.setSynchronisee(false);
					}
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub\
		String sujet = (String)arg;
		switch(sujet) {
			case "Plan":
				dessinerPlan((Plan)o);
				break;
			case "DemandeLivraison":
				dessinerDemandeLivraison((DemandeLivraison)o);
				break;
			case "Tournees":
				dessinerTournees((TourneeManager)o);
				break;	
			case "Alert Temps":
				dessinerTournees((TourneeManager)o);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setHeaderText("Attention");
		        alert.setContentText("Dépasser la limite de temps : 10 secondes! Une Solution est affichée mais elle n'est pas optimale.");
		        alert.show();
				break;	
				
		}
		
	}

	
}

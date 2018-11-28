package vue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import controleur.Controleur;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert;

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





@SuppressWarnings("restriction")
public class VueGraphique extends Parent implements Observer{
	
	private Group tronconGroup;
	private Group noeudGroup;
	private Group entrepotGroup;
	private Group livraisonGroup;
	private Group tourneeGroup;
	private VueTextuelle compagnie;
	private static double hauteur = 800;
	private static double largeur = 800;
	
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
		tourneeGroup = new Group();
		Group rootGroup = new Group();
		GroupHandler handler = new GroupHandler(rootGroup); 
		rootGroup.setOnMousePressed(handler);
		rootGroup.setOnMouseDragged(handler);
		
		//Ajouter les actions suivant les Événements
		

		rootGroup.getChildren().add(tronconGroup);
		rootGroup.getChildren().add(noeudGroup);
		rootGroup.getChildren().add(entrepotGroup);
		rootGroup.getChildren().add(livraisonGroup);
		rootGroup.getChildren().add(tourneeGroup);
		rootGroup.scaleXProperty().bind(zoomSlider.valueProperty());
		rootGroup.scaleYProperty().bind(zoomSlider.valueProperty());
		
		paneGraphique.setContent(rootGroup);
		container.setCenter(paneGraphique);
		
		container.setBottom(zoomSlider);
		
		//Ajouter le EventListner
		ajouterEventListner();
		
		this.getChildren().add(container);
	}
	
	public void ajouterEventListner() {
		tronconGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
                if(event.getTarget() instanceof TronconVue) {
                	TronconVue temp = (TronconVue)event.getTarget();
                	compagnie.setTabNomRue(temp.getNomRue());
                }
            }
        });
		
		tourneeGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		System.out.println("Done Demande");
	}
	
	public void dessinerTournees(TourneeManager manager) {
		clearTournees();
		ArrayList<Tournee> tournees = manager.getListeTournees();
		for(Tournee tournee : tournees) {
			ArrayList<Chemin> tempChemins = tournee.getListeChemins();
			for(Chemin chemin : tempChemins) {
				ArrayList<Troncon> tempTroncons = chemin.getListeTroncons();
				for(Troncon troncon : tempTroncons) {
					IntersectionNormal depart = troncon.getOrigine();
					IntersectionNormal destination = troncon.getDestination();
					TourneeVue tempTronconTournee = new TourneeVue(Controleur.getInstance().transformerLongitude(depart.getLongitude(), largeur),Controleur.getInstance().transformerLatitude(depart.getLatitude(), hauteur),
							Controleur.getInstance().transformerLongitude(destination.getLongitude(), largeur),Controleur.getInstance().transformerLatitude(destination.getLatitude(), hauteur),troncon.getNomRue());
					tourneeGroup.getChildren().add(tempTronconTournee);
				}
			}
		}
		System.out.println("Done Tournees");
	}
	
	public void clearVue() {
		tronconGroup.getChildren().clear();
		noeudGroup.getChildren().clear();
		entrepotGroup.getChildren().clear();
		livraisonGroup.getChildren().clear();
		tourneeGroup.getChildren().clear();
	}
	
	public void clearEntrepotLivraison() {
		entrepotGroup.getChildren().clear();
		livraisonGroup.getChildren().clear();
	}
	
	public void clearTournees() {
		tourneeGroup.getChildren().clear();
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
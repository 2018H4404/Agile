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
import javafx.scene.layout.GridPane;
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
	private ApplicationDemo parent;
	private PointLivraisonVue ajoutPointChoisi = null;
	private IntersectionNormalVue ajoutInterChoisi = null;
	private  double hauteur;
	private  double largeur;
	private static Color[] couleurs = {Color.CRIMSON,Color.CYAN,Color.FUCHSIA,Color.GREENYELLOW,Color.LIMEGREEN,Color.SKYBLUE};
	
	/**
	 * Constructeur de la vue graphique.
	 */
	public VueGraphique(double lFenetre, double hFenetre, ApplicationDemo unParent) {
		//Intialisation de sa compagnie par defaut
		compagnie = null;
		parent = unParent;
		
		//Initialisation du contenu
		ScrollPane paneGraphique = new ScrollPane();
		BorderPane container = new BorderPane();
		Slider zoomSlider = new Slider();

		zoomSlider.setMin(1);
		zoomSlider.setMax(3);
		zoomSlider.setValue(1);
		zoomSlider.setBlockIncrement(0.01);
		
		hauteur = hFenetre*0.85;
		largeur = hFenetre*0.85;
		paneGraphique.setPrefViewportHeight(hFenetre*0.85);
		paneGraphique.setPrefViewportWidth(hFenetre*0.85);
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
		
		//Ajouter les actions suivant les √âv√©nements
		
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
	 * M√©thode pour ajouter l'event listener.
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
		
		noeudGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
                if(event.getTarget() instanceof IntersectionNormalVue) {
                	 if(Controleur.getInstance().getEtatCourant().getClass().getSimpleName().equals("EtatAjouterChoixNouvellePointLivraison")){
                		try {
                			 IntersectionNormalVue temp = (IntersectionNormalVue)event.getTarget();
                			 ajoutInterChoisi = temp;
                			 int duree = parent.getDuree();
                			 if(duree == Integer.MAX_VALUE) {
                				 Exception e = new Exception();
                				 throw e;
                			 }
                			 temp.setFill(Color.GREEN);
                			 temp.setRadius(8);
                			 Controleur.getInstance().setAjoutNouvellePoint(temp.getIntersectionId(),duree);
                			 parent.VerifierEtat(Controleur.getInstance());
                			 //Remettre a l'etat initial (couleur, radius)
                			 ajoutInterChoisi.setRadius(4);
                           	 ajoutInterChoisi.setFill(Color.GRAY);
                			 ajoutPointChoisi.setFill(Color.CORNFLOWERBLUE);
                			 ajoutPointChoisi.setRadius(6);
                			 ajoutPointChoisi.setActiveChangerCouleurSelectionne(true);
                			 //Rajout du point de livraison
                			 IntersectionNormal tempInter = Controleur.getInstance().getMonPlan().getIntersectionNormal(temp.getIntersectionId());
                			 PointLivraisonVue tempPointLivraison = new PointLivraisonVue(Controleur.getInstance().transformerLongitude(tempInter.getLongitude(), largeur),Controleur.getInstance().transformerLatitude(tempInter.getLatitude(), hauteur),6,tempInter.getId());
                			 livraisonGroup.getChildren().add(tempPointLivraison);
                			 parent.setInfo("Point de livraison ajout®¶");
                		}catch(Exception e) {
                			System.out.println("Duree incorrect ou Probleme durant Ajout");
                			e.printStackTrace();
                		}
                	 }
                }
            }
        });
		livraisonGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
                if(event.getTarget() instanceof PointLivraisonVue) {
                	if(Controleur.getInstance().getEtatCourant().getClass().getSimpleName().equals("EtatAjouterChoixPointLivraison")) {
                		try {
                			PointLivraisonVue temp = (PointLivraisonVue)event.getTarget();
                			ajoutPointChoisi = temp;
                			temp.setActiveChangerCouleurSelectionne(false);
                			temp.setFill(Color.GREEN);
                			temp.setRadius(8);
                			Controleur.getInstance().setAjoutDepart(temp.getIntersectionId());
                			parent.VerifierEtat(Controleur.getInstance());
                			parent.setInfo("Choisissez maintenant o®¥ vous voulez rajouter un nouveau point de Livraison apr®®s le point de livraison que vous venez de choisir.");
                		}catch(Exception e) {
                			System.out.println("Duree incorrect ou Probleme durant Ajout");
                			e.printStackTrace();
                		}
                	 }else if(Controleur.getInstance().getEtatCourant().getClass().getSimpleName().equals("EtatSupprimerChoixPointLivraison")) {
                		 try {
                 			PointLivraisonVue temp = (PointLivraisonVue)event.getTarget();
                 			temp.setActiveChangerCouleurSelectionne(false);
                 			temp.setFill(Color.GREEN);
                 			temp.setRadius(8);
                 			Controleur.getInstance().setSupprimerPointLivraison(temp.getIntersectionId());
                 			livraisonGroup.getChildren().remove(temp);
                 			parent.VerifierEtat(Controleur.getInstance());
                 			parent.setInfo("Point de livraison supprim®¶.");
                 		}catch(Exception e) {
                 			System.out.println("Duree incorrect ou Probleme durant Ajout");
                 			e.printStackTrace();
                 		}
                	 }
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
	 * M√©thode pour d√©ssiner le plan.
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
	 * M√©thode pour dessiner les demandes de livraison.
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
	}
	
	/**
	 * M√©thode pour generer des couleurs aleatoires que nous utilisons afin de dessiner les tournees.
	 * @param nbLivreur : nombre des livreurs
	 */
	public Color genererCouleurs(int index) {
		return couleurs[index%couleurs.length];
	}
	
	/**
	 * M√©thode pour d√©ssiner les tourn√©es.
	 * @param manager le manager des tourn√©e.
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
	 * M√©thode pour filtrer les tournees affichees.
	 * @param afficheGroup : les numeros des tournees a afficher
	 */
	public void filtrerTournees(ArrayList<Integer> afficheGroup) {
		clearAfficheeTournees();
		for(Integer i : afficheGroup) {
			tourneesAfficheesGroup.getChildren().add(tourneesGroup.get(i));
		}
	}
	
	/**
	 * M√©thode pour effacer la vue.
	 */	
	public void clearVue() {
		clearPlan();
		clearEntrepotLivraison();
		clearTournees();
	}
	
	/**
	 * M√©thode pour effacer le plan.
	 */	
	public void clearPlan() {
		tronconGroup.getChildren().clear();
		noeudGroup.getChildren().clear();
	}
	
	/**
	 * M√©thode pour effacer les entrepots et les points de livraison.
	 */	
	public void clearEntrepotLivraison() {
		entrepotGroup.getChildren().clear();
		livraisonGroup.getChildren().clear();
	}
	
	/**
	 * M√©thode pour effacer les tournees stockees et affichees.
	 */	
	public void clearTournees() {
		tourneesAfficheesGroup.getChildren().clear();
		for(int i = 0; i < tourneesGroup.size(); i++) {
			tourneesGroup.get(i).getChildren().clear();
		}
		tourneesGroup.clear();
	}
	
	/**
	 * M√©thode pour effacer juste les tournees affichees.
	 */	
	public void clearAfficheeTournees() {
		tourneesAfficheesGroup.getChildren().clear();
	}
	
	/**
	 * M√©thode pour synchroniser la vue avec la vue textuelle.
	 * @param id : id de PointLivraisonVue qui doit ®∫tre synchronis®¶
	 * @param expanded : boolean qui indique si le TitledPane est expanded
	 */	
	public void synchronisationLivraison(long id,boolean expanded) {
		ObservableList<Node> tempLivraisons = livraisonGroup.getChildren();
		for(Node e : tempLivraisons) {
			if(e instanceof PointLivraisonVue) {
				PointLivraisonVue tempReference = (PointLivraisonVue)e;
				tempReference.changerCouleurNonSelectionnee();
				if(tempReference.getIntersectionId() == id) {
					if(expanded) {
						tempReference.changerFormeSynchronise();
					}else {
						tempReference.setSynchronisee(false);
						tempReference.setOriginalColor(Color.CORNFLOWERBLUE);
						tempReference.changeRadius(6);
						tempReference.changerCouleurNonSelectionnee();
					}
				}
			}
		}
	}
	
	/**
	 * M√©thode pour arr®∫ter temporairement la synchronisation.
	 */	
	public void arreterSynchronisationLivraison() {
		ObservableList<Node> tempLivraisons = livraisonGroup.getChildren();
		for(Node e : tempLivraisons) {
			PointLivraisonVue tempReference = (PointLivraisonVue)e;
			tempReference.setSynchronisee(false);
			tempReference.setOriginalColor(Color.CORNFLOWERBLUE);
			tempReference.changeRadius(6);
			tempReference.changerCouleurNonSelectionnee();
		}
		compagnie.arreterSynchronisationLivraison();
	}
	
	/**
	 * M√©thode pour changer juste l'affichage d'une tourn®¶e.
	 * @param manager : objet TourneeManager stock®¶ dans le controleur
	 */	
	public void changerVueUneTournee(TourneeManager manager) {
		int index = manager.getTourneeChangedIndex();
		Group tempReference = tourneesGroup.get(index);
		tempReference.getChildren().clear();
		Color tourneeCouleur = genererCouleurs(index);
		ArrayList<Chemin> tempChemins = manager.getListeTournees().get(index).getListeChemins();
		for(Chemin chemin : tempChemins) {
			ArrayList<Troncon> tempTroncons = chemin.getListeTroncons();
			for(Troncon troncon : tempTroncons) {
				IntersectionNormal depart = troncon.getOrigine();
				IntersectionNormal destination = troncon.getDestination();
				TourneeVue tempTronconTournee = new TourneeVue(Controleur.getInstance().transformerLongitude(depart.getLongitude(), largeur),Controleur.getInstance().transformerLatitude(depart.getLatitude(), hauteur),
						Controleur.getInstance().transformerLongitude(destination.getLongitude(), largeur),Controleur.getInstance().transformerLatitude(destination.getLatitude(), hauteur),troncon.getNomRue(), tourneeCouleur);
				tempReference.getChildren().add(tempTronconTournee);
			}
		}
		ajouterListenerRueNom(tempReference);
	}
	
	/**
	 * M√©thode pour supprimer une tourn®¶e.
	 * @param manager : objet TourneeManager stock®¶ dans le controleur
	 */	
	public void supprimerUneTournee(TourneeManager manager) {
		int index = manager.getTourneeChangedIndex();
		Group tempReference = tourneesGroup.get(index);
		tourneesAfficheesGroup.getChildren().remove(tempReference);
		tourneesGroup.remove(tempReference);
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
			case "UniqueTournee":
				changerVueUneTournee((TourneeManager)o);
				break;
			case "SupprimerTournee":
				supprimerUneTournee((TourneeManager)o);
				break;	
			case "Alert Temps":
				dessinerTournees((TourneeManager)o);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setHeaderText("Attention");
		        alert.setContentText("D√©passer la limite de temps : 10 secondes! Une Solution est affich√©e mais elle n'est pas optimale.");
		        alert.show();
				break;			
		}	
	}	
}

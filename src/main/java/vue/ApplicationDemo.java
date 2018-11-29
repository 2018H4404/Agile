package vue;

import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import modele.TourneeManager;
import modele.metier.DemandeLivraison;
import modele.metier.Intersection;
import modele.metier.IntersectionNormal;
import modele.metier.Plan;
import services.Paire;
import controleur.Controleur;

/** 
 * La classe de la démo de l'application.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

enum ETAT {
    EtatInit,EtatPlanCharge,EtatDemandeLivraison;

}

@SuppressWarnings("restriction")
public class ApplicationDemo extends Application{
	private VueGraphique graph;
	private VueTextuelle texte;
	private Button buttonChargePlan;
	private Button buttonChargeDemandeLivraison;
	private Button buttonCalculer;
	private Button buttonEfface;

	private MenuBar menuBar;
	private Menu menuFile;
	private Menu menuTournee;
	private Menu menuLivraison;
	private MenuItem itemChargerPlan;
	private MenuItem itemChargerDemandeLivraison;
	private MenuItem itemCalculerTournees;
	private MenuItem itemEffacer;
	private MenuItem itemAjouterLivraison;
	private MenuItem itemSupprimerLivraison;


	

	/**
	 * Méthode permettant de commencer la démo de l'application.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        graph = new VueGraphique();
        texte = new VueTextuelle();
        Controleur.getInstance().addObserver(graph);
        Controleur.getInstance().setGraph(graph);
        Controleur.getInstance().setTexte(texte);
        graph.setCompagnie(texte);
        texte.setCompagnie(graph);
		BorderPane pane = new BorderPane();	
		
		//vbox pour les buttons
        VBox vbox = new VBox(8); // spacing = 8
        vbox.setMinWidth(300);
        Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		separator.setMinHeight(1200);
		separator.setMaxWidth(20);
		separator.setLayoutX(0);
		buttonChargePlan = new Button("Charger un plan");
		buttonChargePlan.setMinWidth(300);
		buttonChargeDemandeLivraison = new Button("Charger une demande");
		buttonChargeDemandeLivraison.setMinWidth(300);
		buttonCalculer = new Button("Calculer");
		buttonCalculer.setMinWidth(300);
		buttonEfface = new Button("Efface");
		buttonEfface.setMinWidth(300);
        vbox.getChildren().addAll(buttonChargePlan,buttonChargeDemandeLivraison,buttonCalculer,buttonEfface,separator);

		//Ajout de la barre de menu
        Controleur.getInstance().setEtat(Controleur.getInstance().getEtatInit());
        AjouterBarreNavigateur(pane,primaryStage,Controleur.getInstance());
		VerifierEtat(Controleur.getInstance());

        
        //Placement de differents vues
        pane.setLeft(texte);
        pane.setCenter(graph);
        pane.setRight(vbox);
        BorderPane.setAlignment(graph, Pos.TOP_CENTER);
        BorderPane.setMargin(graph, new Insets(30,0,0,0));
        BorderPane.setMargin(vbox, new Insets(30,0,0,0));
        
        //Creation de la scene
        Scene scene = new Scene(pane, 1450, 900);
        scene.setFill(Color.BLACK);

        primaryStage.setScene(scene);
        primaryStage.setTitle("PLD Agile");
        primaryStage.show();
        

	}
	
	/**
	 * Méthode pour ajouter la barre de navigation.
	 * @param pane
	 * @param primaryStage
   * @param controleur
	 */
	public void AjouterBarreNavigateur(BorderPane pane, Stage primaryStage,Controleur controleur) {
		
		menuBar = new MenuBar();
		
		//Ajout de l'onglet Fichiers
		menuFile = new Menu("Fichiers");
        itemChargerDemandeLivraison = new MenuItem("Charger DemandeLivraison");
        itemChargerDemandeLivraison.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	
	        	 FileChooser fileChooser = new FileChooser();
	        	 fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
               FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Fichiers", "*.xml");
               fileChooser.getExtensionFilters().add(extFilter);
               File file = fileChooser.showOpenDialog(primaryStage);
               if(file != null) {
            	   try {
					Controleur.getInstance().chargerFichierDemandeLivraison(file);
					VerifierEtat(controleur);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
               }
	         }
	      });
        buttonChargeDemandeLivraison.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	
	        	 FileChooser fileChooser = new FileChooser();
	        	 fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Fichiers", "*.xml");
              fileChooser.getExtensionFilters().add(extFilter);
              File file = fileChooser.showOpenDialog(primaryStage);
              if(file != null) {
           	   try {
				Controleur.getInstance().chargerFichierDemandeLivraison(file);
				VerifierEtat(controleur);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              }
	         }
	      });
        itemChargerPlan = new MenuItem("Charger Plan");
        itemChargerPlan.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	
	        	FileChooser fileChooser = new FileChooser();
	        	fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
               FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Fichiers", "*.xml");
               fileChooser.getExtensionFilters().add(extFilter);
               File file = fileChooser.showOpenDialog(primaryStage);
               if(file != null) {
            	   try {
					Controleur.getInstance().chargerFichierPlan(file);
					VerifierEtat(controleur);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	   
         	   
               }
               
	         }
	      }); 
        buttonChargePlan.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	
	        	FileChooser fileChooser = new FileChooser();
	        	fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Fichiers", "*.xml");
              fileChooser.getExtensionFilters().add(extFilter);
              File file = fileChooser.showOpenDialog(primaryStage);
              if(file != null) {
           	   try {
				Controleur.getInstance().chargerFichierPlan(file);
				VerifierEtat(controleur);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           	   
              }
              
	         }
	      }); 
 
        menuFile.getItems().addAll(itemChargerPlan,itemChargerDemandeLivraison);
 
      //Ajout de l'onglet Opération
        menuTournee = new Menu("Tournée");
        itemCalculerTournees = new MenuItem("Calculer les tournees");

        itemCalculerTournees.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
               try {
				Controleur.getInstance().CalculerLesTournees();
				VerifierEtat(controleur);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         }
	      }); 
        buttonCalculer.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
              try {
				Controleur.getInstance().CalculerLesTournees();
				VerifierEtat(controleur);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         }
	      }); 

        itemEffacer = new MenuItem("Effacer");

        itemEffacer.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
               graph.clearVue();
               controleur.setEtat(controleur.getEtatInit());
               VerifierEtat(controleur);
	         }
	      }); 
        buttonEfface.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
              graph.clearVue();
              controleur.setEtat(controleur.getEtatInit());
              VerifierEtat(controleur);
	         }
	      }); 
        menuTournee.getItems().addAll(itemCalculerTournees,itemEffacer);
        
      //Ajout de l'onglet View(Composant prevu pour apres)
        menuLivraison = new Menu("Livraison");
        itemAjouterLivraison = new MenuItem("Ajouter une tournee");
        itemSupprimerLivraison = new MenuItem("Supprimer une tournee");
        
        
        menuBar.getMenus().addAll(menuFile, menuTournee, menuLivraison);
        pane.setTop(menuBar);
		
	}
	
	/**
	 * La méthode main.
	 * @param args
	 */
	public static void main(String[] args) {
        launch(args);

	public void VerifierEtat(Controleur c) {
	    ETAT e = ETAT.valueOf(c.getEtatCourant().getClass().getSimpleName());

		switch(e) {
		case EtatInit:
			itemChargerPlan.setDisable(false);
			buttonChargePlan.setDisable(false);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(true);
			itemEffacer.setDisable(true);
			buttonEfface.setDisable(true);
			break;
		case EtatPlanCharge:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(false);
			buttonChargeDemandeLivraison.setDisable(false);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(true);
			itemEffacer.setDisable(false);
			buttonEfface.setDisable(false);
			break;
		case EtatDemandeLivraison:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(false);
			buttonCalculer.setDisable(false);
			itemEffacer.setDisable(false);
			buttonEfface.setDisable(false);
			break;
        
		default:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(true);
			itemEffacer.setDisable(true);
			buttonEfface.setDisable(true);
			break;
		}
	}
	public static void main(String[] args) {
        launch(args);
		
    }

}

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
	private MenuItem itemEffacerTournees;
	private MenuItem itemAjouterLivraison;
	private MenuItem itemSupprimerLivraison;


	

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
        AjouterBarreNavigateur(pane,primaryStage);
        
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
	
	public void AjouterBarreNavigateur(BorderPane pane, Stage primaryStage) {
		
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
            	   Controleur.getInstance().chargerFichierDemandeLivraison(file);
            	   itemCalculerTournees.setDisable(false);
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
           	   Controleur.getInstance().chargerFichierDemandeLivraison(file);
           	buttonCalculer.setDisable(false);
              }
	         }
	      });
        itemChargerDemandeLivraison.setDisable(true);
        buttonChargeDemandeLivraison.setDisable(true);
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
        		   itemEffacerTournees.setDisable(false);
            	   Controleur.getInstance().chargerFichierPlan(file);
            	   itemChargerDemandeLivraison.setDisable(false);
            	   if(!itemCalculerTournees.isDisable()) {
            		   itemCalculerTournees.setDisable(true);
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
               buttonEfface.setDisable(false);
           	   Controleur.getInstance().chargerFichierPlan(file);
           	   buttonChargeDemandeLivraison.setDisable(false);
           	   if(!buttonCalculer.isDisable()) {
           		buttonCalculer.setDisable(true);
           	   }
           	   
              }
              
	         }
	      }); 
 
        menuFile.getItems().addAll(itemChargerPlan,itemChargerDemandeLivraison);
 
      //Ajout de l'onglet Opération
        menuTournee = new Menu("Tournée");
        itemCalculerTournees = new MenuItem("Calculer les tournees");
        itemCalculerTournees.setDisable(true);
        buttonCalculer.setDisable(true);

        itemCalculerTournees.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
               Controleur.getInstance().CalculerLesTournees();
               itemEffacerTournees.setDisable(false);
	         }
	      }); 
        buttonCalculer.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
              Controleur.getInstance().CalculerLesTournees();
              buttonCalculer.setDisable(false);
	         }
	      }); 

        itemEffacerTournees = new MenuItem("Effacer les tournees");
        itemEffacerTournees.setDisable(true);
        buttonEfface.setDisable(true);

        itemEffacerTournees.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
               graph.clearVue();
               itemEffacerTournees.setDisable(true);
	         }
	      }); 
        buttonEfface.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
              graph.clearVue();
              buttonEfface.setDisable(true);
	         }
	      }); 
        menuTournee.getItems().addAll(itemCalculerTournees,itemEffacerTournees);
        
      //Ajout de l'onglet View(Composant prevu pour apres)
        menuLivraison = new Menu("Livraison");
        itemAjouterLivraison = new MenuItem("Ajouter une tournee");
        itemSupprimerLivraison = new MenuItem("Supprimer une tournee");
        
        
        menuBar.getMenus().addAll(menuFile, menuTournee, menuLivraison);
        pane.setTop(menuBar);
		
	}
	
	public static void main(String[] args) {
        launch(args);
		
    }

}

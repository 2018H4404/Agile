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
import javafx.scene.Group;
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
	private Button buttonSupprimerPoint;


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
        Controleur.getInstance().addObserver(graph,texte);
        Controleur.getInstance().setGraph(graph);
        Controleur.getInstance().setTexte(texte);
        graph.setCompagnie(texte);
        texte.setCompagnie(graph);
		BorderPane pane = new BorderPane();	
		
		//vbox pour les buttons
        VBox vbox = new VBox(8); // spacing = 8
        vbox.setMinWidth(300);
		buttonChargePlan = new Button("Charger un plan");
		buttonChargePlan.setMinWidth(300);
		buttonChargeDemandeLivraison = new Button("Charger une demande");
		buttonChargeDemandeLivraison.setMinWidth(300);
		buttonCalculer = new Button("Calculer");
		buttonCalculer.setMinWidth(300);
		buttonEfface = new Button("Efface");
		buttonEfface.setMinWidth(300);
		
		
        vbox.getChildren().addAll(buttonChargePlan,buttonChargeDemandeLivraison,buttonCalculer,buttonEfface);

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
            	Stage entreeLivreur = new Stage();
            	int maximum = Controleur.getInstance().getNbLivreurMaximum();
            	TextField nbLivreur = new TextField() {
            	      @Override
            	      public void replaceText(int start, int end, String text) {
            	        if (!text.matches("[a-z]")) {
            	          super.replaceText(start, end, text);
            	        }
            	      }

            	      @Override
            	      public void replaceSelection(String text) {
            	        if (!text.matches("[a-z]")) {
            	          super.replaceSelection(text);
            	        }
            	      }
            	    };
            	nbLivreur.setMaxWidth(200);
   	        	Label label = new Label("Nombre de livreurs"  + "(Maximum :" + maximum + ")" + " :");
   	        	Button validerButton = new Button("Calculer");
   	        	validerButton.setOnAction(new EventHandler<ActionEvent>() {
	   	        	 @Override
	   		         public void handle(ActionEvent event) {
	   	        		 String contenu = nbLivreur.getText();
	   	        		 if(contenu.equals("")) {
	   	        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	   	 		       		alert.setHeaderText("Attention");
	   	 		       		alert.setContentText("Rentrer un nombre avant de lancer, s'il vous plait.");
	   	 		       		alert.show();
	   	        		 }else {
	   	        			 int nbLivreur = Integer.parseInt(contenu);
	   	        			 if(nbLivreur > maximum) {
	   	        				Alert alert = new Alert(Alert.AlertType.INFORMATION);
		   	 		       		alert.setHeaderText("Attention");
		   	 		       		alert.setContentText("Nombre trop grand!");
		   	 		       		alert.show();
	   	        			 }else {
	   	        				 try {
	   	        					entreeLivreur.close();
	   	        					Controleur.getInstance().calculerLesTournees(nbLivreur);
	   	        					VerifierEtat(controleur);
	   	        				 }catch(Exception e) {
	   	        					e.printStackTrace();
	   	        				 }
	   	        			 }
	   	        		 }
	   	        	 }
   	        	});
   	            BorderPane fentreNbLivreur = new BorderPane();
   	            fentreNbLivreur.setTop(label);
   	            fentreNbLivreur.setCenter(nbLivreur);
   	         	fentreNbLivreur.setBottom(validerButton);
   	         	BorderPane.setAlignment(label, Pos.CENTER);
   	         	BorderPane.setAlignment(nbLivreur, Pos.CENTER);
   	         	BorderPane.setAlignment(validerButton, Pos.CENTER);
   	            Scene secondScene = new Scene(fentreNbLivreur, 300, 70);

   	            // New window (Stage)
   	            entreeLivreur.setTitle("");
   	            entreeLivreur.setScene(secondScene);
   	 
   	            // Specifies the owner Window (parent) for new window
   	            entreeLivreur.initOwner(primaryStage);
   	 
   	            // Set position of second window, related to primary window.
   	            entreeLivreur.setX(primaryStage.getX() + 650);
   	            entreeLivreur.setY(primaryStage.getY() + 400);
   	 
   	            entreeLivreur.show();
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
	             	int maximum = Controleur.getInstance().getNbLivreurMaximum();
	             	Stage entreeLivreur = new Stage();
	             	TextField nbLivreur = new TextField() {
	             	      @Override
	             	      public void replaceText(int start, int end, String text) {
	             	        if (!text.matches("[a-z]")) {
	             	          super.replaceText(start, end, text);
	             	        }
	             	      }

	             	      @Override
	             	      public void replaceSelection(String text) {
	             	        if (!text.matches("[a-z]")) {
	             	          super.replaceSelection(text);
	             	        }
	             	      }
	             	    };
	             	nbLivreur.setMaxWidth(200);
	    	        	Label label = new Label("Nombre de livreurs"  + "(Maximum :" + maximum + ")" + " :");
	    	        	Button validerButton = new Button("Calculer");
	    	        	validerButton.setOnAction(new EventHandler<ActionEvent>() {
	 	   	        	 @Override
	 	   		         public void handle(ActionEvent event) {
	 	   	        		 String contenu = nbLivreur.getText();
	 	   	        		 if(contenu.equals("")) {
	 	   	        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	 	   	 		       		alert.setHeaderText("Attention");
	 	   	 		       		alert.setContentText("Rentrer un nombre avant de lancer, s'il vous plait.");
	 	   	 		       		alert.show();
	 	   	        		 }else {
	 	   	        			 int nbLivreur = Integer.parseInt(contenu);
	 	   	        			 if(nbLivreur > maximum) {
	 	   	        				Alert alert = new Alert(Alert.AlertType.INFORMATION);
	 		   	 		       		alert.setHeaderText("Attention");
	 		   	 		       		alert.setContentText("Nombre trop grand!");
	 		   	 		       		alert.show();
	 	   	        			 }else {
	 	   	        				 try {
	 	   	        					entreeLivreur.close();
	 	   	        					Controleur.getInstance().calculerLesTournees(nbLivreur);
	 	   	        					VerifierEtat(controleur);
	 	   	        				 }catch(Exception e) {
	 	   	        					e.printStackTrace();
	 	   	        				 }
	 	   	        			 }
	 	   	        		 }
	 	   	        	 }
	    	        	});
	    	            BorderPane fentreNbLivreur = new BorderPane();
	    	            fentreNbLivreur.setTop(label);
	    	            fentreNbLivreur.setCenter(nbLivreur);
	    	         	fentreNbLivreur.setBottom(validerButton);
	    	         	BorderPane.setAlignment(label, Pos.CENTER);
	    	         	BorderPane.setAlignment(nbLivreur, Pos.CENTER);
	    	         	BorderPane.setAlignment(validerButton, Pos.CENTER);
	    	            Scene secondScene = new Scene(fentreNbLivreur, 300, 70);
	    	   
	    	            // New window (Stage)
	    	            entreeLivreur.setTitle("");
	    	            entreeLivreur.setScene(secondScene);
	    	 
	    	            // Specifies the owner Window (parent) for new window
	    	            entreeLivreur.initOwner(primaryStage);
	    	 
	    	            // Set position of second window, related to primary window.
	    	            entreeLivreur.setX(primaryStage.getX() + 650);
	    	            entreeLivreur.setY(primaryStage.getY() + 400);
	    	 
	    	            entreeLivreur.show();
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
               texte.clearVue();
               controleur.setEtat(controleur.getEtatInit());
               VerifierEtat(controleur);
	         }
	      }); 
        buttonEfface.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
              graph.clearVue();
              texte.clearVue();
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
	
	/**
	 * La méthode main.
	 * @param args
	 */

	public static void main(String[] args) {
        launch(args);
		
    }

}

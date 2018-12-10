package vue;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import modele.TourneeManager;
import modele.metier.DemandeLivraison;
import modele.metier.Intersection;
import modele.metier.IntersectionNormal;
import modele.metier.Plan;
import controleur.CommandeAjouterLivraison;
import controleur.Controleur;
import controleur.EtatAjouterChoixNouvellePointLivraison;

/** 
 * La classe de la demo de l'application.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

enum ETAT {
    EtatInit,EtatPlanCharge,EtatDemandeLivraison,EtatPosteCalcul,
    EtatAjouterChoixPointLivraison,EtatAjouterChoixNouvellePointLivraison,
    EtatSupprimerChoixPointLivraison,EtatChoixPointLivraisonADeplacer,EtatChoixPointLivraisonApresDeplacer;

}

@SuppressWarnings("restriction")
public class ApplicationDemo extends Application{
	private VueGraphique graph;
	private VueTextuelle texte;
	private Button buttonChargePlan;
	private Button buttonChargeDemandeLivraison;
	private Button buttonCalculer;
	private Button buttonEffacer;
	private Button buttonEffacerDemande;
	private Button buttonSupprimerLivraison;
	private Button buttonAjouterLivraison;
	private Button buttonDeplacerLivraison;
	private Button buttonRedo;
	private Button buttonUndo;

	private Label labelNombreLivreurs;
	private TextField textFieldnombreLivreur;
	private Label labelError;
	
	private Label labelDuree;
	private TextField textFieldDuree;
	private Label labelDureeError;

	private Label labelInfo;
	
	private MenuBar menuBar;
	private Menu menuFile;
	private Menu menuTournee;
	private Menu menuLivraison;
	private MenuItem itemChargerPlan;
	private MenuItem itemChargerDemandeLivraison;
	private MenuItem itemCalculerTournees;
	private MenuItem itemEffacer;
	private MenuItem itemEffacerDemande;
	private MenuItem itemAjouterLivraison;
	private MenuItem itemSupprimerLivraison;
	private MenuItem itemDeplacerLivraison;

	/**
	 * Methode permettant de commencer la demo de l'application.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        graph = new VueGraphique(1400,900,this);
        texte = new VueTextuelle(this);
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
		buttonChargePlan.setMaxWidth(300);
		buttonChargeDemandeLivraison = new Button("Charger une demande");
		buttonChargeDemandeLivraison.setMinWidth(300);
		buttonChargeDemandeLivraison.setMaxWidth(300);
		buttonCalculer = new Button("Calculer");
		buttonCalculer.setMinWidth(300);
		buttonCalculer.setMaxWidth(300);
		
		labelDuree = new Label("Duree(en seconde) :");
		labelDuree.setMinWidth(300);
		labelDuree.setMaxWidth(300);
		labelDuree.setWrapText(true);
		
		textFieldDuree = new TextField() {
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
   	    
   	    textFieldDuree.setMinWidth(300);
   	    textFieldDuree.setMaxWidth(300);
		
   	    labelDureeError=new Label();   
   	    labelDureeError.setMinWidth(300);
   	    labelDureeError.setMaxWidth(300);
   	    labelDureeError.setWrapText(true);
   	    labelDureeError.setTextFill(Color.web("#FF0000"));
		
		labelNombreLivreurs = new Label("Nombre de livreur:");
		labelNombreLivreurs.setMinWidth(300);
		labelNombreLivreurs.setMaxWidth(300);
		labelNombreLivreurs.setWrapText(true);
		
		textFieldnombreLivreur = new TextField() {
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
		textFieldnombreLivreur.setMinWidth(300);
		textFieldnombreLivreur.setMaxWidth(300);
		
		labelError=new Label();   
		labelError.setMinWidth(300);
		labelError.setMaxWidth(300);
		labelError.setWrapText(true);
		labelError.setTextFill(Color.web("#FF0000"));
		
		buttonEffacer = new Button("Effacer tout");
		buttonEffacer.setMinWidth(300);
		buttonEffacer.setMaxWidth(300);
		
		buttonEffacerDemande = new Button("Effacer demande de livraison");
		buttonEffacerDemande.setMinWidth(300);
		buttonEffacerDemande.setMaxWidth(300);
		
		buttonSupprimerLivraison = new Button("Supprimer un point de livraison");
		buttonSupprimerLivraison.setMinWidth(300);
		buttonSupprimerLivraison.setMaxWidth(300);
		
		buttonAjouterLivraison = new Button("Ajouter un point de livraison");
		buttonAjouterLivraison.setMinWidth(300);
		buttonAjouterLivraison.setMaxWidth(300);
		
		buttonDeplacerLivraison = new Button("Déplacer un point de livraison");
		buttonDeplacerLivraison.setMinWidth(300);
		buttonDeplacerLivraison.setMaxWidth(300);
		
		buttonRedo = new Button("Redo");
		buttonRedo.setMinWidth(300);
		buttonRedo.setMaxWidth(300);
		
		buttonUndo = new Button("Undo");
		buttonUndo.setMinWidth(300);
		buttonUndo.setMaxWidth(300);
		
		labelInfo = new Label();
		labelInfo.setMinWidth(300);
		labelInfo.setMaxWidth(300);
		labelInfo.setWrapText(true);
		
        vbox.getChildren().addAll(buttonChargePlan,buttonChargeDemandeLivraison, labelNombreLivreurs, 
        		textFieldnombreLivreur, labelError, buttonCalculer,buttonAjouterLivraison,labelDuree,textFieldDuree,
        		labelDureeError,buttonSupprimerLivraison,buttonDeplacerLivraison,
        		buttonEffacer, buttonEffacerDemande, labelInfo,buttonRedo,buttonUndo);

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
	 * Methode pour ajouter la duree saisie par utilisateur.
	 */
	public int getDuree(){
		String text = textFieldDuree.getText();
		if(text.equals("")) {
			labelDureeError.setText("Entree une valeur, s'il vous plaît.");
			return Integer.MAX_VALUE;
		}else {
			labelDureeError.setText("");
			return Integer.parseInt(text);
		}
	}
	
	/**
	 * Methode pour modifier l'information dans le labelInfo.
	 * @param texte : info à saisie
	 */
	public void setInfo(String texte){
		labelInfo.setTextFill(Color.BLACK);
		labelInfo.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		labelInfo.setText(texte);
	}
	
	
	/**
	 * Methode pour ajouter la barre de navigation.
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
					Controleur.getInstance().ajouterListenerOnClick();
					labelInfo.setText("");
					textFieldnombreLivreur.setText("1");
					VerifierEtat(controleur);
				} catch (Exception e) {
					labelInfo.setTextFill(Color.web("#FF0000"));
           	   		labelInfo.setText("Le fichier XML de livraison fourni est mal forme.");
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
						Controleur.getInstance().ajouterListenerOnClick();
						labelInfo.setText("");
						textFieldnombreLivreur.setText("1");
						VerifierEtat(controleur);
	           	   	} catch (Exception e) {
	           	   		labelInfo.setTextFill(Color.web("#FF0000"));
	           	   		labelInfo.setText("Le fichier XML de livraison fourni est mal forme.");
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
	        			labelInfo.setText("");
	        			VerifierEtat(controleur);
	        		} catch (Exception e) {
						System.out.println("d");
						labelInfo.setTextFill(Color.web("#FF0000"));
	           	   		labelInfo.setText("Le fichier XML de plan fourni est mal forme.");
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
				labelInfo.setText("");
				VerifierEtat(controleur);

			} catch (Exception e) {
				labelInfo.setTextFill(Color.web("#FF0000"));
       	   		labelInfo.setText("Le fichier XML de plan fourni est mal forme.");
				e.printStackTrace();
			}
           	   
              }
	         }
	      }); 
        
        menuFile.getItems().addAll(itemChargerPlan,itemChargerDemandeLivraison);
 
      //Ajout de l'onglet Operation
        menuTournee = new Menu("Tournee");
        itemCalculerTournees = new MenuItem("Calculer les tournees");

        itemCalculerTournees.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
               try {
            	   int maximum = Controleur.getInstance().getNbLivreurMaximum();
 	        		 String contenu = textFieldnombreLivreur.getText();
 	        		 if(contenu.equals("")) {
	   	        			labelError.setText("Il n'y a pas d'entree, "
	   	        					+ "veuillez specifier une valeur.");
 	        		 } else {
	        			 int nbLivreur = Integer.parseInt(contenu);
	        			 if(nbLivreur > maximum || nbLivreur < 1) {
	        				 labelError.setText("Le nombre de livreurs donnee est plus grand que le nombre "
	        				 		+ "maximum de livreurs (" + maximum + " livreurs) ou inferieur à 1,  veuillez specifier une valeur valide.");
	        			 } else {
	        				 try {
	        					Controleur.getInstance().calculerLesTournees(nbLivreur);
	   	        				//Controleur.getInstance().effaceListenerOnClick();
	        					labelError.setText("");
	        					VerifierEtat(controleur);
	        				 }catch(Exception e) {
	        					e.printStackTrace();
	        				 }
	        			 }
	        		 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         }
	      }); 
        
        /**
         * Boutton calculer qui permet le calcul des tournees.
         */
        buttonCalculer.setOnAction(new EventHandler<ActionEvent>() {
	         @Override
	         public void handle(ActionEvent event) {
	        	 try {

	        		 int maximum = Controleur.getInstance().getNbLivreurMaximum();
   	        		 String contenu = textFieldnombreLivreur.getText();
   	        		 if(contenu.equals("")) {
	   	        			labelError.setText("Il n'y a pas d'entree, "
	   	        					+ "veuillez specifier une valeur.");
   	        		 } else {
	        			 int nbLivreur = Integer.parseInt(contenu);
	        			 if(nbLivreur > maximum || nbLivreur < 1) {
	        				 labelError.setText("Le nombre de livreurs donnee est plus grand que le nombre "
	        				 		+ "maximum de livreurs (" + maximum + " livreurs) ou inferieur à 1,  veuillez specifier une valeur valide.");
	        			 } else {
	        				 try {
	        					Controleur.getInstance().calculerLesTournees(nbLivreur);
	   	        				//Controleur.getInstance().effaceListenerOnClick();
	        					labelError.setText("");
	        					VerifierEtat(controleur);
	        				 }catch(Exception e) {
	        					e.printStackTrace();
	        				 }
	        			 }
	        		 }
	 			} catch (Exception e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	         }
 	      }); 
        
        itemEffacer = new MenuItem("Effacer tout");
        itemEffacerDemande = new MenuItem("Effacer la demande de livraison");

        itemEffacer.setOnAction(new EventHandler<ActionEvent>() {
	         @Override
	         public void handle(ActionEvent event) {
               graph.clearVue();
               texte.clearVue();
               labelNombreLivreurs.setText("Nombre de livreurs :");
               textFieldnombreLivreur.setText("");
               Controleur.getInstance().setEtat(Controleur.getInstance().getEtatInit());
               VerifierEtat(controleur);
	         }
	      });
        
        itemEffacerDemande.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
            graph.clearEntrepotLivraison();
            graph.clearTournees();
            labelNombreLivreurs.setText("Nombre de livreurs :");
            textFieldnombreLivreur.setText("");
            Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPlanCharge());
            VerifierEtat(controleur); 
	         }
	      }); 
        
        buttonEffacer.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
              graph.clearVue();
              texte.clearVue();
              labelNombreLivreurs.setText("Nombre de livreurs :");
              textFieldnombreLivreur.setText("");
              Controleur.getInstance().setEtat(Controleur.getInstance().getEtatInit());
              VerifierEtat(controleur); 
	         }
	      }); 
        
        buttonEffacerDemande.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
             graph.clearEntrepotLivraison();
             graph.clearTournees();
             labelNombreLivreurs.setText("Nombre de livreurs :");
             textFieldnombreLivreur.setText("");
             Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPlanCharge());
             VerifierEtat(controleur); 
	         }
	      }); 
        
        menuTournee.getItems().addAll(itemCalculerTournees,itemEffacer,itemEffacerDemande);
        
      //Ajout de l'onglet View(Composant prevu pour apres)
        menuLivraison = new Menu("Livraison");
        itemAjouterLivraison = new MenuItem("Ajouter un point de livraison");
        itemSupprimerLivraison = new MenuItem("Supprimer un point de livraison");
        itemDeplacerLivraison = new MenuItem("Déplacer un point de livraison");
        
        itemAjouterLivraison.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	            try {
					Controleur.getInstance().ajouterPointLivraison();
					VerifierEtat(controleur);
					graph.arreterSynchronisationLivraison();
					texte.arreterSynchronisationLivraison();
					textFieldDuree.setText("0");
					labelInfo.setTextFill(Color.BLACK);
					labelInfo.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
					labelInfo.setText("Choisissez le point de livraison apres lequel vous voulez ajouter un point de Livraison.");
				} catch (Exception e) {
					e.printStackTrace();
				}
	             
	         }
	      }); 
        
        itemSupprimerLivraison.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	 try {
	        	    Controleur.getInstance().setEtat(Controleur.getInstance().getEtatSupprimerChoixPointLivraison());
					VerifierEtat(controleur);
					graph.arreterSynchronisationLivraison();
					texte.arreterSynchronisationLivraison();
					labelInfo.setTextFill(Color.BLACK);
					labelInfo.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
					labelInfo.setText("Choisissez le point de livraison que vous voulez supprimer.");
				} catch (Exception e) {
					e.printStackTrace();
				}
	         }
	      }); 
        
        
        buttonAjouterLivraison.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	            try {
					Controleur.getInstance().ajouterPointLivraison();
					VerifierEtat(controleur);
					graph.arreterSynchronisationLivraison();
					texte.arreterSynchronisationLivraison();
					textFieldDuree.setText("0");
					labelInfo.setTextFill(Color.BLACK);
					// labelInfo.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
					labelInfo.setText("Choisissez le point de livraison apres lequel vous voulez ajouter un point de Livraison.");
				} catch (Exception e) {
					e.printStackTrace();
				}
	             
	         }
	      }); 
        
        buttonSupprimerLivraison.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	 try {
	        	    Controleur.getInstance().supprimerPointLivraison();
					VerifierEtat(controleur);
					graph.arreterSynchronisationLivraison();
					texte.arreterSynchronisationLivraison();
					labelInfo.setTextFill(Color.BLACK);
					// labelInfo.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
					labelInfo.setText("Choisissez le point de livraison que vous voulez supprimer.");
				} catch (Exception e) {
					e.printStackTrace();
				}
	         }
	      });
    
        buttonDeplacerLivraison.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	 try {
	        		 Controleur.getInstance().deplacerPointLivraison();
					VerifierEtat(controleur);
					graph.arreterSynchronisationLivraison();
					texte.arreterSynchronisationLivraison();
					labelInfo.setTextFill(Color.BLACK);
					labelInfo.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
					labelInfo.setText("Choisissez le point de livraison que vous voulez déplacer.");
				} catch (Exception e) {
					e.printStackTrace();
				}
	         }
	      }); 
        buttonRedo.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	 try {
	        		 Controleur.getInstance().redo();
					VerifierEtat(controleur);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	         }
	      }); 
        buttonUndo.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	 try {
	        		 Controleur.getInstance().undo();
					VerifierEtat(controleur);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	         }
	      }); 

        menuLivraison.getItems().addAll(itemAjouterLivraison,itemSupprimerLivraison);
        
        menuBar.getMenus().addAll(menuFile, menuTournee, menuLivraison);
        pane.setTop(menuBar);
		
	}
	
	public void setLabelNbLivreur(int nbLivreur) {
		labelNombreLivreurs.setText("Nombre de livreurs (maximum :" + nbLivreur + ") :");
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
			textFieldnombreLivreur.setDisable(true);
			itemEffacer.setDisable(true);
			buttonEffacer.setDisable(true);
			itemEffacerDemande.setDisable(true);
			buttonEffacerDemande.setDisable(true);
			buttonAjouterLivraison.setDisable(true);
			textFieldDuree.setDisable(true);
			buttonSupprimerLivraison.setDisable(true);
			buttonDeplacerLivraison.setDisable(true);
			itemSupprimerLivraison.setDisable(true);
			itemAjouterLivraison.setDisable(true);
			break;
		case EtatPlanCharge:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(false);
			buttonChargeDemandeLivraison.setDisable(false);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(true);
			textFieldnombreLivreur.setDisable(true);
			itemEffacer.setDisable(false);
			buttonEffacer.setDisable(false);
			itemEffacerDemande.setDisable(true);
			buttonEffacerDemande.setDisable(true);
			buttonAjouterLivraison.setDisable(true);
			textFieldDuree.setDisable(true);
			buttonSupprimerLivraison.setDisable(true);
			buttonDeplacerLivraison.setDisable(true);
			itemSupprimerLivraison.setDisable(true);
			itemAjouterLivraison.setDisable(true);
			break;
		case EtatDemandeLivraison:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(false);
			buttonCalculer.setDisable(false);
			textFieldnombreLivreur.setDisable(false);
			itemEffacer.setDisable(false);
			buttonEffacer.setDisable(false);
			itemEffacerDemande.setDisable(false);
			buttonEffacerDemande.setDisable(false);
			buttonAjouterLivraison.setDisable(true);
			textFieldDuree.setDisable(true);
			buttonSupprimerLivraison.setDisable(true);
			buttonDeplacerLivraison.setDisable(true);
			itemSupprimerLivraison.setDisable(true);
			itemAjouterLivraison.setDisable(true);
			break;
			
		case EtatPosteCalcul:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(false);
			itemEffacer.setDisable(false);
			buttonEffacer.setDisable(false);
			itemEffacerDemande.setDisable(false);
			buttonEffacerDemande.setDisable(false);
			buttonAjouterLivraison.setDisable(false);
			textFieldDuree.setDisable(false);
			buttonSupprimerLivraison.setDisable(false);
			buttonDeplacerLivraison.setDisable(false);
			itemSupprimerLivraison.setDisable(false);
			itemAjouterLivraison.setDisable(false);
			break;
			
		case EtatAjouterChoixPointLivraison:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(true);
			itemEffacer.setDisable(true);
			buttonEffacer.setDisable(true);
			itemEffacerDemande.setDisable(true);
			buttonEffacerDemande.setDisable(true);
			buttonAjouterLivraison.setDisable(true);
			textFieldDuree.setDisable(true);
			buttonSupprimerLivraison.setDisable(true);
			buttonDeplacerLivraison.setDisable(true);
			itemSupprimerLivraison.setDisable(true);
			itemAjouterLivraison.setDisable(true);
			break;
			
		case EtatAjouterChoixNouvellePointLivraison:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(true);
			itemEffacer.setDisable(true);
			buttonEffacer.setDisable(true);
			itemEffacerDemande.setDisable(true);
			buttonEffacerDemande.setDisable(true);
			buttonAjouterLivraison.setDisable(true);
			textFieldDuree.setDisable(true);
			buttonSupprimerLivraison.setDisable(true);
			buttonDeplacerLivraison.setDisable(true);
			itemSupprimerLivraison.setDisable(true);
			itemAjouterLivraison.setDisable(true);
			break;
			
		case EtatSupprimerChoixPointLivraison:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(true);
			itemEffacer.setDisable(true);
			buttonEffacer.setDisable(true);
			itemEffacerDemande.setDisable(true);
			buttonEffacerDemande.setDisable(true);
			buttonAjouterLivraison.setDisable(true);
			textFieldDuree.setDisable(true);
			buttonSupprimerLivraison.setDisable(true);
			buttonDeplacerLivraison.setDisable(true);
			itemSupprimerLivraison.setDisable(true);
			itemAjouterLivraison.setDisable(true);
			break;
		
		case EtatChoixPointLivraisonADeplacer:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(true);
			itemEffacer.setDisable(true);
			buttonEffacer.setDisable(true);
			itemEffacerDemande.setDisable(true);
			buttonEffacerDemande.setDisable(true);
			buttonAjouterLivraison.setDisable(true);
			buttonSupprimerLivraison.setDisable(true);
			buttonDeplacerLivraison.setDisable(true);
			itemSupprimerLivraison.setDisable(true);
			itemAjouterLivraison.setDisable(true);
			break;
			
		case EtatChoixPointLivraisonApresDeplacer:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(true);
			itemEffacer.setDisable(true);
			buttonEffacer.setDisable(true);
			itemEffacerDemande.setDisable(true);
			buttonEffacerDemande.setDisable(true);
			buttonAjouterLivraison.setDisable(true);
			buttonSupprimerLivraison.setDisable(true);
			buttonDeplacerLivraison.setDisable(true);
			itemSupprimerLivraison.setDisable(true);
			itemAjouterLivraison.setDisable(true);
			break;
			


		default:
			itemChargerPlan.setDisable(true);
			buttonChargePlan.setDisable(true);
			itemChargerDemandeLivraison.setDisable(true);
			buttonChargeDemandeLivraison.setDisable(true);
			itemCalculerTournees.setDisable(true);
			buttonCalculer.setDisable(true); 
			textFieldnombreLivreur.setDisable(true);
			itemEffacer.setDisable(true);
			buttonEffacer.setDisable(true);
			buttonEffacerDemande.setDisable(true);
			buttonAjouterLivraison.setDisable(true);
			buttonSupprimerLivraison.setDisable(true);
			itemSupprimerLivraison.setDisable(true);
			itemAjouterLivraison.setDisable(true);
			break;
		}
	}
	
	/**
	 * La methode main.
	 * @param args
	 */

	public static void main(String[] args) {
		launch(args);
    }
}

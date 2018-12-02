package vue;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.joda.time.DateTime;

import controleur.Controleur;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import modele.TourneeManager;
import modele.metier.Chemin;
import modele.metier.Entrepot;
import modele.metier.Intersection;
import modele.metier.Tournee;

/** 
 * La classe de la vue textuelle.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

@SuppressWarnings("restriction")
public class VueTextuelle extends Parent implements Observer{
	
	@SuppressWarnings("unused")
	private VueGraphique compagnie;
	private Label monLabel;
	private TabPane infos;
	private Tab nomRue;
	private Tab infosLivraison;
	private Tab infosTournee;
	private TitledPane filtreTournees;
	private GridPane conteneurFiltres;
	private Accordion conteneurInfoParTournee;
	private CheckBox[] lesFiltres = null;
	private TitledPane[] infoParTournee = null;
	private Button filtreButton;
	private TitledPane infosTournees;
	
	/**
	 * Constructeur de la vue textuelle.
	 */
	public VueTextuelle() {
		//Intialisation de sa compagnie par defaut
				compagnie = null;
				
				//Creation du separateur avec la vue graphique
				Separator separator = new Separator();
				separator.setOrientation(Orientation.VERTICAL);
				separator.setMinHeight(1200);
				separator.setMaxWidth(20);
				separator.setLayoutX(300);
				infos = new TabPane();
				monLabel = new Label("Bienvenue sur PLD Agile.");
				monLabel.setLayoutX(0);
				monLabel.setLayoutY(0);
				monLabel.setMaxWidth(300);
				monLabel.setMinWidth(300);
				monLabel.setWrapText(true);
				monLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
				//Creation des Tabs
				nomRue = new Tab();
				nomRue.setClosable(false);
				nomRue.setContent(monLabel);
				nomRue.setText("Nom de la rue");
				
				infosLivraison = new Tab();
				infosLivraison.setText("Livraisons");
				infosLivraison.setClosable(false);
				
				infosTournee = new Tab();
				infosTournee.setText("Tournees");
				ScrollPane scroll = new ScrollPane();
				scroll.setPrefViewportHeight(900);
				Accordion conteneur = new Accordion();
				filtreTournees = new TitledPane();
				filtreTournees.setText("Filtre");
				filtreTournees.setMaxWidth(300);
				filtreTournees.setMinWidth(300);
				conteneurFiltres = new GridPane();
				conteneurFiltres.setVgap(4);
				conteneurFiltres.setPadding(new Insets(5, 5, 5, 5));
				filtreButton = new Button("Filtrer");
				filtreTournees.setContent(conteneurFiltres);
				infosTournees = new TitledPane();
				infosTournees.setMaxWidth(300);
				infosTournees.setMinWidth(300);
				infosTournees.setText("Infos");
				conteneurInfoParTournee = new Accordion();
				infosTournees.setContent(conteneurInfoParTournee);
				conteneur.getPanes().addAll(filtreTournees,infosTournees);
				scroll.setContent(conteneur);
				infosTournee.setContent(scroll);
				
				
				infos.getTabs().addAll(nomRue,infosLivraison,infosTournee);
				
				ajouterListeners();
				this.getChildren().add(infos);
				this.getChildren().add(separator);
	}
	
	public void setCompagnie(VueGraphique vue) {
		this.compagnie = vue;
	}
	
	public void setTabNomRue(String nomRue) {
		monLabel.setText(nomRue);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		String sujet = (String)arg1;
		switch(sujet) {
			case "Plan":
				break;
			case "DemandeLivraison":
				break;
			case "Tournees":
				System.out.println("In");
				ajouterTimeTableTournees((TourneeManager)arg0);
				ajouterFiltreTournees((TourneeManager)arg0);
				break;	
			case "Alert Temps":
				ajouterTimeTableTournees((TourneeManager)arg0);
				ajouterFiltreTournees((TourneeManager)arg0);
				break;	
				
		}
	}
	
	/**
	 * Methode qui ajoute les checkBoxs du filtre dans le panneau Tournee
	 * @param manager : TourneeManager qui contient la liste des tournees
	 */
	public void ajouterFiltreTournees(TourneeManager manager) {
		conteneurFiltres.getChildren().clear();
		int nbTournees = manager.getListeTournees().size();
		lesFiltres = new CheckBox[nbTournees];
		for(int i = 0; i < nbTournees; i++) {
			CheckBox tempCheckBox = new CheckBox();
			tempCheckBox.setText("Tournee "+(i+1));
			lesFiltres[i] = tempCheckBox;
			conteneurFiltres.add(tempCheckBox, 0, i);
		}
		conteneurFiltres.add(filtreButton, 0, nbTournees);
	}
	
	/**
	 * Methode qui ajoute l'agenda planifie pour l'horaire dans le panneau infos dans le TitledPane Tournees
	 * @param manager : TourneeManager qui contient la liste des tournees
	 */
	public void ajouterTimeTableTournees(TourneeManager manager) {
		System.out.println("TimeTable");
		conteneurInfoParTournee.getPanes().clear();
		ArrayList<Tournee> tournees = manager.getListeTournees();
		int nbTournees = tournees.size();
		DateTime depart = Controleur.getInstance().getActuelHeureDepart();
		infoParTournee = new TitledPane[nbTournees];
		int index = 0;
		for(Tournee t : tournees) {
			TitledPane tempPane = new TitledPane();
			tempPane.setText("Tournee"+(index+1));
			tempPane.setMaxWidth(300);
			tempPane.setMinWidth(300);
			Label tempLabel = new Label();
			tempLabel.setMaxWidth(300);
			tempLabel.setMinWidth(300);
			tempLabel.setWrapText(true);
			tempPane.setContent(tempLabel);
			infoParTournee[index] = tempPane;
			conteneurInfoParTournee.getPanes().add(tempPane);
			ArrayList<Chemin> tempChemins = t.getListeChemins();
			DateTime tempTime = new DateTime(depart);
			String contenuLabel = "";
			for(Chemin c : tempChemins) {
				Intersection interDest = c.getIntersectionDest();
				Intersection interDepart = c.getIntersectionDepart();
				if(interDest instanceof Entrepot) {
					contenuLabel += "Depart du point de livraison(" + interDepart.getLatitude() + "," + interDepart.getLongitude() + ") : ";
					contenuLabel += tempTime.toString("MM-dd-yyyy hh:mm:ss") + "\n";
					tempTime = tempTime.plus(1000*c.getDuree());
					contenuLabel += "Arrivee a l'entrepot";
					contenuLabel += tempTime.toString("MM-dd-yyyy hh:mm:ss") + "\n";
				}else {
					if(interDepart instanceof Entrepot) {
						contenuLabel += "Depart de l'entrepot : ";
						contenuLabel += tempTime.toString("MM-dd-yyyy hh:mm:ss") + "\n";
						tempTime = tempTime.plus(1000*c.getDuree());
						//System.out.println(1000*c.getDuree());
						contenuLabel += "Arrivee au point de Livraison(" + interDest.getLatitude() + "," + interDest.getLongitude() + ") : ";
						contenuLabel += tempTime.toString("MM-dd-yyyy hh:mm:ss") + "\n";
						tempTime = tempTime.plus(interDest.getDuree()*1000);
						//System.out.println(interDest.getDuree()*1000);
					}else {
						contenuLabel += "Depart du point de livraison(" + interDepart.getLatitude() + "," + interDepart.getLongitude() + ") : ";
						contenuLabel += tempTime.toString("MM-dd-yyyy hh:mm:ss") + "\n";
						tempTime = tempTime.plus(1000*c.getDuree());
						//System.out.println(1000*c.getDuree());
						contenuLabel += "Arrivee au point de Livraison(" + interDest.getLatitude() + "," + interDest.getLongitude() + ") : ";
						contenuLabel += tempTime.toString("MM-dd-yyyy hh:mm:ss") + "\n";
						tempTime = tempTime.plus(interDest.getDuree()*1000);
						//System.out.println(interDest.getDuree()*1000);
					}
				}
			}
			tempLabel.setText(contenuLabel);
			index++;
		}
		System.out.println("Done TimeTable");
	}
	
	/**
	 * Methode qui ajoute des listeners necessaire pour les composants dans la vue textuelle
	 */
	public void ajouterListeners() {
		filtreButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                if(lesFiltres == null) {
                	System.out.println("Erreur durant l'ajout des filtres.");
                	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		        alert.setHeaderText("Attention");
    		        alert.setContentText("Erreur durant l'ajout des filtres, reessayez s'il vous plait.");
    		        alert.show();
                }else {
                	ArrayList<Integer> coches = new ArrayList<Integer>();
                	for(int i = 0; i < infoParTournee.length; i++) {
                		infoParTournee[i].setDisable(true);
                	}
                	for(int i = 0; i < lesFiltres.length; i++) {
                		if(lesFiltres[i].isSelected()) {
                			coches.add(i);
                			infoParTournee[i].setDisable(false);
                		}
                	}
                	compagnie.filtrerTournees(coches);
                }
            }
        });
	}
	
	/**
	 * Methode qui efface les contenus de la vue textuelle
	 */
	public void clearVue() {
		conteneurFiltres.getChildren().clear();
		conteneurInfoParTournee.getPanes().clear();
	}
	
	public void VerifierEtat(Controleur c) {
	    ETAT e = ETAT.valueOf(c.getEtatCourant().getClass().getSimpleName());

		switch(e) {
		case EtatInit:
			infosTournees.setDisable(true);
			filtreTournees.setDisable(true);
			break;
		case EtatPlanCharge:
			infosTournees.setDisable(true);
			filtreTournees.setDisable(true);
			break;
		case EtatDemandeLivraison:
			infosTournees.setDisable(false);
			filtreTournees.setDisable(false);
			break;
        
		default:
			infosTournees.setDisable(true);
			filtreTournees.setDisable(true);
			break;
		}
	}

}

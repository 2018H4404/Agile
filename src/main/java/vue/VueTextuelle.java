package vue;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.geometry.Orientation;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

@SuppressWarnings("restriction")
public class VueTextuelle extends Parent implements Observer{
	
	@SuppressWarnings("unused")
	private VueGraphique compagnie;
	private Label monLabel;
	private TabPane infos;
	private Tab nomRue;
	private Tab infosLivraison;
	private Tab infosTournee;
	
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
		infosTournee.setClosable(false);
		infos.getTabs().addAll(nomRue,infosLivraison,infosTournee);
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
		
	}

}

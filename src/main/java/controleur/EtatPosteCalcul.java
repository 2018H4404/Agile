package controleur;


import javafx.scene.Node;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import modele.metier.Tournee;
import modele.services.SerialiseurFeuilleDeRoute;
import vue.element.IntersectionNormalVue;
import vue.element.PointLivraisonVue;


/**
 * La classe de l'etat poste calcul.
 * @author H4404
 */

public class EtatPosteCalcul extends EtatDefaut {
	
	/**
	 * Methode pour passer a etat suivant en ajoutant un point de livraison
	 */
	@Override
	public void ajouterPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatAjouterChoixPointLivraison());
	}
	
	/**
	 * Methode pour passer a etat suivant en deplacant un point de livraison
	 */
	@Override
	public void deplacerPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatChoixPointLivraisonADeplacer());
	}
	
	/**
	 * Methode pour  passer a etat suivant en supprimant un point de livraison
	 */
	@Override
	public void supprimerPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatSupprimerChoixPointLivraison());
	}
	
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
	
	/**
	 * Methode pour calculer les tournees
	 * @param nbLivreur  nombre de livreur
	 * @param mode  le choix entre algorithm clustering et optimal
	 */
	@Override
	public void CalculerLesTournees(int nbLivreur, int mode) throws Exception{
		
		//Controleur.getInstance().getMonManager().calculerLesTournees(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur);
		Controleur.getInstance().getMonManager().calculerLesTourneesSelonMode(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur,mode);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
	}
	
	/**
	 * Methode pour annuler une commande
	 */
	public void undo() {
		Controleur.getInstance().getHistorique().undo();
	}
	
	/**
	 * Methode pour effectuer une commande
	 */
	public void redo() {
		Controleur.getInstance().getHistorique().redo();

	}

	/**
	 * Methode pour exporter la feuille de route
	 * @throws Exception l'exception lors ecriturer du fichier.
	 */
	@Override
	public Document exportFeuilleDeRoute() throws FileNotFoundException, DocumentException {
		ArrayList<Tournee> listeTournees = Controleur.getInstance().getMonManager().getListeTournees();
		Document feuilleDeRoute = SerialiseurFeuilleDeRoute.exportFeuilleDeRoute(listeTournees);
		return feuilleDeRoute;
	}
}

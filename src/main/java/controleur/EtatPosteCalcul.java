package controleur;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import modele.metier.Tournee;
import modele.services.SerialiseurFeuilleDeRoute;


/**
 * La classe de l'etat poste calcul.
 * @author H4404
 */

public class EtatPosteCalcul extends EtatDefaut {
	
	/**
	 * Methode pour passer a l'etat suivant pour choisir le point de livraison a ajouter.
	 * @see Etat
	 */
	@Override
	public void ajouterPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatAjouterChoixPointLivraison());
	}
	
	/**
	 * Methode pour passer a l'etat suivant pour choisir le point de livraison a deplacer.
	 * @see Etat
	 */
	@Override
	public void deplacerPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatChoixPointLivraisonADeplacer());
	}
	
	/**
	 * Methode pour passer a l'etat suivant pour choisir le point de livraison a supprimer.
	 * @see Etat
	 */
	@Override
	public void supprimerPointLivraison() throws Exception {
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatSupprimerChoixPointLivraison());
	}
	
	/**
	 * Methode pour obtenir le nombre de livreur maximum.
	 * @see Etat
	 */
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
	
	/**
	 * Methode pour calculer les tournees selon le nombre de livreur passe et le mode passe.
	 * @see Etat
	 */
	@Override
	public void CalculerLesTournees(int nbLivreur, int mode) throws Exception{
		Controleur.getInstance().getMonManager().calculerLesTourneesSelonMode(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur,mode);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
	}
	
	/**
	 * Methode pour annuler une commande.
	 */
	public void undo() {
		Controleur.getInstance().getHistorique().undo();
	}
	
	/**
	 * Methode pour effectuer une commande.
	 */
	public void redo() {
		Controleur.getInstance().getHistorique().redo();

	}

	/**
	 * Methode pour exporter la feuille de route.
	 * @see Etat
	 */
	@Override
	public Document exportFeuilleDeRoute() throws FileNotFoundException, DocumentException {
		ArrayList<Tournee> listeTournees = Controleur.getInstance().getMonManager().getListeTournees();
		Document feuilleDeRoute = SerialiseurFeuilleDeRoute.exportFeuilleDeRoute(listeTournees);
		return feuilleDeRoute;
	}
}

package controleur;

import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

/**
 * La classe de l'etat par defaut qui implement l'interface du Etat et la classe
 * 
 * @author H4404
 */
public class EtatDefaut implements Etat {

	/**
	 *  Methode pour charger un plan selon le fichier XML passe.
	 *  @see Etat
	 */
	@Override
	public void chargerFichierPlan(File f) throws Exception {

	}

	/**
	 * Methode pour calculer les tournees.
	 * @see Etat
	 */
	@Override
	public void CalculerLesTournees(int nbLivreur, int mode) throws Exception {

	}

	/**
	 * Methode pour lire un fichier XML qui contient une demande de livraison.
	 * @see Etat
	 */
	@Override
	public void lectureLivraisonEntrepotXML(File f) throws Exception {

	}

	/**
	 *  Methode pour obtenir le nombre de livreur maximum.
	 * @see Etat
	 */
	@Override
	public int getNbLivreurMaximum() {
		return 0;
	}

	/**
	 * Methode pour pour activer le listener qui permet a l'utilisateur 
	 * d'ajouter des points de livraison(qui a une duree 0) avant le calcul des tournees.
	 * @see Etat
	 */
	@Override
	public void ajouterListenerOnClick() {

	}

	/**
	 * Methode pour passer le controleur dans l'etat EtatAjouterChoixPointLivraison .
	 * @see Etat
	 */
	@Override
	public void ajouterPointLivraison() throws Exception {

	}

	/**
	 * Methode pour passer le controleur dans l'etat EtatAjouterChoixNouvellePointLivraison .
	 * @see Etat
	 */
	@Override
	public void choixNouveauPointLivraison() throws Exception {

	}

	/**
	 * Methode pour ajouter un point de livraison.
	 * @see Etat
	 */
	@Override
	public void effectuerAjoutPointLivraison(long idDepart, long idNouvelle, int duree) throws Exception {

	}

	/**
	 * Methode pour supprimeer un point de livraison.
	 * @see Etat
	 */
	@Override
	public void effectuerSupprimerPointLivraison(long id) throws Exception {

	}

	/**
	 * Methode pour passer le controleur dans l'etat EtatChoixPointLivraisonApresDeplacer .
	 * @see Etat
	 */
	@Override
	public void choixPointLivraisonApresDeplacer() throws Exception {

	}

	/**
	 * Methode pour effectuer le deplacement.
	 * @see Etat
	 */
	@Override
	public void effectuerDeplacement(long idADeplacer, long idApres) throws Exception {

	}

	/**
	 * Methode pour passer le controleur dans l'etat EtatSupprimerChoixPointLivraison .
	 * @see Etat
	 */
	@Override
	public void supprimerPointLivraison() throws Exception {

	}

	/**
	 * Methode pour  passer le controleur dans l'etat EtatChoixPointLivraisonADeplacer .
	 * @see Etat
	 */
	@Override
	public void deplacerPointLivraison() throws Exception {

	}

	/**
	 * Methode pour effectuer une commande.
	 * @see Etat
	 */
	@Override
	public void undo() {

	}

	/**
	 * Methode pour annuler une commande.
	 * @see Etat
	 */
	@Override
	public void redo() {

	}

	/**
	 * Methode pour exporter la feuille de route.
	 * @see Etat
	 */
	@Override
	public Document exportFeuilleDeRoute() throws FileNotFoundException, DocumentException {
		return new Document();
	}

}

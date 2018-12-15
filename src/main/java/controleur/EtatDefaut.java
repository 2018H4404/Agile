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
	 * Methode pour charger une fichier XML de plan
	 */
	@Override
	public void chargerFichierPlan(File f) throws Exception {

	}

	/**
	 * Methode pour calculer les tournees
	 */
	@Override
	public void CalculerLesTournees(int nbLivreur, int mode) throws Exception {

	}

	/**
	 * Methode pour lire une fichier XML de point de livraison
	 */
	@Override
	public void lectureLivraisonEntrepotXML(File f) throws Exception {

	}

	@Override
	public int getNbLivreurMaximum() {
		return 0;
	}

	/**
	 * Methode pour ajouter des listeners sur elements dans certain etat
	 */
	@Override
	public void ajouterListenerOnClick() {

	}

	/**
	 * Methode pour ajouter un point de livraison
	 */
	@Override
	public void ajouterPointLivraison() throws Exception {

	}

	/**
	 * Methode pour choisir le nouveau point de livraison quand on ajoute le point
	 * de livraison
	 */
	@Override
	public void choixNouveauPointLivraison() throws Exception {

	}

	/**
	 * Methode pour effectuer ajouter le point de livraison
	 */
	@Override
	public void effectuerAjoutPointLivraison(long idDepart, long idNouvelle, int duree) throws Exception {

	}

	/**
	 * Methode pour effectuer supprimeer le point de livraison
	 */
	@Override
	public void effectuerSupprimerPointLivraison(long id) throws Exception {

	}

	/**
	 * Methode pour choisir le point livraison a deplacer
	 */
	@Override
	public void choixPointLivraisonApresDeplacer() throws Exception {

	}

	/**
	 * Methode pour effectuer le deplacement
	 */
	@Override
	public void effectuerDeplacement(long idADeplacer, long idApres) throws Exception {

	}

	/**
	 * Methode pour supprimer le point de livraison
	 */
	@Override
	public void supprimerPointLivraison() throws Exception {

	}

	/**
	 * Methode pour deplacer le point de livraison
	 */
	@Override
	public void deplacerPointLivraison() throws Exception {

	}

	/**
	 * Methode pour effectuer une commande
	 */
	@Override
	public void undo() {

	}

	/**
	 * Methode pour annuler une commande
	 */
	@Override
	public void redo() {

	}

	/**
	 * Methode pour exporter la feuille de route
	 */
	@Override
	public Document exportFeuilleDeRoute() throws FileNotFoundException, DocumentException {
		return new Document();
	}

}

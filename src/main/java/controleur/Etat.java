package controleur;

import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

/**
 * La classe Etat.
 * 
 * @author H4404
 */
public interface Etat {

	/**
	 * Methode de la classe Etat pour charger un plan selon le fichier XML passe.
	 * 
	 * @param f : le fichier XML a charger.
	 * @throws Exception si le fichier XML n'est pas correctement forme.
	 */
	public void chargerFichierPlan(File f) throws Exception;

	/**
	 * Methode de la classe Etat pour calculer les tournees selon le nombre de livreur et le mode passe.
	 * 
	 * @param nbLivreur : le nombre de livreurs.
	 * @param mode :      le mode choisi par l'utilisateur pour calculer les tournees.
	 * @throws Exception si un point de livraison n'est pas livrable.
	 */
	public void CalculerLesTournees(int nbLivreur, int mode) throws Exception;

	/**
	 * Methodede la classe Etat pour charger une demande de livraison selon le fichier XML passe.
	 * 
	 * @param f : le fichier XML a charger.
	 * @throws Exception si le fichier XML n'est pas correctement forme.
	 */
	public void lectureLivraisonEntrepotXML(File f) throws Exception;

	/**
	 * Methode de la classe Etat pour obtenir le nombre de livreur maximum.
	 * 
	 * @return retourne le nombre de livreur maximum.
	 */
	public int getNbLivreurMaximum();

	/**
	 * Methode de la classe Etat pour activer le listener qui permet a l'utilisateur 
	 * d'ajouter des points de livraison(qui a une duree 0) avant le calcul des tournees.
	 */
	public void ajouterListenerOnClick();

	/**
	 * Methode de la classe Etat pour passer le controleur dans l'etat EtatAjouterChoixPointLivraison .
	 * 
	 * @throws Exception si erreur durant le passage d'etat.
	 */
	public void ajouterPointLivraison() throws Exception;

	/**
	 * Methode de la classe Etat pour passer le controleur dans l'etat EtatSupprimerChoixPointLivraison .
	 * 
	 * @throws Exception si erreur durant le passage d'etat.
	 */
	public void supprimerPointLivraison() throws Exception;

	/**
	 * Methode de la classe Etat pour passer le controleur dans l'etat EtatChoixPointLivraisonADeplacer .
	 * 
	 * @throws Exception si erreur durant le passage d'etat.
	 */
	public void deplacerPointLivraison() throws Exception;

	/**
	 * Methode de la classe Etat pour passer le controleur dans l'etat EtatAjouterChoixNouvellePointLivraison .
	 * 
	 * @throws Exception si erreur durant le passage d'etat.
	 */
	public void choixNouveauPointLivraison() throws Exception;

	/**
	 * Methode de la classe Etat pour ajouter un point de livraison 
	 * et passer le controleur dans l'etat EtatPosteCalcul .
	 * 
	 * @param idDepart :   l'id du point de livraison apres lequel nous voulons ajouter le nouveau point de livraison.
	 * @param idNouvelle : l'id du point de livraison a ajouter.
	 * @param duree :      la duree du nouveau point de livraison.
	 * @throws Exception si le point de livraison a ajouter n'est pas livrable.
	 */
	public void effectuerAjoutPointLivraison(long idDepart, long idNouvelle, int duree) throws Exception;

	/**
	 *  Methode de la classe Etat pour supprimer un point de livraison 
	 * et passer le controleur dans l'etat EtatPosteCalcul .
	 * 
	 * @param id : du point de livraison a supprimer.
	 * @throws Exception si erreur lors de la suppression du point de livraison.
	 */
	public void effectuerSupprimerPointLivraison(long id) throws Exception;

	/**
	 * Methode de la classe Etat pour passer le controleur dans l'etat EtatChoixPointLivraisonApresDeplacer .
	 * 
	 * @throws Exception si erreur durant le passage d'etat.
	 */
	public void choixPointLivraisonApresDeplacer() throws Exception;

	/**
	 * * Methode de la classe Etat pour deplacer un point de livraison 
	 * et passer le controleur dans l'etat EtatPosteCalcul .
	 * 
	 * @param idADeplacer : l'id du point de livraison a deplacer.
	 * @param idApres :     l'id du point de livraison apres lequel nous voulons mettre le point de livraison a deplacer.
	 * @throws Exception si le deplacement est au sein d'une meme tournee.
	 */
	public void effectuerDeplacement(long idADeplacer, long idApres) throws Exception;

	/**
	 * Methode pour annuler une commande.
	 */
	public void undo();

	/**
	 * Methode pour faire une commande.
	 */
	public void redo();

	/**
	 * Methode pour exporter la feuille de route.
	 * @throws FileNotFoundException : Exception si fichier introuvable.
	 * @throws DocumentException : Exception lors ecrit un document
	 * @return le document ecrit
	 */
	public Document exportFeuilleDeRoute() throws FileNotFoundException, DocumentException;
}
package controleur;

import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

/**
 * La classe Etat.
 * @author H4404
 */
public interface Etat {

	/**
	 * Methode etat pour la methode chargerFichierPlan.
	 * @param f le fichier a charger.
	 * @throws Exception l'exception lors du chargement du fichier.
	 */
	public void chargerFichierPlan(File f)throws Exception;
	
	/**
	 * Methode etat pour la methode CalculerLesTournees.
	 * @param nbLivreur le nombre de livreurs.
	 * @param mode le mode choisi par l'utilisateur pour calculer les tournees.
	 * @throws Exception l'exception lors du calcul des tournees.
	 */
	public void CalculerLesTournees(int nbLivreur,int mode)throws Exception;

	/**
	 * Methode etat pour la methode lectureLivraisonEntrepotXML.
	 * @param f le fichier a charger.
	 * @throws Exception l'exception lors de la lecture du fichier.
	 */
	public void lectureLivraisonEntrepotXML(File f)throws Exception;
	
	/**
	 * Methode etat pour la methode getNbLivreurMaximum.
	 * @return retourne le nombre des livreurs maximum.
	 */
	public int getNbLivreurMaximum();
	
	/**
	 * Methode etat pour la methode modifierPointLivraison.
	 * @throws Exception lors de la modification du point de livraison.
	 */
	public void modifierPointLivraison()throws Exception;

	/**
	 * Methode etat pour la methode ajouterListenerOnClick.
	 */
	public void ajouterListenerOnClick();
	
	/**
	 * Methode etat pour la methode ajouterPointLivraison.
	 * @throws Exception lors de l'ajout du point de livraison.
	 */
	public void ajouterPointLivraison()throws Exception;
	
	/**
	 * Methode etat pour la methode supprimerPointLivraison.
	 * @throws Exception lors de la suppression du point de livraion.
	 */
	public void supprimerPointLivraison()throws Exception;
	
	/**
	 * Methode etat pour la methode deplacerPointLivraison.
	 * @throws Exception lors deplacement du point de livraison.
	 */
	public void deplacerPointLivraison()throws Exception;
	
	/**
	 * Methode etat pour la methode choixNouveauPointLivraison.
	 * @throws Exception lors du choix de la du nouveau point de livraison.
	 */
	public void choixNouveauPointLivraison()throws Exception;
	
	/**
	 * Methode etat pour la methode effectuerAjoutPointLivraison.
	 * @param idDepart l'id du point de depart.
	 * @param idNouvelle le nouvel id.
	 * @param duree la duree.
	 * @throws Exception l'exception lors de l'ajout du point de livraison.
	 */
	public void effectuerAjoutPointLivraison(long idDepart, long idNouvelle, int duree)throws Exception;
	
	/**
	 * Methode etat pour la methode effectuerSupprimerPointLivraison.
	 * @param id du point de livraison a supprimer.
	 * @throws Exception l'exception lors de la suppression du point de livraison.
	 */
	public void effectuerSupprimerPointLivraison(long id) throws Exception;
	
	/**
	 * Methode etat pour la methode choixPointLivraisonApresDeplacer.
	 * @throws Exception l'exception lors du choix de la livraison apres le deplacement.
	 */
	public void choixPointLivraisonApresDeplacer() throws Exception;
	
	/**
	 * Methode etat pour la methode effectuerDeplacement.
	 * @param idADeplacer l'id du point de livraison a deplacer.
	 * @param idApres l'id du point a livraison apres etre deplace.
	 * @throws Exception l'exception lors du deplacement.
	 */
	public void effectuerDeplacement(long idADeplacer, long idApres) throws Exception;

	public void undo();

	public void redo();

	public Document exportFeuilleDeRoute() throws FileNotFoundException, DocumentException;
	
	//public void effaceListenerOnClick();
	
}
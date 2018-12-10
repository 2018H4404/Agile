package controleur;

import java.io.File;

/**
 * La classe Etat.
 * @author H4404
 */
public interface Etat {

	/**
	 * Methode etat pour la methode chargerFichierPlan.
	 * @param f
	 * @throws Exception
	 */
	public void chargerFichierPlan(File f)throws Exception;
	
	/**
	 * Methode etat pour la methode CalculerLesTournees.
	 * @param nbLivreur
	 * @throws Exception
	 */
	public void CalculerLesTournees(int nbLivreur)throws Exception;

	/**
	 * Methode etat pour la methode lectureLivraisonEntrepotXML.
	 * @param f
	 * @throws Exception
	 */
	public void lectureLivraisonEntrepotXML(File f)throws Exception;
	
	/**
	 * Methode etat pour la methode getNbLivreurMaximum.
	 * @return
	 * @throws Exception
	 */
	public int getNbLivreurMaximum()throws Exception;
	
	/**
	 * Methode etat pour la methode modifierPointLivraison.
	 * @throws Exception
	 */
	public void modifierPointLivraison()throws Exception;

	/**
	 * Methode etat pour la methode ajouterListenerOnClick.
	 */
	public void ajouterListenerOnClick();
	
	/**
	 * Methode etat pour la methode ajouterPointLivraison.
	 * @throws Exception
	 */
	public void ajouterPointLivraison()throws Exception;
	
	/**
	 * Methode etat pour la methode supprimerPointLivraison.
	 * @throws Exception
	 */
	public void supprimerPointLivraison()throws Exception;
	
	/**
	 * Methode etat pour la methode deplacerPointLivraison.
	 * @throws Exception
	 */
	public void deplacerPointLivraison()throws Exception;
	
	/**
	 * Methode etat pour la methode choixNouvellePointLivraison.
	 * @throws Exception
	 */
	public void choixNouvellePointLivraison()throws Exception;
	
	/**
	 * Methode etat pour la methode effectuerAjoutPointLivraison.
	 * @param idDepart
	 * @param idNouvelle
	 * @param duree
	 * @throws Exception
	 */
	public void effectuerAjoutPointLivraison(long idDepart, long idNouvelle, int duree)throws Exception;
	
	/**
	 * Methode etat pour la methode effectuerSupprimerPointLivraison.
	 * @param id
	 * @throws Exception
	 */
	public void effectuerSupprimerPointLivraison(long id) throws Exception;
	
	/**
	 * Methode etat pour la methode choixPointLivraisonApresDeplacer.
	 * @throws Exception
	 */
	public void choixPointLivraisonApresDeplacer() throws Exception;
	
	/**
	 * Methode etat pour la methode effectuerDeplacement.
	 * @param idADeplacer
	 * @param idApres
	 * @throws Exception
	 */
	public void effectuerDeplacement(long idADeplacer, long idApres) throws Exception;
	
	//public void effaceListenerOnClick();
	
}
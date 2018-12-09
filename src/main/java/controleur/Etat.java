package controleur;

import java.io.File;


public interface Etat {

	public void chargerFichierPlan(File f)throws Exception;
		
	public void CalculerLesTournees(int nbLivreur)throws Exception;

	public void lectureLivraisonEntrepotXML(File f)throws Exception;
	
	public int getNbLivreurMaximum()throws Exception;
	
	public void modifierPointLivraison()throws Exception;

	public void ajouterListenerOnClick();
	
	public void ajouterPointLivraison()throws Exception;
	
	public void supprimerPointLivraison()throws Exception;
	
	public void deplacerPointLivraison()throws Exception;
	
	public void choixNouvellePointLivraison()throws Exception;
	
	public void effectuerAjoutPointLivraison(long idDepart, long idNouvelle, int duree)throws Exception;
	
	public void effectuerSupprimerPointLivraison(long id) throws Exception;
	
	public void choixPointLivraisonApresDeplacer() throws Exception;
	
	public void effectuerDeplacement(long idADeplacer, long idApres) throws Exception;
	
	//public void effaceListenerOnClick();
	
}
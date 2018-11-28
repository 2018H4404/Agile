package controleur;

import java.io.File;


public interface Etat {

	public void chargerFichierPlan(File f)throws Exception;
	
	public void chargerFichierDemandeLivraison(File f)throws Exception;
	
	public void CalculerLesTournees()throws Exception;
	

}
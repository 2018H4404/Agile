package controleur;

import java.io.File;


public interface Etat {

	public void chargerFichierPlan(File f)throws Exception;
		
	public void CalculerLesTournees()throws Exception;

	public void lectureLivraisonEntrepotXML(File f)throws Exception;
	

}
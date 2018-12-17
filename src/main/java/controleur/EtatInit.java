package controleur;

import java.io.File;

import modele.services.LecteurDeXML;

/**
 * La classe de l'etat initial.
 * @author H4404
 */
public class EtatInit extends EtatDefaut {

	
	/**
	 * Methode pour charger un plan selon le fichier XML passe.
	 * @see Etat
	 */
	@Override
	public void chargerFichierPlan(File f) throws Exception {
		LecteurDeXML.getInstance().lecturePlanXML(f);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPlanCharge());
	}
}
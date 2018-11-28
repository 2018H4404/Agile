package controleur;

import java.io.File;

import services.LecteurDeXML;

public class EtatInit extends EtatDefaut {

	@Override
	public void chargerFichierPlan(File f) throws Exception {
		LecteurDeXML.getInstance().lecturePlanXML(f);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPlanCharge());

	}
}
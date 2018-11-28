package controleur;

import java.io.File;

import services.LecteurDeXML;

public class EtatPlanCharge extends EtatDefaut {

	@Override
	public void lectureLivraisonEntrepotXML(File f) throws Exception {
		LecteurDeXML.getInstance().lectureLivraisonEntrepotXML(f);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatDemandeLivraison());

	}
	
}

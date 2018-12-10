package controleur;

import java.io.File;

import modele.services.LecteurDeXML;

/**
 * La classe de l'etat plan charge.
 * @author H4404
 */
public class EtatPlanCharge extends EtatDefaut {

	@Override
	public void lectureLivraisonEntrepotXML(File f) throws Exception {
		LecteurDeXML.getInstance().lectureLivraisonEntrepotXML(f);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatDemandeLivraison());
	}
}

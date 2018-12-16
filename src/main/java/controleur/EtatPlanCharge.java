package controleur;

import java.io.File;

import modele.services.LecteurDeXML;

/**
 * La classe de l'etat plan charge.
 * @author H4404
 */
public class EtatPlanCharge extends EtatDefaut {

	/**
	 * Methode pour charger une demande de livraison selon le fichier XML passe.
	 * @see Etat.
	 */
	@Override
	public void lectureLivraisonEntrepotXML(File f) throws Exception {
		LecteurDeXML.getInstance().lectureLivraisonEntrepotXML(f);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatDemandeLivraison());
	}
}

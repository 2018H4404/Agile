package controleur;

/**
 * La classe de l'etat de l'ajout du choix de nouvelle point de livraison.
 * @author H4404
 */
public class EtatAjouterChoixNouvellePointLivraison extends EtatDefaut{
	@Override
	public void effectuerAjoutPointLivraison(long idDepart, long idNouvelle, int duree) throws Exception {
		Controleur.getInstance().getMonManager().ajouterPointLivraison(idDepart, idNouvelle, duree);
		Controleur.getInstance().setEtat(Controleur.getInstance().getEtatPosteCalcul());
		CommandeAjouterLivraison cmd = new CommandeAjouterLivraison(Controleur.getInstance().getCommandeIdPrece(),Controleur.getInstance().getCommandeId(),Controleur.getInstance().getCommandeVue(),Controleur.getInstance().getCommandeDuree());
		Controleur.getInstance().getHistorique().ajouteCmd(cmd);
	}
	
	@Override
	public int getNbLivreurMaximum() {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
}

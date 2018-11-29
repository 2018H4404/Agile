package controleur;

public class EtatDemandeLivraison extends EtatDefaut {

	@Override
	public void CalculerLesTournees(int nbLivreur) {
		
		Controleur.getInstance().getMonManager().calculerLesTournees(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan(), nbLivreur);

	}
	
	@Override
	public int getNbLivreurMaximum() throws Exception {
		return Controleur.getInstance().getMaDemande().getNbLivreurMaximum();
	}
	
}

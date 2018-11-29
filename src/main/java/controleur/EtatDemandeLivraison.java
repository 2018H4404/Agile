package controleur;

public class EtatDemandeLivraison extends EtatDefaut {

	@Override
	public void CalculerLesTournees() {
		
		Controleur.getInstance().getMonManager().calculerLesTournees(Controleur.getInstance().getMaDemande(), Controleur.getInstance().getMonPlan());

	}
	
}

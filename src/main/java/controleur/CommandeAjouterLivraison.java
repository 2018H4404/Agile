package controleur;

public class CommandeAjouterLivraison implements Commande{
	private long prePoint;
	private long nouvellePoint;
	private int duree;

	public CommandeAjouterLivraison(long prePoint, long nouvellePoint, int duree) {
		this.prePoint = prePoint;
		this.nouvellePoint = nouvellePoint;
		this.duree = duree;
	}

	@Override
	public void doCmd() {
			try {
				Controleur.getInstance().getMonManager().ajouterPointLivraisonMetier(prePoint, nouvellePoint, duree);
				Controleur.getInstance().getMonManager().notifyVue();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public void undoCmd() {
			try {
				Controleur.getInstance().getMonManager().supprimerPointLivraisonMetier(nouvellePoint);
				Controleur.getInstance().getMonManager().notifyVue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}

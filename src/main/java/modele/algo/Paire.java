package modele.algo;

public class Paire {
	private int numeroLivraison;
	private int cout;
	
	public Paire(int cout, int numero) {
		this.cout = cout;
		this.numeroLivraison = numero;
	}

	public int getNumeroLivraison() {
		return numeroLivraison;
	}

	public void setNumeroLivraison(int numeroLivraison) {
		this.numeroLivraison = numeroLivraison;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}
	
	
}

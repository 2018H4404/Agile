package modele.algo;

public class Paire {
	private int numeroLivraison;
	private int cout;
	
	/**
	 * Constructeur de la classe Paire.
	 * @param cout : cout entre deux points de livraison.
	 * @param numero : le numero du point de livraison etant la destination.
	 */
	public Paire(int cout, int numero) {
		this.cout = cout;
		this.numeroLivraison = numero;
	}

	//Getters et Setters
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

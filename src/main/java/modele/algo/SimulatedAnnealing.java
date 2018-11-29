package modele.algo;

import java.util.ArrayList;
import java.util.List;

/** 
 * La classe de l'algorithme du Simulated Annealing (Recuit Simulé).
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class SimulatedAnnealing implements TSP{
	
	/**
	 * Les Attributs de la solution.
	 */
	private Integer[] meilleuresSolution;
    private Double coutMeilleureSolution;
    private Boolean tempsLimiteAtteint;
    private Boolean arreterCalcul;

    /** 
     * La température du Simulated Annealing.
    */
    private double temperature;
    
    /** 
     * Le ratio de refroidissement du Simulated Annealing.
    */
    private double ratioRefroidisement;
    
    /**
     * Les paramètres de l'algorithme par défaut.
     */
    private static final double TEMPERATURE = 10000;
    private static final double RATIO_REFROIDISSEMENT = 0.03;

    /**
     * Contructeur paramétré du Simulated Annealing.
     * @param temperature La température du Simulated Annealing.
     * @param ratioRefroidisement Le ratio de refroidissement du Simulated Annealing.
     */
    public SimulatedAnnealing(double temperature, double ratioRefroidisement){
        this.temperature = temperature;
        this.ratioRefroidisement = ratioRefroidisement;
    }
    
    /**
     * Constructeur par défaut du Simulated Annealing.
     */
    public SimulatedAnnealing(){
        this.temperature = TEMPERATURE;
        this.ratioRefroidisement = RATIO_REFROIDISSEMENT;
    }
    
    @Override
    /**
     * Retourne le temps limite atteint.
     */
    public Boolean getTempsLimiteAtteint() {
    	return tempsLimiteAtteint;
    }
    
    
    public void demarrerRechercheSolution(int nombreLivreurs, int tempsLimite, Double[][] couts) throws AlgoException{
    }

	@Override
	public void chercheSolution(int tpsLimite, int nbSommets, int[][] cout, int[] duree,int nbLivreur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getMeilleureSolution(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCoutMeilleureSolution() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}

package controleur;

import java.io.File;

import modele.TourneeManager;
import modele.metier.DemandeLivraison;
import modele.metier.Plan;
import services.LecteurDeXML;
import vue.VueGraphique;
import vue.VueTextuelle;

public class Controleur {
	private Plan monPlan;
	private DemandeLivraison maDemande;
	private TourneeManager monManager;
	private VueGraphique graph;
	private VueTextuelle texte;
	private static Controleur instance = null;
	
	private Controleur() {
		monPlan = new Plan();
		maDemande = new DemandeLivraison();
		monManager = new TourneeManager();
	}
	
	public static Controleur getInstance() {
		if(instance == null) {
			instance = new Controleur();
		}
		return instance;
	}
	
	public void chargerFichierPlan(File f) {
		LecteurDeXML.getInstance().lecturePlanXML(f);
	}
	
	public void chargerFichierDemandeLivraison(File f) {
		LecteurDeXML.getInstance().lectureLivraisonEntrepotXML(f);
	}
	
	public void CalculerLesTournees() {
		monManager.calculerLesTournees(maDemande, monPlan);
	}
	
	public double transformerLatitude(double latitude, double hauteur) {
		return monPlan.transformLatitude(latitude, hauteur);
	}
	
	public double transformerLongitude(double longitude, double largeur) {
		return monPlan.transformLongitude(longitude, largeur);
	}
	
	public void addObserver(VueGraphique vue) {
		monPlan.addObserver(vue);
		maDemande.addObserver(vue);
		monManager.addObserver(vue);
	}

	public Plan getMonPlan() {
		return monPlan;
	}

	public DemandeLivraison getMaDemande() {
		return maDemande;
	}
	
	public TourneeManager getMonManager() {
		return monManager;
	}

	public void setGraph(VueGraphique graph) {
		Controleur.getInstance().graph = graph;
	}

	public void setTexte(VueTextuelle texte) {
		Controleur.getInstance().texte = texte;
	}
	
	
	
}

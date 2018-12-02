package controleur;

import java.io.File;

import org.joda.time.DateTime;

import modele.TourneeManager;
import modele.metier.DemandeLivraison;
import modele.metier.Plan;
import modele.services.LecteurDeXML;
import vue.VueGraphique;
import vue.VueTextuelle;

public class Controleur {
	private Etat etat;
	private Plan monPlan;
	private DemandeLivraison maDemande;
	private TourneeManager monManager;
	private VueGraphique graph;
	private VueTextuelle texte;
	private static Controleur instance = null;
	private EtatPlanCharge etatPlanCharge;
	private EtatInit etatInit;
	private EtatDemandeLivraison etatDemandeLivraison;

	
	private Controleur() {
		monPlan = new Plan();
		maDemande = new DemandeLivraison();
		monManager = new TourneeManager();
		etatPlanCharge = new EtatPlanCharge();
		etatInit = new EtatInit();
		etatDemandeLivraison = new EtatDemandeLivraison();
		etat = etatInit;
	}
	
	public static Controleur getInstance() {
		if(instance == null) {
			instance = new Controleur();
		}
		return instance;
	}
	
	public VueGraphique getGraph() {
		return this.graph;
	}
	
	
	public void chargerFichierPlan(File f) throws Exception {
		etat.chargerFichierPlan(f);
	}
	
	public void chargerFichierDemandeLivraison(File f) throws Exception{
		etat.lectureLivraisonEntrepotXML(f);
	}
	
	public void calculerLesTournees(int nbLivreur) throws Exception{
		etat.CalculerLesTournees(nbLivreur);
	}
	
	public int getNbLivreurMaximum() throws Exception {
		return etat.getNbLivreurMaximum();
	}
	
	public double transformerLatitude(double latitude, double hauteur) {
		return monPlan.transformLatitude(latitude, hauteur);
	}
	
	public double transformerLongitude(double longitude, double largeur) {
		return monPlan.transformLongitude(longitude, largeur);
	}
	
	public double reverseTransformLongitude(double longitudeTransforme,double largeur)throws Exception {
		return monPlan.reverseTransformLongitude(longitudeTransforme,largeur);
	}
	
	public double reverseTransformLatitude(double latitudeTransforme,double hauteur)throws Exception{
		return monPlan.reverseTransformLatitude(latitudeTransforme,hauteur);
	}
	
	public void addObserver(VueGraphique graph, VueTextuelle texte) {
		monPlan.addObserver(graph);
		maDemande.addObserver(graph);
		monManager.addObserver(graph);
		monPlan.addObserver(texte);
		maDemande.addObserver(texte);
		monManager.addObserver(texte);
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

	public Etat getEtatDemandeLivraison() {
		return etatDemandeLivraison;
	}

	public void setEtat(Etat etatCrt) {
		Controleur.getInstance().etat = etatCrt;
	}
	public Etat getEtatCourant() {
		return etat;
	}
	public Etat getEtatInit() {
		return etatInit;
	}

	public Etat getEtatPlanCharge() {
		return etatPlanCharge;
	}
	
	public DateTime getActuelHeureDepart() {
		return maDemande.getDebutTime();
	}
	
}

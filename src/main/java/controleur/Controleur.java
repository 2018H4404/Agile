package controleur;

import java.io.File;

import org.joda.time.DateTime;

import modele.TourneeManager;
import modele.metier.DemandeLivraison;
import modele.metier.Intersection;
import modele.metier.Plan;
import modele.services.LecteurDeXML;
import vue.VueGraphique;
import vue.VueTextuelle;

/**
 * La classe controleur.
 * @author H4404
 */
public class Controleur {
	private Etat etat;
	private Plan monPlan;
	private DemandeLivraison maDemande;
	private TourneeManager monManager;
	private VueGraphique graph;
	private VueTextuelle texte;
	private long ajoutIdDepartPointLivraison;
	private long ajoutIdNouvellePointLivraison;
	private long idADeplacerPointLivraison;
	private long idApresDeplacerPointLivraison;
	private static Controleur instance = null;
	private EtatPlanCharge etatPlanCharge;
	private EtatInit etatInit;
	private EtatDemandeLivraison etatDemandeLivraison;
	private EtatPosteCalcul etatPosteCalcul;
	private EtatAjouterChoixPointLivraison etatAjouterChoixPointLivraison;
	private EtatAjouterChoixNouvellePointLivraison etatAjouterChoixNouvellePointLivraison;
	private EtatSupprimerChoixPointLivraison etatSupprimerChoixPointLivraison;
	private EtatChoixPointLivraisonADeplacer etatChoixPointLivraisonADeplacer;
	private EtatChoixPointLivraisonApresDeplacer etatChoixPointLivraisonApresDeplacer;
	
	/**
	 * Constructeur du controlleur.
	 */
	
	private Controleur() {
		monPlan = new Plan();
		maDemande = new DemandeLivraison();
		monManager = new TourneeManager();
		etatPlanCharge = new EtatPlanCharge();
		etatInit = new EtatInit();
		etatDemandeLivraison = new EtatDemandeLivraison();
		etatPosteCalcul = new EtatPosteCalcul();
		etatAjouterChoixPointLivraison = new EtatAjouterChoixPointLivraison();
		etatAjouterChoixNouvellePointLivraison = new EtatAjouterChoixNouvellePointLivraison();
		etatSupprimerChoixPointLivraison = new EtatSupprimerChoixPointLivraison();
		etatChoixPointLivraisonADeplacer = new EtatChoixPointLivraisonADeplacer();
		etatChoixPointLivraisonApresDeplacer = new EtatChoixPointLivraisonApresDeplacer();
		etat = etatInit;
	}
	
	/**
	 * Methode pour avoir l'instance du controleur.
	 * @return
	 */
	public static Controleur getInstance() {
		if(instance == null) {
			instance = new Controleur();
		}
		return instance;
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat ajouterPointLivraison.
	 * @throws Exception
	 */
	public void ajouterPointLivraison() throws Exception{
		etat.ajouterPointLivraison();
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat supprimerPointLivraison.
	 * @throws Exception
	 */
	public void supprimerPointLivraison() throws Exception{
		etat.supprimerPointLivraison();
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat deplacerPointLivraison.
	 * @return
	 */
	public void deplacerPointLivraison() throws Exception{
		etat.deplacerPointLivraison();
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat chargerFichierPlan.
	 * @param f
	 * @throws Exception
	 */
	public void chargerFichierPlan(File f) throws Exception {
		etat.chargerFichierPlan(f);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat lectureLivraisonEntrepotXML.
	 * @param f
	 * @throws Exception
	 */
	public void chargerFichierDemandeLivraison(File f) throws Exception{
		etat.lectureLivraisonEntrepotXML(f);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat CalculerLesTournees.
	 * @param nbLivreur
	 * @throws Exception
	 */
	public void calculerLesTournees(int nbLivreur) throws Exception{
		etat.CalculerLesTournees(nbLivreur);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat getNbLivreurMaximum.
	 * @return
	 * @throws Exception
	 */
	public int getNbLivreurMaximum() throws Exception {
		return etat.getNbLivreurMaximum();
	}

	/**
	 * Methode pour avoir le plan.
	 * @return
	 */
	public Plan getMonPlan() {
		return monPlan;
	}

	/**
	 * Methode pour avoir la demande de livraison.
	 * @return
	 */
	public DemandeLivraison getMaDemande() {
		return maDemande;
	}
	
	/**
	 * Methode pour avoir le tournee manager.
	 * @return
	 */
	public TourneeManager getMonManager() {
		return monManager;
	}

	/**
	 * Methode pour avoir l'etat getEtatDemandeLivraison.
	 * @return
	 */
	public Etat getEtatDemandeLivraison() {
		return etatDemandeLivraison;
	}

	/**
	 * Methode pour avoir l'etat etatAjouterChoixNouvellePointLivraison.
	 * @return
	 */
	public EtatAjouterChoixNouvellePointLivraison getEtatAjouterChoixNouvellePointLivraison() {
		return etatAjouterChoixNouvellePointLivraison;
	}
	
	/**
	 * Methode pour avoir l'etat.
	 * @return
	 */
	public Etat getEtatCourant() {
		return etat;
	}
	
	/**
	 * Methode pour avoir l'etat initial.
	 * @return
	 */
	public Etat getEtatInit() {
		return etatInit;
	}

	/**
	 * Methode pour l'etat plan charge.
	 * @return
	 */
	public Etat getEtatPlanCharge() {
		return etatPlanCharge;
	}
	
	/**
	 * Methode pour avoir l'etat AjouterChoixPointLivraison.
	 * @return
	 */
	public Etat getEtatAjouterChoixPointLivraison() {
		return etatAjouterChoixPointLivraison;
	}

	/**
	 * Methode pour avoir l'etat etatSupprimerChoixPointLivraison.
	 * @return
	 */
	public EtatSupprimerChoixPointLivraison getEtatSupprimerChoixPointLivraison() {
		return etatSupprimerChoixPointLivraison;
	}
	
	/**
	 * Methode pour avoir l'etat etatChoixPointLivraisonADeplacer.
	 * @return
	 */
	public EtatChoixPointLivraisonADeplacer getEtatChoixPointLivraisonADeplacer() {
		return etatChoixPointLivraisonADeplacer;
	}
	
	/**
	 * Methode pour avoir l'etat etatChoixPointLivraisonApresDeplacer.
	 * @return
	 */
	public EtatChoixPointLivraisonApresDeplacer getEtatChoixPointLivraisonApresDeplacer() {
		return etatChoixPointLivraisonApresDeplacer;
	}

	/**
	 * Methode pour avoir l'heure actuelle de depart.
	 * @return
	 */
	public DateTime getActuelHeureDepart() {
		return maDemande.getDebutTime();
	}

	/**
	 * Methode pour avoir l'etat etatPosteCalcul.
	 * @return
	 */
	public Etat getEtatPosteCalcul() {
		return etatPosteCalcul;
	}
	
	/**
	 * Methode pour retourner le graphe de la vue graphique.
	 * @return
	 */
	public VueGraphique getGraph() {
		return this.graph;
	}
	
	/**
	 * Methode pour transformer la latitude et la hauteur du plan.
	 * @param latitude
	 * @param hauteur
	 * @return
	 */
	public double transformerLatitude(double latitude, double hauteur) {
		return monPlan.transformLatitude(latitude, hauteur);
	}
	
	/**
	 * Methode pour transformer la longitude du plan.
	 * @param longitude
	 * @param largeur
	 * @return
	 */
	public double transformerLongitude(double longitude, double largeur) {
		return monPlan.transformLongitude(longitude, largeur);
	}
	
	/**
	 * Methode pour appliquer la methode reverse transform de mon plan.
	 * @param latitudeTransforme
	 * @param hauteur
	 * @return
	 * @throws Exception
	 */
	public double reverseTransformLatitude(double latitudeTransforme,double hauteur)throws Exception{
		return monPlan.reverseTransformLatitude(latitudeTransforme,hauteur);
	}
	
	/**
	 * Methode pour pour reverseTransformLongitude de plans.
	 * @param longitudeTransforme
	 * @param largeur
	 * @return
	 * @throws Exception
	 */
	public double reverseTransformLongitude(double longitudeTransforme,double largeur)throws Exception {
		return monPlan.reverseTransformLongitude(longitudeTransforme,largeur);
	}
	
	/**
	 * Methode pour ajouter l'observateur.
	 * @param graph
	 * @param texte
	 */
	public void addObserver(VueGraphique graph, VueTextuelle texte) {
		monPlan.addObserver(graph);
		maDemande.addObserver(graph);
		monManager.addObserver(graph);
		monPlan.addObserver(texte);
		maDemande.addObserver(texte);
		monManager.addObserver(texte);
	}
	
	/**
	 * Methode pour ajouter le listener.
	 */
	public void ajouterListenerOnClick() {
		etat.ajouterListenerOnClick();
	}
	
	/*
	public void effaceListenerOnClick() {
		etat.effaceListenerOnClick();
	}*/
	
	/**
	 * Methode pour mettre le controlleur sur l'etat choixNouvellePointLivraison.
	 * @param id
	 * @throws Exception
	 */
	public void setAjoutDepart(long id) throws Exception{
		this.ajoutIdDepartPointLivraison = id;
		etat.choixNouvellePointLivraison();
	}
	
	/**
	 * Methode pour mettre le controlleur sur l'etat effectuerAjoutPointLivraison.
	 * @param id
	 * @param duree
	 * @throws Exception
	 */
	public void setAjoutNouvellePoint(long id,int duree) throws Exception{
		this.ajoutIdNouvellePointLivraison = id;
		etat.effectuerAjoutPointLivraison(ajoutIdDepartPointLivraison, ajoutIdNouvellePointLivraison, duree);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat choixPointLivraisonApresDeplacer.
	 * @param id
	 * @throws Exception
	 */
	public void setADeplacer(long id) throws Exception{
		this.idADeplacerPointLivraison = id;
		etat.choixPointLivraisonApresDeplacer();
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat effectuerDeplacement.
	 * @param id
	 * @throws Exception
	 */
	public void setApresDeplacer(long id) throws Exception{
		this.idApresDeplacerPointLivraison = id;
		etat.effectuerDeplacement(idADeplacerPointLivraison, idApresDeplacerPointLivraison);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat effectuerSupprimerPointLivraison.
	 * @param id
	 * @throws Exception
	 */
	public void setSupprimerPointLivraison(long id) throws Exception{
		etat.effectuerSupprimerPointLivraison(id);
	}
	
	/**
	 * Methode pour mettre l'etat.
	 * @param etatCrt
	 */
	public void setEtat(Etat etatCrt) {
		Controleur.getInstance().etat = etatCrt;
	}
	
	/**
	 * Methode pour mettre le graphe.
	 * @param graph
	 */
	public void setGraph(VueGraphique graph) {
		Controleur.getInstance().graph = graph;
	}

	/**
	 * Methode pour mettre le texte.
	 * @param texte
	 */
	public void setTexte(VueTextuelle texte) {
		Controleur.getInstance().texte = texte;
	}
}

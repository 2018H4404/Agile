package controleur;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.util.ArrayList;


import org.joda.time.DateTime;

import modele.TourneeManager;
import modele.metier.DemandeLivraison;
import modele.metier.Intersection;
import modele.metier.Plan;
import modele.metier.Tournee;
import modele.services.LecteurDeXML;
import modele.services.SerialiseurFeuilleDeRoute;
import modele.metier.PointLivraison;

import vue.VueGraphique;
import vue.VueTextuelle;
import vue.element.PointLivraisonVue;

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
	private Historique historique;
	private long ajoutIdDepartPointLivraison;
	private long ajoutIdNouvellePointLivraison;
	private long idADeplacerPointLivraison;
	private long idApresDeplacerPointLivraison;
	private CommandeAjouterLivraison tempAjout;
	private CommandeSupprimeLivraison tempSupprimer;
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
		historique = new Historique();
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
		tempAjout = null;
		tempSupprimer = null;
	}
	
  
	public void setAjoutNouvellePoint(long id,int duree) throws Exception{
		this.ajoutIdNouvellePointLivraison = id;
		etat.effectuerAjoutPointLivraison(ajoutIdDepartPointLivraison, ajoutIdNouvellePointLivraison, duree);
	}
	
  /**
	 * Methode pour mettre sur le controleur sur l'etat ajouterPointLivraison.
	 * @throws Exception l'exception a l'ajout du point de livraison.
	 */
	public void ajouterPointLivraison() throws Exception{
		etat.ajouterPointLivraison();
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat supprimerPointLivraison.
	 * @throws Exception l'exception a la suppression du point de livraison.
	 */
	public void supprimerPointLivraison() throws Exception{
		etat.supprimerPointLivraison();
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat deplacerPointLivraison.
	 * throws Exception l'exception au deplacement du point de livraison.
	 */
	public void deplacerPointLivraison() throws Exception{
		etat.deplacerPointLivraison();
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat chargerFichierPlan.
	 * @param f le fichier XML.
	 * @throws Exception au chargement du point de livraison.
	 */
	public void chargerFichierPlan(File f) throws Exception {
		etat.chargerFichierPlan(f);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat lectureLivraisonEntrepotXML.
	 * @param f le fichier XML.
	 * @throws Exception l'exception a la lecture du fichier.
	 */
	public void chargerFichierDemandeLivraison(File f) throws Exception{
		etat.lectureLivraisonEntrepotXML(f);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat CalculerLesTournees.
	 * @param nbLivreur nombre de livreurs.
	 * @throws Exception l'exception au calcul des tournee.
	 */
	public void calculerLesTournees(int nbLivreur, int mode) throws Exception{
		etat.CalculerLesTournees(nbLivreur,mode);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat getNbLivreurMaximum.
	 * @return l'etat.
	 * @throws Exception l'exception lors de l'etat.
	 */
	public int getNbLivreurMaximum() throws Exception {
		return etat.getNbLivreurMaximum();
	}

	/**
	 * Methode pour avoir le plan.
	 * @return le plan.
	 */
	public Plan getMonPlan() {
		return monPlan;
	}

	/**
	 * Methode pour avoir la demande de livraison.
	 * @return la demande de livraison.
	 */
	public DemandeLivraison getMaDemande() {
		return maDemande;
	}
	
	/**
	 * Methode pour avoir le tournee manager.
	 * @return le tournee manager.
	 */
	public TourneeManager getMonManager() {
		return monManager;
	}

  
	public int getDureePointLivraison(long id) {
		return maDemande.getPointLivraisonParId(id).getDuree();
	}

	public Intersection getPrePointLivraisonId(long id) throws Exception{
		return monManager.getPrePointLivraisonId(id);
	}
	
	public void setTimeLimite(int time){
		monManager.setTimeLimite(time);;
	}
	
  	/**
	 * Methode pour avoir l'etat getEtatDemandeLivraison.
	 * @return l'etat de la demande de livraison.
	 */
	public Etat getEtatDemandeLivraison() {
		return etatDemandeLivraison;
	}

	/**
	 * Methode pour avoir l'etat etatAjouterChoixNouvellePointLivraison.
	 * @return l'etat etatAjouterChoixNouvellePointLivraison.
	 */
	public EtatAjouterChoixNouvellePointLivraison getEtatAjouterChoixNouvellePointLivraison() {
		return etatAjouterChoixNouvellePointLivraison;
	}
	
	/**
	 * Methode pour avoir l'etat.
	 * @return l'etat.
	 */
	public Etat getEtatCourant() {
		return etat;
	}
	
	/**
	 * Methode pour avoir l'etat initial.
	 * @return l'etat etatInit.
	 */
	public Etat getEtatInit() {
		return etatInit;
	}

	/**
	 * Methode pour l'etat plan charge.
	 * @return l'etat etatPlanCharge.
	 */
	public Etat getEtatPlanCharge() {
		return etatPlanCharge;
	}
	
	/**
	 * Methode pour avoir l'etat AjouterChoixPointLivraison.
	 * @return l'etat etatAjouterChoixPointLivraison.
	 */
	public Etat getEtatAjouterChoixPointLivraison() {
		return etatAjouterChoixPointLivraison;
	}

	/**
	 * Methode pour avoir l'etat etatSupprimerChoixPointLivraison.
	 * @return l'etat etatSupprimerChoixPointLivraison.
	 */
	public EtatSupprimerChoixPointLivraison getEtatSupprimerChoixPointLivraison() {
		return etatSupprimerChoixPointLivraison;
	}
	
	/**
	 * Methode pour avoir l'etat etatChoixPointLivraisonADeplacer.
	 * @return l'etat etatChoixPointLivraisonADeplacer.
	 */
	public EtatChoixPointLivraisonADeplacer getEtatChoixPointLivraisonADeplacer() {
		return etatChoixPointLivraisonADeplacer;
	}
	
	/**
	 * Methode pour avoir l'etat etatChoixPointLivraisonApresDeplacer.
	 * @return l'etat etatChoixPointLivraisonApresDeplacer.
	 */
	public EtatChoixPointLivraisonApresDeplacer getEtatChoixPointLivraisonApresDeplacer() {
		return etatChoixPointLivraisonApresDeplacer;
	}

	/**
	 * Methode pour avoir l'heure actuelle de depart.
	 * @return le temps de debut de la demande
	 */
	public DateTime getActuelHeureDepart() {
		return maDemande.getDebutTime();
	}

	/**
	 * Methode pour avoir l'etat etatPosteCalcul.
	 * @return l'etat du poste de calcul.
	 */
	public Etat getEtatPosteCalcul() {
		return etatPosteCalcul;
	}
	
	/**
	 * Methode pour retourner le graphe de la vue graphique.
	 * @return le graphe de la vue graphique.
	 */
	public VueGraphique getGraph() {
		return this.graph;
	}
	
	/**
	 * Methode pour avoir l'instance du controleur.
	 * @return retourne l'instance.
	 */
	public static Controleur getInstance() {
		if(instance == null) {
			instance = new Controleur();
		}
		return instance;
	}
	

	
	public long getIdAjoutDepart()throws Exception{
		return this.ajoutIdDepartPointLivraison;
	}
	

  
  public long getIdAjoutNouvellePoint() {
		return ajoutIdNouvellePointLivraison;
	}
  
	/**
	 * Methode pour transformer la latitude et la hauteur du plan.
	 * @param latitude la latitude du plan.
	 * @param hauteur la hauteur du plan.
	 * @return la transformation.
	 */
	public double transformerLatitude(double latitude, double hauteur) {
		return monPlan.transformLatitude(latitude, hauteur);
	}
	
	/**
	 * Methode pour transformer la longitude du plan.
	 * @param longitude la longitude du plan.
	 * @param largeur la largeur du plan.
	 * @return le plan transforme.
	 */
	public double transformerLongitude(double longitude, double largeur) {
		return monPlan.transformLongitude(longitude, largeur);
	}
	
	/**
	 * Methode pour appliquer la methode reverse transform de mon plan.
	 * @param latitudeTransforme la latitude de la transformation.
	 * @param hauteur la hauteur de la transformation.
	 * @return la transformation
	 * @throws Exception l'exception lors de la transformation.
	 */
	public double reverseTransformLatitude(double latitudeTransforme,double hauteur)throws Exception{
		return monPlan.reverseTransformLatitude(latitudeTransforme,hauteur);
	}
	
	/**
	 * Methode pour pour reverseTransformLongitude de plans.
	 * @param longitudeTransforme la longitude de la transformation.
	 * @param largeur la largeur de la transformation.
	 * @return le plan transforme.
	 * @throws Exception lors de la transformation.
	 */
	public double reverseTransformLongitude(double longitudeTransforme,double largeur)throws Exception {
		return monPlan.reverseTransformLongitude(longitudeTransforme,largeur);
	}
	
	/**
	 * Methode pour ajouter l'observateur.
	 * @param graph le graphe de la vue graphique.
	 * @param texte de texte de la vue textuelle.
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

	/**
	 * Methode pour mettre le controlleur sur l'etat choixNouvellePointLivraison.
	 * @param id l'id du point de livraison.
	 * @throws Exception lors de l'ajout de la livraison.
	 */
	public void setAjoutDepart(long id) throws Exception{
		this.ajoutIdDepartPointLivraison = id;
		etat.choixNouveauPointLivraison();
	}
	
	/**
	 * Methode pour mettre le controlleur sur l'etat effectuerAjoutPointLivraison.
	 * @param id du nouvau point a ajouter.
	 * @param duree la duree de la livraison.
	 * @throws Exception l'exception d'ajout de nouvel point de livraison.
	 */
	public void setAjoutNouveauPoint(long id,int duree) throws Exception{
		this.ajoutIdNouvellePointLivraison = id;
		etat.effectuerAjoutPointLivraison(ajoutIdDepartPointLivraison, ajoutIdNouvellePointLivraison, duree);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat choixPointLivraisonApresDeplacer.
	 * @param id l'id du point de livraison a deplacer.
	 * @throws Exception l'exception au choix du point de livraison apres le deplacement.
	 */
	public void setADeplacer(long id) throws Exception{
		this.idADeplacerPointLivraison = id;
		etat.choixPointLivraisonApresDeplacer();
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat effectuerDeplacement.
	 * @param id l'id du point de livraison.
	 * @throws Exception l'exception lors du deplacement.
	 */
	public void setApresDeplacer(long id) throws Exception{
		this.idApresDeplacerPointLivraison = id;
		etat.effectuerDeplacement(idADeplacerPointLivraison, idApresDeplacerPointLivraison);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat effectuerSupprimerPointLivraison.
	 * @param id l'id du point de livraison.
	 * @throws Exception l'exception lors du deplacement.
	 */
	public void setSupprimerPointLivraison(long id) throws Exception{
		etat.effectuerSupprimerPointLivraison(id);
	}
	
	/**
	 * Methode pour mettre l'etat.
	 * @param etatCrt l'etat du controleur.
	 */
	public void setEtat(Etat etatCrt) {
		Controleur.getInstance().etat = etatCrt;
	}
	
	/**
	 * Methode pour mettre le graphe.
	 * @param graph l'etat du graphe de la vue graphique.
	 */
	public void setGraph(VueGraphique graph) {
		Controleur.getInstance().graph = graph;
	}

	/**
	 * Methode pour mettre le texte.
	 * @param texte le texte de la vue textuelle.
	 */
	public void setTexte(VueTextuelle texte) {
		Controleur.getInstance().texte = texte;
	}
	
	/**
	 * Methode pour obtenir longitude d'une intersection.
	 * @param id: id de l'intersection.
	 * @return longittude de cette intersection.
	 */
	public double getLongititudeIntersection(long id) {
		return monPlan.getIntersectionNormal(id).getLongitude();
	}
	
	/**
	 * Methode pour obtenir latitude d'une intersection.
	 * @param id: id de l'intersection.
	 * @return latitude de cette intersection.
	 */
	public double getLatitudeIntersection(long id) {
		return monPlan.getIntersectionNormal(id).getLatitude();
	}

	public void undo() {
		etat.undo();
	}

	public void redo() {
		etat.redo();
	}

	public Historique getHistorique() {
		return historique;
	}

	/**
	 * Methode pour exporter une feuille de route dans l'espace de travail.
	 */
	public Document exportFeuilleDeRoute() throws FileNotFoundException, DocumentException {
		return etat.exportFeuilleDeRoute();
	}
	
	public void clearHistorique() {
		historique.clear();
	}


	public CommandeAjouterLivraison getTempAjout() {
		return tempAjout;
	}


	public void setTempAjout(CommandeAjouterLivraison tempAjout) {
		this.tempAjout = tempAjout;
	}


	public CommandeSupprimeLivraison getTempSupprimer() {
		return tempSupprimer;
	}


	public void setTempSupprimer(CommandeSupprimeLivraison tempSupprimer) {
		this.tempSupprimer = tempSupprimer;
	}
}

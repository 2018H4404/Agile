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
	 * Constructeur du controleur.
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
	
  /**
   * Methode pour mettre le nouveau point de livraison a ajouter et effectuer cette operation.
   * @param id Id du point de livraison a ajouter.
   * @param duree la duree de ce point de livraison.
   * @throws Exception si erreur durant l'operation pour ajouter un nouveau point de livraison.
   */
	public void setAjoutNouvellePoint(long id,int duree) throws Exception{
		this.ajoutIdNouvellePointLivraison = id;
		etat.effectuerAjoutPointLivraison(ajoutIdDepartPointLivraison, ajoutIdNouvellePointLivraison, duree);
	}
	
  /**
	 * Methode pour mettre le controleur sur l'etat EtatAjouterChoixPointLivraison.
	 * @throws Exception si erreur durant le passage a un nouvel etat.
	 */
	public void ajouterPointLivraison() throws Exception{
		etat.ajouterPointLivraison();
	}
	
	/**
	 * Methode pour mettre le controleur sur l'etat EtatSupprimerChoixPointLivraison.
	 * @throws Exception si erreur durant le passage a un nouvel etat.
	 */
	public void supprimerPointLivraison() throws Exception{
		etat.supprimerPointLivraison();
	}
	
	/**
	 * Methode pour mettre le controleur sur l'etat EtatChoixPointLivraisonADeplacer.
	 * @throws Exception si erreur durant le passage a un nouvel etat.
	 */
	public void deplacerPointLivraison() throws Exception{
		etat.deplacerPointLivraison();
	}
	
	/**
	 * Methode pour mettre le controleur sur l'etat EtatPlanCharge et charger le plan dans le fichier XML dont le flux est passe en parametre.
	 * @param f le fichier XML.
	 * @throws Exception si erreur durant le chargement d'un plan.
	 */
	public void chargerFichierPlan(File f) throws Exception {
		etat.chargerFichierPlan(f);
	}
	
	/**
	 * Methode pour mettre le controleur sur l'etat EtatDemandeLivraison et charger la demande de livraison dans le fichier XML dont le flux est passe en parametre..
	 * @param f le fichier XML.
	 * @throws Exception si erreur durant le chargement d'une demande de livraison.
	 */
	public void chargerFichierDemandeLivraison(File f) throws Exception{
		etat.lectureLivraisonEntrepotXML(f);
	}
	
	/**
	 * Methode pour mettre le controleur sur l'etat EtatPosteCalcul et calculer les tournees
	 * selon le nombre de livreur et le mode passe.
	 * @param nbLivreur nombre de livreurs.
	 * @param mode mode utilise pour calculer les tournees(1 pour sans clustering et 2
	 *                pour clustering)
	 * @throws Exception l'exception au calcul des tournee(Existence d'un point de livraison non livrable).
	 */
	public void calculerLesTournees(int nbLivreur, int mode) throws Exception{
		etat.CalculerLesTournees(nbLivreur,mode);
	}
	
	/**
	 * Methode pour retourner le maximum pour le nombre de livreur.
	 * @return le maximum pour le nombre de livreur.
	 * @throws Exception si erreur durant l'execution.
	 */
	public int getNbLivreurMaximum() throws Exception {
		return etat.getNbLivreurMaximum();
	}

	/**
	 * Methode pour avoir le plan stocke dans le controleur.
	 * @return le plan.
	 */
	public Plan getMonPlan() {
		return monPlan;
	}

	/**
	 * Methode pour avoir la demande de livraison stocke dans le controleur..
	 * @return la demande de livraison.
	 */
	public DemandeLivraison getMaDemande() {
		return maDemande;
	}
	
	/**
	 * Methode pour avoir le tournee manager stocke dans le controleur..
	 * @return le tournee manager.
	 */
	public TourneeManager getMonManager() {
		return monManager;
	}

	/**
	 * Methode pour obtenir la duree d'un point de livraison
	 * @param id Id du point de livraison pour lequel nous voulons obtenir la duree
	 * @return la duree de ce point de livraison
	 */
	public int getDureePointLivraison(long id) {
		return maDemande.getPointLivraisonParId(id).getDuree();
	}

	/**
	 * Methode pour obtenir un point de livraison
	 * @param id Id du point de livraison.
	 * @return le point de livraison correspondant
	 */
	public Intersection getPrePointLivraisonId(long id) throws Exception{
		return monManager.getPrePointLivraisonId(id);
	}
	
	/**
	 * Methode pour definir la limite du temps pour calculer les tournees.
	 * @param time une limite du temps
	 */
	public void setTimeLimite(int time){
		monManager.setTimeLimite(time);;
	}
	
  	/**
	 * Methode pour avoir l'etat etatDemandeLivraison.
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
	 * Methode pour avoir l'etat actuel du controleur.
	 * @return l'etat actuel.
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
	 * Methode pour retourner la vue graphique.
	 * @return la vue graphique.
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
	

	/**
	 * Methode pour obtenir l'id du point de livraison apres lequel nous voulons ajouter un nouveau point de livraison.
	 * @return l'id de ce point de livraison
	 * @throws Exception si erreur durant l'execution.
	 */
	public long getIdAjoutDepart()throws Exception{
		return this.ajoutIdDepartPointLivraison;
	}
	

  /**
   * Methode pour obtenir l'id du point de livraison a ajouter
   * @return l'id de ce point de livraison
   */
	public long getIdAjoutNouvellePoint() {
		return ajoutIdNouvellePointLivraison;
	}
  
	/**
	 * Methode pour transformer la latitude en une coordonnee X dans le plan selon la hauteur de la vue graphique.
	 * @param latitude une lattitude.
	 * @param hauteur la hauteur de la vue graphique.
	 * @return la coordonnee X calculee.
	 */
	public double transformerLatitude(double latitude, double hauteur) {
		return monPlan.transformLatitude(latitude, hauteur);
	}
	
	/**
	 * Methode pour transformer la longitude en une coordonnee Y dans le plan selon la largeur de la vue graphique.
	 * @param longitude une longitude.
	 * @param largeur la largeur de la vue graphique.
	 * @return la coordonnee Y calculee.
	 */
	public double transformerLongitude(double longitude, double largeur) {
		return monPlan.transformLongitude(longitude, largeur);
	}
	
	/**
	 * Methode pour reverser le processus de la methode transformerLatitude.
	 * @param latitudeTransforme la coordonne Y.
	 * @param hauteur la hauteur de la vue graphique.
	 * @return la latitude calculee.
	 * @throws Exception si erreur durant la transformation.
	 */
	public double reverseTransformLatitude(double latitudeTransforme,double hauteur)throws Exception{
		return monPlan.reverseTransformLatitude(latitudeTransforme,hauteur);
	}
	
	/**
	 * Methode pour reverser le processus de la methode transformerLongitude.
	 * @param longitudeTransforme une coordonnee X.
	 * @param largeur la largeur de la vue graphique.
	 * @return la longitude calculee.
	 * @throws Exception si erreur durant la transformation.
	 */
	public double reverseTransformLongitude(double longitudeTransforme,double largeur)throws Exception {
		return monPlan.reverseTransformLongitude(longitudeTransforme,largeur);
	}
	
	/**
	 * Methode pour ajouter l'observateur sur les objets observables stockes dans le controleur.
	 * @param graph la vue graphique.
	 * @param texte la vue textuelle.
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
	 * Methode pour ajouter le listner qui permet l'utilisateur de ajouter des points de livraison (avec duree 0) avant le calcul des tournnees.
	 */
	public void ajouterListenerOnClick() {
		etat.ajouterListenerOnClick();
	}

	/**
	 * Methode pour mettre le controlleur sur l'etat etatAjouterChoixNouvellePointLivraison et definir l'id du point de livraison apres lequel nous voulons realiser un ajout.
	 * @param id l'id du point de livraison apres lequel nous voulons ajouter un nouveau point de livraison.
	 * @throws Exception si erreur lors de l'ajout de la livraison.
	 */
	public void setAjoutDepart(long id) throws Exception{
		this.ajoutIdDepartPointLivraison = id;
		etat.choixNouveauPointLivraison();
	}
	
	/**
	 * Methode pour mettre le controlleur sur l'etat etatPosteCalcul et effectuer l'ajour d'un nouveau point de livraison.
	 * @param id id du nouvau point de livraison a ajouter.
	 * @param duree la duree du nouveau point livraison.
	 * @throws Exception si erreur durant l'ajout du nouveau point de livraison.
	 */
	public void setAjoutNouveauPoint(long id,int duree) throws Exception{
		this.ajoutIdNouvellePointLivraison = id;
		etat.effectuerAjoutPointLivraison(ajoutIdDepartPointLivraison, ajoutIdNouvellePointLivraison, duree);
	}
	
	/**
	 * Methode pour mettre le controleur sur l'etat etatChoixPointLivraisonApresDeplacer.
	 * @param id l'id du point de livraison a deplacer.
	 * @throws Exception si erreur durant l'execution.
	 */
	public void setADeplacer(long id) throws Exception{
		this.idADeplacerPointLivraison = id;
		etat.choixPointLivraisonApresDeplacer();
	}
	
	/**
	 * Methode pour mettre le controleur sur l'etat etatPosteCalcul et effectuer le deplacement.
	 * @param id l'id du point de livraison apres lequel nous voulons mettre le point de livraison a deplacer.
	 * @throws Exception si le deplacement n'est pas realisable(Un deplacement dans la meme tournee).
	 */
	public void setApresDeplacer(long id) throws Exception{
		this.idApresDeplacerPointLivraison = id;
		etat.effectuerDeplacement(idADeplacerPointLivraison, idApresDeplacerPointLivraison);
	}
	
	/**
	 * Methode pour mettre sur le controleur sur l'etat etatSupprimerChoixPointLivraison et supprimer le point de livraison ayant l'id passe en parametre.
	 * @param id l'id du point de livraison a supprimer.
	 * @throws Exception si erreur lors de l'execution.
	 */
	public void setSupprimerPointLivraison(long id) throws Exception{
		etat.effectuerSupprimerPointLivraison(id);
	}
	
	/**
	 * Methode pour mettre le controleur sur l'etat passe.
	 * @param etatCrt un etat.
	 */
	public void setEtat(Etat etatCrt) {
		Controleur.getInstance().etat = etatCrt;
	}
	
	/**
	 * Methode pour mettre la vue graphique.
	 * @param graph une vue graphique.
	 */
	public void setGraph(VueGraphique graph) {
		Controleur.getInstance().graph = graph;
	}

	/**
	 * Methode pour mettre la vue textuelle.
	 * @param texte une vue textuelle.
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

	/**
	 * Methode pour faire une commande
	 * @see Classe Commande
	 */
	public void undo() {
		etat.undo();
	}
	
	/**
	 * Methode pour annuler une commande
	 * @see Classe Commande
	 */
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
	
	/**
	 * Methode pour effacer l'historique des commandes.
	 */
	public void clearHistorique() {
		historique.clear();
	}

	/**
	 * Methode pour obtenir la commande stockee actuellement dans le controleur pour ajouter un point de livraison.
	 * @return la commande qui ajoute un point de livraison.
	 */
	public CommandeAjouterLivraison getTempAjout() {
		return tempAjout;
	}

	/**
	 * Methode pour mettre la commande passee an parametre dans le controleur.
	 * @param tempAjout une commande pour ajouter un point de livraison.
	 */
	public void setTempAjout(CommandeAjouterLivraison tempAjout) {
		this.tempAjout = tempAjout;
	}

	/**
	 * Methode pour obtenir la commande stockee actuellement dans le controleur pour supprimer un point de livraison.
	 * @return la commande qui supprime un point de livraison.
	 */
	public CommandeSupprimeLivraison getTempSupprimer() {
		return tempSupprimer;
	}

	/**
	 * Methode pour mettre la commande passee an parametre dans le controleur.
	 * @param tempAjout une commande pour supprimer un point de livraison.
	 */
	public void setTempSupprimer(CommandeSupprimeLivraison tempSupprimer) {
		this.tempSupprimer = tempSupprimer;
	}
}

package testunitaire;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import modele.services.LecteurDeXML;

import controleur.Controleur;
import modele.*;
import modele.metier.*;

public class TestTourneeManager {

	private static Controleur controleur = Controleur.getInstance();
	private TourneeManager tm;
	private Observer observer;
	private boolean updateAppele;
	
	@Before
	public void setUp(){
		controleur.setEtat(controleur.getEtatInit());
		tm = new TourneeManager();
		updateAppele = false;
		observer = new Observer(){public void update(Observable o, Object arg){updateAppele = true;}};
		tm.addObserver(observer);
	}
	
	@Test
	public void testCalculerLesTournees1() throws Exception {
		File fichierTestPlan = new File("fichiersXML2018/fichiersTest/testTournee_plan1.xml");	
		controleur.chargerFichierPlan(fichierTestPlan);
		Plan unPlan = controleur.getMonPlan();
		File fichierTestDemandeLivraison = new File("fichiersXML2018/fichiersTest/testTournee_dl1.xml");	
		controleur.chargerFichierDemandeLivraison(fichierTestDemandeLivraison);
		DemandeLivraison demande = controleur.getMaDemande();
		tm.calculerLesTournees(demande, unPlan, 1);
		int nbTournee = tm.getListeTournees().size();
		assertEquals(nbTournee,1);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().size(),2);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().get(0).getListeIntersections().size(),3);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().get(1).getListeIntersections().size(),3);
		assert(updateAppele);
	}
	
	@Test
	public void testCalculerLesTournees2() throws Exception {
		File fichierTestPlan = new File("fichiersXML2018/fichiersTest/testTournee_plan1.xml");	
		controleur.chargerFichierPlan(fichierTestPlan);
		Plan unPlan = controleur.getMonPlan();
		File fichierTestDemandeLivraison = new File("fichiersXML2018/fichiersTest/testTournee_dl2.xml");	
		controleur.chargerFichierDemandeLivraison(fichierTestDemandeLivraison);
		DemandeLivraison demande = controleur.getMaDemande();
		tm.calculerLesTournees(demande, unPlan, 2);
		int nbTournee = tm.getListeTournees().size();
		assertEquals(nbTournee,2);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().size(),2);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().get(0).getListeIntersections().size(),2);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().get(1).getListeIntersections().size(),2);
		assertEquals(tm.getListeTournees().get(1).getListeChemins().get(0).getListeIntersections().size(),3);
		assertEquals(tm.getListeTournees().get(1).getListeChemins().get(1).getListeIntersections().size(),3);
		assert(updateAppele);
	}
	
	@Test
	public void testCalculerLesTournees3() throws Exception {
		System.out.println("-------------------------------------------------------------------");
		File fichierTestPlan = new File("fichiersXML2018/fichiersTest/testTournee_plan2.xml");	
		controleur.chargerFichierPlan(fichierTestPlan);
		Plan unPlan = controleur.getMonPlan();
		File fichierTestDemandeLivraison = new File("fichiersXML2018/fichiersTest/testTournee_dl3.xml");	
		controleur.chargerFichierDemandeLivraison(fichierTestDemandeLivraison);
		DemandeLivraison demande = controleur.getMaDemande();
		tm.calculerLesTournees(demande, unPlan, 3);
		int nbTournee = tm.getListeTournees().size();
		assertEquals(nbTournee,3);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().size(),3);
		Intersection test1 = new IntersectionNormal(26057085,45.756638,4.8683963);
		Intersection test2 = new PointLivraison(26057084,45.756714,4.8673143,60);
		
		assertEquals(tm.getListeTournees().get(0).getListeChemins().get(0).getListeIntersections().get(2),test1);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().get(0).getListeIntersections().get(3),test2);
		
		//System.out.println(tm.getListeTournees().get(0).getListeChemins().get(0).getListeIntersections());
		
		
		assert(updateAppele);
	}
	
	@Test
	public void testCalculerLesTourneesClustering() throws Exception {
		File fichierTestPlan = new File("fichiersXML2018/fichiersTest/testTournee_plan2.xml");	
		controleur.chargerFichierPlan(fichierTestPlan);
		Plan unPlan = controleur.getMonPlan();
		File fichierTestDemandeLivraison = new File("fichiersXML2018/fichiersTest/testTournee_dl3.xml");	
		controleur.chargerFichierDemandeLivraison(fichierTestDemandeLivraison);
		DemandeLivraison demande = controleur.getMaDemande();
		tm.calculerLesTourneesClustering(demande, unPlan, 3);
		int nbTournee = tm.getListeTournees().size();
		assertEquals(nbTournee,3);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().size(),3);
		Intersection test1 = new IntersectionNormal(26057085,45.756638,4.8683963);
		Intersection test2 = new PointLivraison(26057084,45.756714,4.8673143,60);
		
		assertEquals(tm.getListeTournees().get(0).getListeChemins().get(0).getListeIntersections().get(2),test1);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().get(0).getListeIntersections().get(3),test2);
		
	}
	
	
	
	@Test
	public void testCalculerLesTournees_TempsPasserLimite() throws Exception{
		
		/*
		 * 
		 * assert(updateAppele);
		 */
		
		
	}
	
	
}

package testunitaire;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import services.LecteurDeXML;
import controleur.Controleur;
import modele.*;
import modele.metier.*;

public class TestTourneeManager {

	private static Controleur controleur = Controleur.getInstance();
	private TourneeManager tm;
	
	@Before
	public void setUp(){
		controleur.setEtat(controleur.getEtatInit());
		tm = new TourneeManager();
	}
	
	@Test
	public void testCalculerLesTournees1() throws Exception {
		File fichierTestPlan = new File("fichiersXML2018/fichiersTest/testPlan.xml");	
		controleur.chargerFichierPlan(fichierTestPlan);
		Plan unPlan = controleur.getMonPlan();
		File fichierTestDemandeLivraison = new File("fichiersXML2018/fichiersTest/testPlan.xml");	
		controleur.chargerFichierDemandeLivraison(fichierTestDemandeLivraison);
		DemandeLivraison demande = controleur.getMaDemande();
		tm.calculerLesTournees(demande, unPlan, 1);
		
		
	}
	
}

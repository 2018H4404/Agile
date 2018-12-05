package testunitaire;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import modele.services.LecteurDeXML;

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
		File fichierTestPlan = new File("fichiersXML2018/fichiersTest/testTournee_plan2.xml");	
		controleur.chargerFichierPlan(fichierTestPlan);
		Plan unPlan = controleur.getMonPlan();
		File fichierTestDemandeLivraison = new File("fichiersXML2018/fichiersTest/testTournee_dl2.xml");	
		controleur.chargerFichierDemandeLivraison(fichierTestDemandeLivraison);
		DemandeLivraison demande = controleur.getMaDemande();
		tm.calculerLesTournees(demande, unPlan, 1);
		int nbTournee = tm.getListeTournees().size();
		assertEquals(nbTournee,1);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().size(),2);
		
		//System.out.println(tm.getListeTournees().get(0).getListeChemins().get(0).getListeIntersections().toString());
		//System.out.println(tm.getListeTournees().get(0).getListeChemins().get(j).getListeTroncons().toString());
		assertEquals(tm.getListeTournees().get(0).getListeChemins().get(0).getListeIntersections().size(),3);
		assertEquals(tm.getListeTournees().get(0).getListeChemins().get(1).getListeIntersections().size(),3);
	}
	
}

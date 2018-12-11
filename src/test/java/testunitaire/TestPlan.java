/*package testunitaire;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import modele.TourneeManager;
import modele.metier.*;
import controleur.Controleur;

public class TestPlan {

	private static Controleur controleur = Controleur.getInstance();
	@Before
	public void setUp() throws Exception{
		File fichierTestPlan = new File("fichiersXML2018/fichiersTest/testTournee_plan1.xml");	
		controleur.chargerFichierPlan(fichierTestPlan);
		
		File fichierTestDemandeLivraison = new File("fichiersXML2018/fichiersTest/testTournee_dl1.xml");	
		controleur.chargerFichierDemandeLivraison(fichierTestDemandeLivraison);
		System.out.println("-------------------------------------------------------------------");
	}
	
	@Test
	public void testTransformLongitude() {
		System.out.println("------------testTransformLongitude----------------");
		Plan plan = controleur.getMonPlan();
		double l = plan.transformLongitude(1, 1.0);
		System.out.println(l);
	}
	
	@Test
	public void testTransformLatitude() {
		System.out.println("------------testTransformLatitude----------------");
		Plan plan = controleur.getMonPlan();
		double l = plan.transformLatitude(1, 1.0);
		System.out.println(l);
		assert(l==45.756638);
	}
	
	@Test
	public void testReverseTransformLongitude() {
		System.out.println("------------testReverseTransformLongitude----------------");
		Plan plan = controleur.getMonPlan();
		double l = plan.reverseTransformLongitude(1, 1.0);
		System.out.println(l);
	}
	
	@Test
	public void testReverseTransformLatitude() {
		System.out.println("------------testReverseTransformLatitude----------------");
		Plan plan = controleur.getMonPlan();
		double l = plan.reverseTransformLatitude(1, 1.0);
		System.out.println(l);
	}
	
}
*/
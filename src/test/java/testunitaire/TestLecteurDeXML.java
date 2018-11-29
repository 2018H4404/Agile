package testunitaire;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import services.LecteurDeXML;
import controleur.Controleur;
import modele.metier.*;

public class TestLecteurDeXML {
	
	private static LecteurDeXML lecture = LecteurDeXML.getInstance();
	private static Controleur controleur = Controleur.getInstance();

/*--------------------------------------Test Lecture Plan----------------------------------------------*/	

	@Test
	public void testLecturePlanXML(){
		Plan plan = controleur.getMonPlan();
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan.xml");	
		lecture.lecturePlanXML(fichierTestPlan);
		assertEquals(plan.getIntersectionNormals(),4);
		assertEquals(plan.getTroncons(),3);
	}
	
	
	@Test(expected = FileNotFoundException.class) 
	public void testLecturePlanXML_FileNotFoundException(){
		System.out.println("test Lecture Plan XML File Not Found Exception:");
		File fichierTestPlan=new File("fichiersXML2018/testPlan2.xml");	
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	//------------------------verification construction du document XML----------------------------------
	@Test
	public void testLecturePlanXML_FichierVide(){
		System.out.println("test Lecture Plan XML Fichier Vide:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testFichierVide.xml");
		lecture.lecturePlanXML(fichierTestPlan);
		
	}
	
	@Test
	public void testLecturePlanXML_FormatTxt(){
		System.out.println("test Lecture Plan XML Format Txt:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_FormatTxt.txt");
		lecture.lecturePlanXML(fichierTestPlan);	
	}
	
	@Test
	public void testLecturePlanXML_NoeudEtTronconNull(){
		System.out.println("test Lecture Plan XML Noeud Et Troncon Null:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_NoeudEtTronconNull.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	@Test
	public void testLecturePlanXML_RacineFausse(){
		System.out.println("test Lecture Plan XML Racine Fausse:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_RacineFausse.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	@Test(expected = NumberFormatException.class)
	public void testLecturePlanXML_NoeudManqueAttribut(){
		System.out.println("test Lecture Plan XML Noeud Manque Attribut:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_NoeudManqueAttribut.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	//------------------------verification construnction de l’objet Plan---------------------------------
	
	@Test(expected = NumberFormatException.class)
	public void testLecturePlanXML_NoeudFormatFausse(){
		System.out.println("test Lecture Plan XML Noeud Format Fausse:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_NoeudFormatFausse.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	@Test(expected = NumberFormatException.class)
	public void testLecturePlanXML_NoeudPointeurNull(){
		System.out.println("test Lecture Plan XML Noeud Pointeur Null:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_NoeudPointeurNull.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	@Test(expected = NumberFormatException.class)
	public void testLecturePlanXML_NoeudTronconInverse(){
		System.out.println("test Lecture Plan XML Noeud Troncon Inverse:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_NoeudTronconInverse.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	@Test
	public void testLecturePlanXML_TronconFormatFausse(){
		System.out.println("test Lecture Plan XML Troncon Format Fausse:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_TronconFormatFausse.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	@Test
	public void testLecturePlanXML_TronconLongeurNegative(){
		System.out.println("test Lecture Plan XML Troncon Longeur Negative:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_TronconLongeurNegative.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	@Test
	public void testLecturePlanXML_TronconNull(){
		System.out.println("test Lecture Plan XML Troncon Null:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_TronconNull.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	@Test
	public void testLecturePlanXML_TronconOriginNull(){
		System.out.println("test Lecture Plan XML Troncon Origin Null:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_TronconOriginNull.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
	@Test(expected = NumberFormatException.class)
	public void testLecturePlanXML_TronconPointeurNull(){
		System.out.println("test Lecture Plan XML Troncon Pointeur Null:");
		File fichierTestPlan=new File("fichiers/fichiersTest/testPlan_TronconPointeurNull.xml");
		lecture.lecturePlanXML(fichierTestPlan);
	}
	
/*-------------------------------Test Lecture Livraison Entrepot---------------------------------------*/
	
	@Test
	public void testLectureLivraisonEntrepotXML(){
		System.out.println("test Lecture Livraison Entrepot XML:");
		File fichierTestLivraison=new File("fichiers/fichiersTest/dl-test-3.xml");
		lecture.lectureLivraisonEntrepotXML(fichierTestLivraison);
		
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testLectureLivraisonEntrepotXML_FileNotFoundException(){
		System.out.println("test Lecture Livraison Entrepot XML File Not Found Exception:");
		File fichierTestLivraison=new File("fichiers/fichiersTest/dl-test-5.xml");
		lecture.lectureLivraisonEntrepotXML(fichierTestLivraison);
		
	}
	
	
	
	
	//------------------------verification construction du document XML----------------------------------

	@Test
	public void testLectureLivraisonEntrepotXML_LivraisonFormatFausse(){
		System.out.println("test Lecture Livraison Entrepot XML Livraison Format Fausse:");
		File fichierTestLivraison=new File("fichiers/fichiersTest/dl-test-3-LivraisonFormatFausse.xml");
		lecture.lectureLivraisonEntrepotXML(fichierTestLivraison);
	}
	@Test
	public void testLectureLivraisonEntrepotXML_LivraisonPointeurNull(){
		System.out.println("test Lecture Livraison Entrepot XML Livraison Pointeur Null:");
		File fichierTestLivraison=new File("fichiers/fichiersTest/dl-test-3-LivraisonPointeurNull.xml");
		lecture.lectureLivraisonEntrepotXML(fichierTestLivraison);
	}
	@Test
	public void testLectureLivraisonEntrepotXML_RacineFausse(){
		System.out.println("test Lecture Livraison Entrepot XML Racine Fausse:");
		File fichierTestLivraison=new File("fichiers/fichiersTest/dl-test-3-RacineFausse.xml");
		lecture.lectureLivraisonEntrepotXML(fichierTestLivraison);
	}
	@Test(expected=NumberFormatException.class)
	public void testLectureLivraisonEntrepotXML_EntrepotManqueAttribut(){
		System.out.println("test Lecture Livraison Entrepot XML Entrepot Manque Attribut:");
		File fichierTestLivraison=new File("fichiers/fichiersTest/dl-test-3-EntrepotManqueAttribut.xml");
		lecture.lectureLivraisonEntrepotXML(fichierTestLivraison);
	}
	

	@After
	public void after() {
		System.out.println("-----------------------------------------------------------------------------");
	}
	 
	@AfterClass
	public static void clean() {
		lecture=null;
		System.out.println("");
	}
	
}

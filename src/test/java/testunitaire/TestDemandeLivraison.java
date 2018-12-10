package testunitaire;

import static org.junit.Assert.*;
import org.junit.*;
import org.joda.time.DateTime;
import java.util.Observable;
import java.util.Observer;
import java.util.HashMap;
import modele.metier.*;

public class TestDemandeLivraison {
	
	Observer observer;
	boolean updateAppele;
	
	@Before
	public void setUp(){
		updateAppele = false;
		observer = new Observer(){public void update(Observable o, Object arg){updateAppele = true;}};
	}
	
	@Test
	public void testAjouterEntrepot() {
		DemandeLivraison dl = new DemandeLivraison();
		dl.addObserver(observer);
		dl.ajouterEntrepot(48830472, 45.75406, 4.857418, 8,0,0);
		assert(updateAppele);
	}
	
	@Test
	public void testAjouterPointLivraison() {
		DemandeLivraison dl = new DemandeLivraison();
		dl.addObserver(observer);
		dl.ajouterPointLivraisonMetier(48830472, 45.75406, 4.857418, 360);
		PointLivraison test = new PointLivraison(48830472, 45.75406, 4.857418, 360);
		assert(dl.getAllPointLivraisons().contains(test));
	}
	
	@Test
	public void testClear() {
		DemandeLivraison dl = new DemandeLivraison();
		dl.ajouterEntrepot(48830472, 45.75406, 4.857418, 8,0,0);
		dl.ajouterPointLivraisonMetier(48830471, 45.75406, 4.857418, 360);
		dl.clear();	
		assertNull(dl.getEntrepotParId(48830472));
		assertNull(dl.getPointLivraisonParId(48830471));
	}
	
	@Test
	public void testAjouterPoint() {
		
		
	}
	
	@Test
	public void testSupprimerPoint() {
		
		
		
	}
	
	@Test
	public void testGetNbLivreurMaximum() {
		DemandeLivraison dl = new DemandeLivraison();
		dl.ajouterEntrepot(48830472, 45.75406, 4.857418, 8,0,0);
		dl.ajouterPointLivraisonMetier(48830471, 45.75406, 4.857418, 360);
		dl.ajouterPointLivraisonMetier(48830471, 45.75406, 4.857418, 360);
		int nblivreur = dl.getNbLivreurMaximum();
		assertEquals(nblivreur,1);
	}
	
	@Test
	public void testGetDebutTime() {
		DemandeLivraison dl = new DemandeLivraison();
		dl.ajouterEntrepot(48830472, 45.75406, 4.857418, 8,0,0);
		DateTime test = new DateTime(2018,11,30,8,0,0);
		DateTime timeDepart = dl.getDebutTime();
		assertEquals(test,timeDepart);
	}
	
	

}

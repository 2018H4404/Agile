package testunitaire;

import static org.junit.Assert.*;
import org.junit.*;
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
	public void TestAjouterEntrepot() {
		DemandeLivraison dl = new DemandeLivraison();
		dl.addObserver(observer);
		dl.ajouterEntrepot(48830472, 45.75406, 4.857418, 8,0,0);
		assert(updateAppele);
	}
	
	@Test
	public void TestAjouterPointLivraison() {
		DemandeLivraison dl = new DemandeLivraison();
		dl.addObserver(observer);
		dl.ajouterPointLivraisonMetier(48830472, 45.75406, 4.857418, 360);
		assert(updateAppele);
	}
	
	@Test
	public void TestClear() {
		DemandeLivraison dl = new DemandeLivraison();
		dl.ajouterEntrepot(48830472, 45.75406, 4.857418, 8,0,0);
		dl.ajouterPointLivraisonMetier(48830471, 45.75406, 4.857418, 360);
		dl.clear();	
		assertNull(dl.getEntrepotParId(48830472));
		assertNull(dl.getPointLivraisonParId(48830471));
	}
	

}

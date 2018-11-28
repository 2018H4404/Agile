package vue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import controleur.Controleur;
import modele.TourneeManager;
import modele.metier.Intersection;
import modele.metier.IntersectionNormal;
import modele.metier.Plan;
import modele.metier.Troncon;
import services.LecteurDeXML;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ArrayList<Intersection> l = new ArrayList<Intersection>();
    	l.add(new IntersectionNormal(495424862,45.752663,4.857675));
    	l.add(new IntersectionNormal(495214862,45.752663,4.857675));
    	for(Intersection inter : l) {
    		System.out.println(inter);
    	}
    	ArrayList<Intersection> l1 = new ArrayList<Intersection>();
    	l1.add(l.get(0));
    	for(Intersection inter : l1) {
    		System.out.println(inter);
    	}
    	l1.clear();
    	for(Intersection inter : l1) {
    		System.out.println(inter);
    	}
    	for(Intersection inter : l) {
    		System.out.println(inter);
    	}
    	/*
    	File f = new File("fichiersXML2018/petitPlan.xml");
    	Controleur.monPlan = new Plan();
    	LecteurDeXML.getInstance().lecturePlanXML(f);
    	
    	TourneeManager t = new TourneeManager();
    	ArrayList<Intersection> l = t.algoAEtoile(new IntersectionNormal(495424862,45.752663,4.857675), new IntersectionNormal(25468067,45.762028,4.864684), Controleur.monPlan);
    	System.out.println("Done");
    	for(Intersection inter : l) {
    		System.out.println(inter);
    	}
    	ArrayList<Troncon> traduction = t.traductionTrajet(l, Controleur.monPlan);
    	for(Troncon c : traduction) {
    		System.out.println(c);
    	}*/
    	/*
    	HashMap<Intersection,Double> h = new HashMap<Intersection,Double>();
    	h.put(new IntersectionNormal(23,4.0,5.0), 2.3);
    	h.put(new IntersectionNormal(25,4.0,5.0), 2.6);
    	System.out.println(h.get(new IntersectionNormal(25,4.0,5.0)));*/
    	
    	
    	/*ArrayList<Troncon> c = Controleur.monPlan.getTronconsParOrigine(2129259180);
    	c.clear();
    	c = Controleur.monPlan.getTronconsParOrigine(2129259180);
    	System.out.println(c.size());*/
    }
}

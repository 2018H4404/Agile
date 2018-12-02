package modele.algo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import modele.metier.Intersection;
import modele.metier.Plan;
import modele.metier.Troncon;

/** 
 * La classe de l'algorithme A*.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class AEtoile {
	
	private static AEtoile instance = null;
	
	private static final double RAYON_TERRE = 6378.137;
	
	private AEtoile() {}
	
	public static AEtoile getInstance() {
		if(instance == null) instance = new AEtoile();
		return instance;
	}
	
	/**
	 * Méthode pour traduire le trajet.
	 * @param chemin le chemin en intersection.
	 * @param unPlan le plan de la ville. 
	 * @return la traduction du chemin en trajet.
	 */
	public ArrayList<Troncon> traductionTrajet(ArrayList<Intersection> chemin, Plan unPlan){
		ArrayList<Troncon> traduction = new ArrayList<Troncon>();
		int length = chemin.size();
		for(int i = 0; i < length-1; i++) {
			ArrayList<Troncon> tempListe = unPlan.getTronconsParOrigine(chemin.get(i).getId());
			for(int j = 0; j < tempListe.size(); j++) {
				Intersection tempDest = tempListe.get(j).getDestination();
				if(tempDest.equals(chemin.get(i+1))) {
					traduction.add(tempListe.get(j));
					break;
				}
			}
		}
		return traduction;
	}
	
	/**
	 * Méthode permettant de trouver les voisins d'une intersection.
	 * @param idCourant l'identifiant de l'intersection en cours.
	 * @param monPlan le plan de la ville.
	 * @return la liste des voisins
	 */
	private ArrayList<Troncon> trouverVosins(final long idCourant, Plan monPlan) {
		return monPlan.getTronconsParOrigine(idCourant);
	}
	
	/**
	 * Méthode de l'algorithme A*.
	 * @param depart l'intersection de départ.
	 * @param dest l'intersection de la déstination.
	 * @param monPlan la plan de la ville.
	 * @return la liste des intersections du chemin à prendre.
	 */
	public ArrayList<Intersection> algoAEtoile(Intersection depart, Intersection dest, Plan monPlan){
		ArrayList<Intersection> meilleurChemin = new ArrayList<Intersection>(); 
		
		HashMap<Intersection,Intersection> parents = new HashMap<Intersection,Intersection>();
		parents.put(depart, depart);
		HashMap<Intersection,Double> distanceEstimeeF = new HashMap<Intersection,Double>();
		distanceEstimeeF.put(depart,heuristique(depart,dest));
		
		ArrayList<Intersection> noir = new ArrayList<Intersection>();
		
		/*PriorityQueue<Paire> gris = new PriorityQueue<Paire>(1,
				new Comparator<Paire>() {  
	                  public int compare(Paire p1, Paire p2) {  
	                	  if (p1.valeurF < p2.valeurF) { return -1; }
	                      if (p1.valeurF  > p2.valeurF){ return 1; }
	                      return 0;
	                    }  
	                  }); */
		Map<Double, Intersection> gris = new TreeMap<Double, Intersection>(
				new Comparator<Double>() {  
	                  public int compare(Double p1, Double p2) {  
	                	  if (p1 < p2) { return -1; }
	                      if (p1 > p2) { return 1; }
	                      return 0;
	                    }  
	                  });
		gris.put(heuristique(depart,dest), depart);
		//gris.offer(new Paire(depart,heuristique(depart,dest)));
		
		ArrayList<Troncon> voisins = new ArrayList<Troncon>();
		
		while( !gris.isEmpty() )
		{
			Map.Entry<Double, Intersection> elemCourant = premierElement(gris);
			Intersection interCourant = elemCourant.getValue();
			
			if(dest.equals(interCourant)) {
				meilleurChemin.clear();
				while(!depart.equals(interCourant)) { 
					meilleurChemin.add(0, interCourant);
					interCourant = parents.get(interCourant);
				}
				meilleurChemin.add(0, depart);
				return meilleurChemin;
			}
			
			noir.add(interCourant);
			gris.remove(elemCourant.getKey());
			
			voisins = trouverVosins(interCourant.getId(),monPlan);
			
			if(voisins != null) {
			for(Troncon voisin : voisins) {
				if(noir.contains(voisin.getDestination())) continue;
				Intersection interVoisin = voisin.getDestination();
				double nouvelleDistance = distanceEstimeeF.get(interCourant) + voisin.getLongueur() - heuristique(interCourant,dest) + heuristique(interVoisin,dest);
				if(isGris(gris,voisin.getDestination().getId())) {
					//System.out.println(interVoisin);
					//System.out.println(distanceEstimeeF.get(interVoisin));
					//Double ancienneDistance = trouverValuerF(interVoisin,distanceEstimeeF);
					//System.out.println(ancienneDistance);
					if(distanceEstimeeF.get(interVoisin) > nouvelleDistance) {
						distanceEstimeeF.remove(interVoisin);
						distanceEstimeeF.put(interVoisin, nouvelleDistance);
						parents.remove(interVoisin);
						parents.put(interVoisin, interCourant);
						Double position = trouverKey(interVoisin,gris);
						gris.remove(position);
						gris.put(nouvelleDistance, interVoisin);
						
					}
				}else {
					distanceEstimeeF.put(interVoisin, nouvelleDistance);
					parents.put(interVoisin, interCourant);
					gris.put(nouvelleDistance, interVoisin);
				}
			}
			}
			
		}
		return meilleurChemin;
	}
	
	/**
	 * Méthode pour le premier élément.
	 * @param map le mapping des intersections.
	 * @return le premier élément.
	 */
	public Map.Entry<Double, Intersection> premierElement(Map<Double,Intersection> map){
		Map.Entry<Double, Intersection> retour = null;
		Set<Entry<Double,Intersection>> set = map.entrySet();
		for(Map.Entry<Double, Intersection> element : set) {
			retour = element;
			break;
		}
		return retour;
	}
	
	/**
	 * Méthode pour trouver la valeur F.
	 * @param unInter une intersection.
	 * @param distanceEstimeeF la distance éstimée de F.
	 * @return la valeur de F.
	 */
	public Double trouverValuerF(Intersection unInter, HashMap<Intersection,Double> distanceEstimeeF){
		Double retour = 0.0;
		Set<Entry<Intersection,Double>> set = distanceEstimeeF.entrySet();
		for(Map.Entry<Intersection,Double> element : set) {
			if(element.getKey().equals(unInter)) {
				retour = element.getValue();
				break;
			}
		}
		return retour;
	}
	
	/**
	 * Méthode pour trouver la clé
	 * @param inter une intersection.
	 * @param map un mapping d'intersection.
	 * @return retourn la clé.
	 */
	public Double trouverKey(Intersection inter, Map<Double,Intersection> map) {
		Double keyTrouve = 0.0;
		Set<Entry<Double,Intersection>> set = map.entrySet();
		for(Map.Entry<Double, Intersection> element : set) {
			if(inter.equals(element.getValue())) {
				keyTrouve = element.getKey();
				break;
			}
		}
		return keyTrouve;
	}
	
	/**
	 * Méthode de l'heuristique.
	 * @param depart intersection de départ.
	 * @param dest intersection d'arrivée.
	 * @return retourne la distance entre le départ et l'arrivée.
	 */
	public double heuristique(Intersection depart, Intersection dest) {
		return getDistance(depart.getLatitude(), depart.getLongitude(), dest.getLatitude(), dest.getLongitude());
	}
	
	/**
	 * Méthode pour savoir si le noeud est gris.
	 * @param gris noeuds gris.
	 * @param voisin identifiant du voisin.
	 * @return retourne vrai si le noeud est gris.
	 */
	public boolean isGris(Map<Double, Intersection> gris, Long voisin) {
		boolean retour = false;
		Set<Entry<Double,Intersection>> tempSet = gris.entrySet();
		for(Entry<Double,Intersection> element : tempSet) {
			if(element.getValue().getId() == voisin) {//.getId()
				retour = true;
			}
		}
		return retour;
	}
	
	/**
	 * Méthode pour calculer la distance entre deux points.
	 * @param latStart la latitude de début.
	 * @param longStart la longitude du début.
	 * @param latEnd la latitude d'arrivée.
	 * @param longEnd la longitude d'arrivée.
	 * @return la distance entre les deux points.
	 */
	public double getDistance(double latStart,double longStart,double latEnd,double longEnd)
    {
        double radLat1 = rad(latStart);
        double radLat2 = rad(latEnd);
        double a = radLat1 - radLat2;
        double b = rad(longStart) - rad(longEnd);
        
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * RAYON_TERRE * 1000;
        s = Math.round(s * 10000) / 10000;
        return s;
    
    }
	
	/**
	 * Méthode pour convertire un degré à un radian.
	 * @param d le degré de l'angle.
	 * @return la valeur en radian.
	 */
	private double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

}

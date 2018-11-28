package modele.metier;

import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

public class DemandeLivraison extends Observable{
	private HashMap<Long,PointLivraison> livraisons;
	private HashMap<Long,Entrepot> entrepots;
	
	public DemandeLivraison() {
		livraisons = null;
		entrepots = null;
	}
	
	public void intialiserDemandeLivraison(HashMap<Long,PointLivraison> livraisons, HashMap<Long,Entrepot> entrepots) {
		if(this.livraisons != null && this.entrepots != null) {
			this.clear();
			System.out.println("Clear demandelivraison");
		}
		this.livraisons = livraisons;
		this.entrepots = entrepots;
		setChanged();
		notifyObservers("DemandeLivraison");
	}
	
	public void ajouterEntrepot(long id, double latitude, double longitude,int heure,int minute,int seconde) {
		Entrepot tempObject = new Entrepot(id,latitude,longitude,heure,minute,seconde);
		entrepots.put(id,tempObject);
		setChanged();
		notifyObservers("DemandeLivraison");
	}
	
	public void clear() {
		entrepots.clear();
		livraisons.clear();
	}
	
	public Entrepot getEntrepotParId(long id) {
		return entrepots.get(id);
	}
	
	public Collection<Entrepot> getAllEntrepots(){
		return entrepots.values();
	}
	
	public void ajouterPointLivraison(long id, double latitude, double longitude,int duree) {
		PointLivraison tempObject = new PointLivraison(id,latitude,longitude,duree);
		livraisons.put(id,tempObject);
		setChanged();
		notifyObservers("DemandeLivraison");
	}
	
	public PointLivraison getPointLivraisonParId(long id) {
		return livraisons.get(id);
	}
	
	public Collection<PointLivraison> getAllPointLivraisons(){
		return livraisons.values();
	}
}

package modele.services.exceptions;

@SuppressWarnings("serial")
public class DemandeLivraisonXMLFileException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML ne correspond pas a une demande de livraison.";
	public DemandeLivraisonXMLFileException(){
        super(EXCEPTION_MESSAGE);
    }
}

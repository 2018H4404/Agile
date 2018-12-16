package modele.services.exceptions;
/** 
 * La classe des exceptions lors on lire une fichier XML.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

@SuppressWarnings("serial")
public class DemandeLivraisonXMLFileException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML ne correspond pas a une demande de livraison.";
	
	/**
	 * Constructeur de la classe DemandeLivraisonXMLFileException.
	 * L'exception est lancee quand le fichier XML ne correspond pas a une demande de livraison.
	 */
	public DemandeLivraisonXMLFileException(){
        super(EXCEPTION_MESSAGE);
    }
}

package exceptions;

/**
 * la classe de l'exception quand le fichier XML n'est pas une demande de livraison
 * @author H4404
 *
 */
@SuppressWarnings("serial")
public class DemandeLivraisonXMLFileException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML ne correspond pas a une demande de livraison.";
	public DemandeLivraisonXMLFileException(){
        super(EXCEPTION_MESSAGE);
    }
}

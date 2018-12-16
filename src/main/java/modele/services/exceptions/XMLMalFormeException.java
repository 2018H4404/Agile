package modele.services.exceptions;

/**
 * La closse pour traiter l'exception lors on lit une fichier XML erronee.
 * @author H4404
 *
 */
@SuppressWarnings("serial")
public class XMLMalFormeException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML contient une erreur.(mal forme)";
	
	/**
	 * Constructeur de la classe XMLMalFormeException.
	 * L'Exception est lancee quand le fichier XML est mal forme.
	 */
	public XMLMalFormeException(){
		super(EXCEPTION_MESSAGE);
    }
}
package modele.services.exceptions;

/**
 * La closse pour traiter l'exception lors on lit une fichier XML erronee.
 * @author H4404
 *
 */
@SuppressWarnings("serial")
public class XMLMalFormeException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML contient une erreur.(mal forme)";
	public XMLMalFormeException(){
		super(EXCEPTION_MESSAGE);
    }
}
package exceptions;

/**
 * La classe pour traiter l'exception lors lire une fichier
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

package modele.services.exceptions;
/** 
 * La classe des exceptions lors on lire une fichier XML errone.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/
@SuppressWarnings("serial")
public class XMLMalFormeException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML contient une erreur.(mal forme)";
	public XMLMalFormeException(){
		super(EXCEPTION_MESSAGE);
    }
}

package exceptions;

@SuppressWarnings("serial")
public class XMLMalFormeException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML contient une erreur.(mal forme)";
	public XMLMalFormeException(){
		super(EXCEPTION_MESSAGE);
    }
}

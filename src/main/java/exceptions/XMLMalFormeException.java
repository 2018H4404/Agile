package exceptions;

public class XMLMalFormeException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML contient une erreur.";
	public XMLMalFormeException(){
		super(EXCEPTION_MESSAGE);
    }
}

package exceptions;

public class DemandeLivraisonXMLFileException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML fourni ne correspond pas à une liste de points à livrer.";
	public DemandeLivraisonXMLFileException(){
        super(EXCEPTION_MESSAGE);
    }
}

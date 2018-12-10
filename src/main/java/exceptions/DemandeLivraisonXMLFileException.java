package exceptions;

@SuppressWarnings("serial")
public class DemandeLivraisonXMLFileException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML fourni ne correspond pas a une liste de points a livrer.";
	public DemandeLivraisonXMLFileException(){
        super(EXCEPTION_MESSAGE);
    }
}

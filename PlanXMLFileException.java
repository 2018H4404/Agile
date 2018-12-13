package exceptions;

/**
 * La classe pour traiter l'exception si utilisateur a ouvert une fichier XML qui n'est pas dans le format de un plan
 * @author H4404
 *
 */
@SuppressWarnings("serial")
public class PlanXMLFileException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML fourni ne correspond pas a un plan.";
	public PlanXMLFileException(){
        super(EXCEPTION_MESSAGE);
    }
}

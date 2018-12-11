package exceptions;

@SuppressWarnings("serial")
public class PlanXMLFileException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML fourni ne correspond pas a un plan.";
	public PlanXMLFileException(){
        super(EXCEPTION_MESSAGE);
    }
}

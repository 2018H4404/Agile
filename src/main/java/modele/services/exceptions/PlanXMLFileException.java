package modele.services.exceptions;

/**
 * La classe des exceptions lors on lire une fichier XML pour charger un plan.
 * 
 * @author H4404
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class PlanXMLFileException extends Exception {
	static String EXCEPTION_MESSAGE = "Le fichier XML fourni ne correspond pas a un plan.";

	public PlanXMLFileException() {
		super(EXCEPTION_MESSAGE);
	}
}

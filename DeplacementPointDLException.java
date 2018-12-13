package exceptions;

/**
 * La classe pour traiter l'exception lors deplace un point de livraison
 * @author H4404
 *
 */
public class DeplacementPointDLException extends Exception {
	static String EXCEPTION_MESSAGE = "Un point de livraison ne peut pas être déplacé vers la même tournée.";
	public DeplacementPointDLException(){
        super(EXCEPTION_MESSAGE);
    }
}

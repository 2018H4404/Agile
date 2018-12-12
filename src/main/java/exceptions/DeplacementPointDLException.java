package exceptions;

public class DeplacementPointDLException extends Exception {
	static String EXCEPTION_MESSAGE = "Un point de livraison ne peut pas être déplacé vers la même tournée.";
	public DeplacementPointDLException(){
        super(EXCEPTION_MESSAGE);
    }
}

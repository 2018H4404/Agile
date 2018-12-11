package exceptions;

/** 
 * La classe des exceptions d'algorithme.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

public class AlgoException extends Exception {

	/**
	 * 
	 * @param msg message de l'exception.
	 */
	public AlgoException(String msg){
        super(msg);
    }
}
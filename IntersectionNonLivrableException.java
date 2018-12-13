package exceptions;

/**
 * La classe pour traiter les points qui sont pas livrables
 * @author H4404
 *
 */
@SuppressWarnings("serial")
public class IntersectionNonLivrableException extends Exception{
	static String EXCEPTION_MESSAGE = "Un des points de livraison n'est pas livrable..";
	public IntersectionNonLivrableException(){
        super(EXCEPTION_MESSAGE);
    }
}

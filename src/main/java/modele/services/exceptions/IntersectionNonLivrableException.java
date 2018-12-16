package modele.services.exceptions;

/**
 * La classe des exceptions lors on ajoute un point dans la liste de livraison
 * or ce point n'est pas livrable.
 * 
 * @author H4404
 * @version 1.0
 * @since 1.0
 */

@SuppressWarnings("serial")
public class IntersectionNonLivrableException extends Exception {
	static String EXCEPTION_MESSAGE = "Un des points de livraison n'est pas livrable..";

	/**
	 * Constructeur de la classe IntersectionNonLivrableException.
	 * L'exception est lancee quand un point de livraison est non livrable.
	 */
	public IntersectionNonLivrableException() {
		super(EXCEPTION_MESSAGE);
	}
}

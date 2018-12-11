package exceptions;

@SuppressWarnings("serial")
public class IntersectionNonLivrableException extends Exception{
	static String EXCEPTION_MESSAGE = "Un des points de livraison n'est pas livrable..";
	public IntersectionNonLivrableException(){
        super(EXCEPTION_MESSAGE);
    }
}

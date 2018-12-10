package exceptions;

@SuppressWarnings("serial")
public class IntersectionNonLivrableException extends Exception{
	static String EXCEPTION_MESSAGE = "Le point choisi n'est pas livrable.";
	public IntersectionNonLivrableException(){
        super(EXCEPTION_MESSAGE);
    }
}

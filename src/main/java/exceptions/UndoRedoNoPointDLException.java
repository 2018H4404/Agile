package exceptions;

@SuppressWarnings("serial")
public class UndoRedoNoPointDLException extends Exception {
	static String EXCEPTION_MESSAGE = "Tous les points de livraisons ont été supprimés, vous ne pouvez annuler la suppression.";
	public UndoRedoNoPointDLException(){
        super(EXCEPTION_MESSAGE);
    }
}

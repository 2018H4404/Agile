package exceptions;

/**
 * La classe pour traiter l'exception lors undo redo des modification
 * @author H4404
 *
 */
@SuppressWarnings("serial")
public class UndoRedoNoPointDLException extends Exception {
	static String EXCEPTION_MESSAGE = "Tous les points de livraisons ont été supprimés, vous ne pouvez annuler la suppression.";
	public UndoRedoNoPointDLException(){
        super(EXCEPTION_MESSAGE);
    }
}

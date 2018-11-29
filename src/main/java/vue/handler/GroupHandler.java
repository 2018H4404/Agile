package vue.handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.Group;

/** 
 * La classe du groupe handler.
 * @author H4404
 * @version 1.0
 * @since 1.0
*/

@SuppressWarnings("restriction")
public class GroupHandler implements EventHandler<MouseEvent> {
	 
    private Group monGroup;
    private double oldGroupX;
    private double oldGroupY;
    private double oldScreenX;
    private double oldScreenY;

    public GroupHandler(Group unGroup) {
        this.monGroup = unGroup;
    }

    @Override
    public void handle(MouseEvent e) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {  
            this.oldGroupX = this.monGroup.getTranslateX();
            this.oldGroupY = this.monGroup.getTranslateY();
            this.oldScreenX = e.getScreenX();
            this.oldScreenY = e.getScreenY();

        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) { 
            this.monGroup.setTranslateX(e.getScreenX() - this.oldScreenX + this.oldGroupX);
            this.monGroup.setTranslateY(e.getScreenY() - this.oldScreenY + this.oldGroupY);
        }
    }
}

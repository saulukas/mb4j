package org.mb4j.example.servlet.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.component.ReflectiveComponent;
import org.mb4j.component.Request;

@Singleton
public class EventEditPanel extends ReflectiveComponent {

    @Inject
    EventEditFormHandler formHandler;

    public EventEditPanelBrick bakeBrick(Request request, int eventId) {
        EventEditPanelBrick brick = new EventEditPanelBrick();
        brick.form = request.resolve(formHandler.fillForm(request, eventId));
        return brick;
    }

}

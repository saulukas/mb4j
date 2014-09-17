package org.mb4j.example.servlet.event.edit;

import com.google.inject.Inject;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewLocator;
import org.mb4j.component.Request;
import static org.mb4j.component.url.UrlPathBuilder.urlPath;
import org.mb4j.example.servlet.master.MasterLayoutPage;

public class EventEditPage extends MasterLayoutPage {

    @Inject
    EventEditPanel contentPanel;


    public static ViewLocator url(int eventId) {
        return ViewLocator.of(EventEditPage.class, urlPath().with(String.valueOf(eventId)));
    }

    @Override
    protected MustacheBrick bakeContentBrick(Request request) {
        int eventId = Integer.parseInt(request.readPathSegment());
        return contentPanel.bakeBrick(request, eventId);
    }
}

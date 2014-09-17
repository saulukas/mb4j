package org.mb4j.example.liferay.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewLocator;
import org.mb4j.component.Request;
import org.mb4j.component.form.Form4Response;
import static org.mb4j.component.url.UrlPathBuilder.urlPath;
import org.mb4j.liferay.PortletView;

@Singleton
public class EventEditView extends PortletView {

    @Inject
    EventEditFormHandler form;

    public static ViewLocator url(int eventId) {
        return ViewLocator.of(EventEditView.class, urlPath().with(String.valueOf(eventId)));
    }

    static class Brick extends MustacheBrick {

        Form4Response form;
    }

    @Override
    public MustacheBrick bakeBrick(Request request) {
        int eventId = Integer.parseInt(request.readPathSegment());
        Brick brick = new Brick();
        brick.form = request.resolve(form.fillForm(request, eventId));
        return brick;
    }
}

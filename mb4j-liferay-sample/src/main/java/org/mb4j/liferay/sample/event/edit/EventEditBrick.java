package org.mb4j.liferay.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.Brick;
import org.mb4j.liferay.sample.domain.Event;
import org.mb4j.liferay.sample.domain.EventQuery;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.baker.BakerView;
import org.mb4j.view.baker.ViewBrickBaker;
import static org.mb4j.view.path.ViewPathBuilder.viewPath;
import org.mb4j.view.url.ViewUrl;

public class EventEditBrick extends Brick {
    String actionSaveUrl;
    EventEditForm form;

    @Singleton
    public static class Baker<P extends Baker.Params> implements ViewBrickBaker<P> {
        @Inject
        EventQuery eventQuery;
        @Inject
        EventEditForm.Filler formFiller;

        @Override
        public EventEditBrick bakeBrick(ViewRequest request, P params) {
            Event event = eventQuery.eventOrNullFor(params.eventId);
            EventEditBrick brick = new EventEditBrick();
            brick.actionSaveUrl = request.stringOf(EventEditForm.SaveAction.url());
            brick.form = formFiller.filledForm(request, new EventEditForm.Filler.Params(event));
            return brick;
        }

        public static class Params {
            public final int eventId;

            public Params(int eventId) {
                this.eventId = eventId;
            }
        }
    }
}

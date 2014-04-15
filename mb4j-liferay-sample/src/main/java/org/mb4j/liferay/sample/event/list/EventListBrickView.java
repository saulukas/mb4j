package org.mb4j.liferay.sample.event.list;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.baker.BakerView;
import org.mb4j.view.url.ViewUrl;

@Singleton
public class EventListBrickView extends BakerView<EventListBrick.Baker.Params> {
    public static final int SHOW_ALL = EventListBrick.Baker.Params.SHOW_ALL;

    public static ViewUrl url() {
        return url(SHOW_ALL);
    }

    public static ViewUrl url(int maxEventCount) {
        return ViewUrl.of(EventListBrickView.class,
                new EventListBrick.Baker.Params(maxEventCount, false).toViewParams());
    }

    @Inject
    public EventListBrickView(EventListBrick.Baker baker) {
        super(baker);
    }

    @Override
    protected EventListBrick.Baker.Params bakerParamsFrom(ViewRequest request) {
        return EventListBrick.Baker.Params.from(request);
    }
}

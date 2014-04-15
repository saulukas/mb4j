package org.mb4j.liferay.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.baker.BakerView;
import static org.mb4j.view.path.ViewPathBuilder.viewPath;
import org.mb4j.view.url.ViewUrl;

@Singleton
public class EventEditBrickView extends BakerView<EventEditBrick.Baker.Params> {
    @Inject
    public EventEditBrickView(EventEditBrick.Baker baker) {
        super(baker);
    }

    public static ViewUrl url(int eventId) {
        return ViewUrl.of(EventEditBrickView.class,
                viewPath().with(String.valueOf(eventId)).instance());
    }

    @Override
    protected EventEditBrick.Baker.Params bakerParamsFrom(ViewRequest request) {
        return new EventEditBrick.Baker.Params(readEventIdFrom(request));
    }

    private int readEventIdFrom(ViewRequest request) {
        return Integer.parseInt(request.pathParamsReader.readSegment());
    }
}

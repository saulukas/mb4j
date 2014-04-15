package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import static org.mb4j.view.path.ViewPathBuilder.viewPath;
import org.mb4j.servlet.sample.master.MasterLayoutPage;
import org.mb4j.view.url.ViewUrl;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.baker.BakerView;

public class EventEditPage extends MasterLayoutPage {
  public static ViewUrl url(int eventId) {
    return ViewUrl.of(View.class, viewPath().with(String.valueOf(eventId)).instance());
  }

  @Singleton
  public static class Baker extends MasterLayoutPage.Baker<EventEditBrick.Baker.Params> {
    @Inject
    public Baker(EventEditBrick.Baker contentBaker) {
      super(contentBaker);
    }
  }

  @Singleton
  public static class View extends BakerView<EventEditBrick.Baker.Params> {
    @Inject
    public View(Baker baker) {
      super(baker);
    }

    @Override
    protected EventEditBrick.Baker.Params bakerParamsFrom(ViewRequest request) {
      return new EventEditBrick.Baker.Params(readEventIdFrom(request));
    }

    private int readEventIdFrom(ViewRequest request) {
      return Integer.parseInt(request.pathParamsReader.readSegment());
    }
  }
}

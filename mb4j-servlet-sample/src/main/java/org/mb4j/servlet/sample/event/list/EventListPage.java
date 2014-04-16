package org.mb4j.servlet.sample.event.list;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.servlet.sample.master.MasterLayoutPage;
import org.mb4j.controller.url.ViewUrl;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.baker.BakerView;

public class EventListPage extends MasterLayoutPage {
  public static final int SHOW_ALL = EventListBrick.Baker.Params.SHOW_ALL;

  public static ViewUrl url() {
    return url(SHOW_ALL);
  }

  public static ViewUrl url(int maxEventCount) {
    return ViewUrl.of(EventListPage.View.class,
        new EventListBrick.Baker.Params(maxEventCount, false).toViewParams());
  }

  @Singleton
  public static class Baker extends MasterLayoutPage.Baker<EventListBrick.Baker.Params> {
    @Inject
    public Baker(EventListBrick.Baker contentBaker) {
      super(contentBaker);
    }
  }

  @Singleton
  public static class View extends BakerView<EventListBrick.Baker.Params> {
    @Inject
    public View(Baker baker) {
      super(baker);
    }

    @Override
    protected EventListBrick.Baker.Params bakerParamsFrom(ViewRequest request) {
      return EventListBrick.Baker.Params.from(request);
    }
  }
}

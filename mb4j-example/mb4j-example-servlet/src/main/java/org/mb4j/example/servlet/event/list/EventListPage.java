package org.mb4j.example.servlet.event.list;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.example.servlet.master.MasterLayoutPage;

public class EventListPage extends MasterLayoutPage {
  public static final int SHOW_ALL = EventListPanel.Params.SHOW_ALL;
  @Inject
  EventListPanel contentPanel;

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(EventListPage.class);
      bind(EventListPanel.class);
      bind(EventListItemPanel.class);
    }
  }

  public static ViewUrl url() {
    return url(SHOW_ALL);
  }

  public static ViewUrl url(int maxEventCount) {
    return ViewUrl.of(EventListPage.class,
        new EventListPanel.Params(maxEventCount, false).toUrlParams());
  }

  @Override
  protected MustacheBrick bakeContentBrick(ViewRequest request) {
    return contentPanel.bakeBrick(request);
  }
}

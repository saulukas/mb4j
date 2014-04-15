package org.mb4j.servlet.sample.home;

import com.google.inject.Singleton;
import org.mb4j.servlet.sample.event.list.EventListPage;
import org.mb4j.view.ViewRequest;

@Singleton
public class HomeContentPanel {
  public HomeContentPanelBrick bakeBrick(ViewRequest request) {
    HomeContentPanelBrick brick = new HomeContentPanelBrick();
    brick.oneEventUrl = request.stringOf(EventListPage.url(1));
    brick.twoEventsUrl = request.stringOf(EventListPage.url(2));
    brick.allEventsUrl = request.stringOf(EventListPage.url(EventListPage.SHOW_ALL));
    return brick;
  }
}

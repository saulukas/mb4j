package org.mb4j.sample.servlet.home;

import com.google.inject.Singleton;
import org.mb4j.sample.servlet.event.list.EventListPage;
import org.mb4j.controller.ControllerRequest;

@Singleton
public class HomeContentPanel {
  public HomeContentPanelBrick bakeBrick(ControllerRequest request) {
    HomeContentPanelBrick brick = new HomeContentPanelBrick();
    brick.oneEventUrl = request.resolve(EventListPage.url(1));
    brick.twoEventsUrl = request.resolve(EventListPage.url(2));
    brick.allEventsUrl = request.resolve(EventListPage.url(EventListPage.SHOW_ALL));
    return brick;
  }
}

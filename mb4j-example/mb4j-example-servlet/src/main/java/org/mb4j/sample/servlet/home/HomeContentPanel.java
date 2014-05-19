package org.mb4j.sample.servlet.home;

import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.Request;
import org.mb4j.controller.url.ControllerUrl4Response;
import org.mb4j.sample.servlet.event.list.EventListPage;

@Singleton
public class HomeContentPanel {
  public static class View extends MustacheBrick {
    ControllerUrl4Response oneEventUrl;
    ControllerUrl4Response twoEventsUrl;
    ControllerUrl4Response allEventsUrl;
  }

  public View bakeBrick(Request request) {
    View brick = new View();
    brick.oneEventUrl = request.resolve(EventListPage.url(1));
    brick.twoEventsUrl = request.resolve(EventListPage.url(2));
    brick.allEventsUrl = request.resolve(EventListPage.url(EventListPage.SHOW_ALL));
    return brick;
  }
}

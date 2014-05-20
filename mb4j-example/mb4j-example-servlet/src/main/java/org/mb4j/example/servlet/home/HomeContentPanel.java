package org.mb4j.example.servlet.home;

import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.url.ControllerUrl4Response;
import org.mb4j.example.servlet.event.list.EventListPage;

@Singleton
public class HomeContentPanel {
  public static class Brick extends MustacheBrick {
    ControllerUrl4Response oneEventUrl;
    ControllerUrl4Response twoEventsUrl;
    ControllerUrl4Response allEventsUrl;
  }

  public Brick bakeBrick(ViewRequest request) {
    Brick brick = new Brick();
    brick.oneEventUrl = request.resolve(EventListPage.url(1));
    brick.twoEventsUrl = request.resolve(EventListPage.url(2));
    brick.allEventsUrl = request.resolve(EventListPage.url(EventListPage.SHOW_ALL));
    return brick;
  }
}

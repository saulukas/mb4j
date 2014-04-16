package org.mb4j.servlet.sample.home;

import com.google.inject.Singleton;
import org.mb4j.brick.Brick;
import org.mb4j.servlet.sample.event.list.EventListPage;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.baker.ParameterlessViewBrickBaker;

public class HomeContentBrick extends Brick {
  String oneEventUrl;
  String twoEventsUrl;
  String allEventsUrl;

  @Singleton
  public static class Baker implements ParameterlessViewBrickBaker {
    @Override
    public HomeContentBrick bakeBrick(ViewRequest request, Void params) {
      HomeContentBrick brick = new HomeContentBrick();
      brick.oneEventUrl = request.stringOf(EventListPage.url(1));
      brick.twoEventsUrl = request.stringOf(EventListPage.url(2));
      brick.allEventsUrl = request.stringOf(EventListPage.url(EventListPage.SHOW_ALL));
      return brick;
    }
  }
}

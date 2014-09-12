package org.mb4j.example.servlet.home;

import com.google.inject.Singleton;
import org.mb4j.component.Request;
import org.mb4j.example.servlet.event.list.EventListPage;

@Singleton
public class HomeContentPanel {


    public HomeContentPanelBrick bakeBrick(Request request) {
        HomeContentPanelBrick brick = new HomeContentPanelBrick();
        brick.oneEventUrl = request.resolve(EventListPage.url(1));
        brick.twoEventsUrl = request.resolve(EventListPage.url(2));
        brick.allEventsUrl = request.resolve(EventListPage.url(EventListPage.SHOW_ALL));
        return brick;
    }
}

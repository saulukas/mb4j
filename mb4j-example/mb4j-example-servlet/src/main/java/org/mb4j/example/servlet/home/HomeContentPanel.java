package org.mb4j.example.servlet.home;

import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ControllerUrl4Response;
import org.mb4j.component.Request;
import org.mb4j.example.servlet.event.list.EventListPage;

@Singleton
public class HomeContentPanel {

    public static class Brick extends MustacheBrick {

        ControllerUrl4Response oneEventUrl;
        ControllerUrl4Response twoEventsUrl;
        ControllerUrl4Response allEventsUrl;
    }

    public Brick bakeBrick(Request request) {
        Brick brick = new Brick();
        brick.oneEventUrl = request.resolve(EventListPage.url(1));
        brick.twoEventsUrl = request.resolve(EventListPage.url(2));
        brick.allEventsUrl = request.resolve(EventListPage.url(EventListPage.SHOW_ALL));
        return brick;
    }
}

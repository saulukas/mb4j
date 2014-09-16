package org.mb4j.example.servlet.event.list;

import com.google.inject.Inject;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewUrl;
import org.mb4j.component.Request;
import org.mb4j.example.servlet.master.MasterLayoutPage;

public class EventListPage extends MasterLayoutPage {

    public static final int SHOW_ALL = EventListPanelParams.SHOW_ALL;
    @Inject
    EventListPanel contentPanel;

    public static ViewUrl url() {
        return url(SHOW_ALL);
    }

    public static ViewUrl url(int maxEventCount) {
        return ViewUrl.of(EventListPage.class,
                new EventListPanelParams(maxEventCount, false).toUrlParams());
    }

    @Override
    protected MustacheBrick bakeContentBrick(Request request) {
        return contentPanel.bakeBrick(request);
    }
}

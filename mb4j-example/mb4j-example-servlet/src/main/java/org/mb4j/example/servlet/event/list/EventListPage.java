package org.mb4j.example.servlet.event.list;

import com.google.inject.Inject;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ControllerUrl;
import org.mb4j.component.Request;
import org.mb4j.example.servlet.master.MasterLayoutPage;

public class EventListPage extends MasterLayoutPage {

    public static final int SHOW_ALL = EventListPanelParams.SHOW_ALL;
    @Inject
    EventListPanel contentPanel;

    public static ControllerUrl url() {
        return url(SHOW_ALL);
    }

    public static ControllerUrl url(int maxEventCount) {
        return ControllerUrl.of(EventListPage.class,
                new EventListPanelParams(maxEventCount, false).toUrlParams());
    }

    @Override
    protected MustacheBrick bakeContentBrick(Request request) {
        return contentPanel.bakeBrick(request);
    }
}

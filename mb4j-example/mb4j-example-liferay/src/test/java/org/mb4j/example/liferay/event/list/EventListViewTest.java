package org.mb4j.example.liferay.event.list;

import org.junit.Test;
import org.mb4j.brick.MustacheBrick;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import static org.mb4j.example.liferay.LiferaySampleTestApplication.inject;
import static org.mb4j.example.liferay.LiferaySampleTestApplication.requestFor;
import org.mb4j.example.liferay.event.EventPortlet.Views;

public class EventListViewTest {

    private final EventListView page = inject(EventListView.class);

    @Test
    public void renders_with_zero_events() {
        int eventCount = 0;
        MustacheBrick brick = page.bakeBrick(requestFor(Views.class, EventListView.url(eventCount)));
        System.out.println(renderToString4Development(brick));
    }

    @Test
    public void renders_with_one_event() {
        int eventCount = 1;
        MustacheBrick brick = page.bakeBrick(requestFor(Views.class, EventListView.url(eventCount)));
        System.out.println(renderToString4Development(brick));
    }

    @Test
    public void renders_with_all_events() {
        MustacheBrick brick = page.bakeBrick(requestFor(Views.class, EventListView.url()));
        System.out.println(renderToString4Development(brick));
    }
}

package org.mb4j.liferay.sample.event.list;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.liferay.sample.LiferaySampleTestApplication.inject;
import static org.mb4j.renderer.RendererUtils.renderToString4Development;
import org.mb4j.view.ViewResponse;
import static org.mb4j.view.ViewTesting.request4Tests;

public class EventListBrickViewTest {
    private final EventListBrickView view = inject(EventListBrickView.class);

    @Test
    public void renders_with_zero_events() {
        int eventCount = 0;
        ViewResponse response = view.handle(request4Tests(EventListBrickView.url(eventCount)));
        assertThat(response.type, is(ViewResponse.Type.BRICK));
        System.out.println(renderToString4Development(response.brick));
    }

    @Test
    public void renders_with_one_event() {
        int eventCount = 1;
        ViewResponse response = view.handle(request4Tests(EventListBrickView.url(eventCount)));
        assertThat(response.type, is(ViewResponse.Type.BRICK));
        System.out.println(renderToString4Development(response.brick));
    }

    @Test
    public void renders_with_all_events() {
        ViewResponse response = view.handle(request4Tests(EventListBrickView.url()));
        assertThat(response.type, is(ViewResponse.Type.BRICK));
        System.out.println(renderToString4Development(response.brick));
    }
}

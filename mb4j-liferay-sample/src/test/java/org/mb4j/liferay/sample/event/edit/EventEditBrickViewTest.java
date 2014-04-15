package org.mb4j.liferay.sample.event.edit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.liferay.sample.LiferaySampleTestApplication.inject;
import static org.mb4j.liferay.sample.event.TypicalEvents.fishingEventId;
import static org.mb4j.renderer.RendererUtils.renderToString4Development;
import org.mb4j.view.ViewResponse;
import static org.mb4j.view.ViewTesting.request4Tests;
import org.mb4j.view.url.ViewUrl;

public class EventEditBrickViewTest {
    private final EventEditBrickView view = inject(EventEditBrickView.class);

    @Test
    public void renders_fishing_event() {
        ViewUrl url = EventEditBrickView.url(fishingEventId());
        ViewResponse response = view.handle(request4Tests(url));
        assertThat(response.type, is(ViewResponse.Type.BRICK));
        System.out.println(renderToString4Development(response.brick));
    }
}

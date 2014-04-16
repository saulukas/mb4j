package org.mb4j.servlet.sample.event.edit;

import org.mb4j.servlet.sample.event.edit.EventEditPage;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import static org.mb4j.controller.ViewTesting.request4Tests;
import static org.mb4j.servlet.sample.ServletSampleTestApplication.inject;
import static org.mb4j.servlet.sample.event.TypicalEvents.fishingEventId;
import org.mb4j.controller.url.ViewUrl;
import org.mb4j.controller.ViewResponse;

public class EventEditPageTest {
  private final EventEditPage.View view = inject(EventEditPage.View.class);

  @Test
  public void renders_fishing_event() {
    ViewUrl url = EventEditPage.url(fishingEventId());
    ViewResponse response = view.handle(request4Tests(url));
    assertThat(response.type, is(ViewResponse.Type.BRICK));
    System.out.println(renderToString4Development(response.brick));
  }
}

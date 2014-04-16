package org.mb4j.servlet.sample.event.edit;

import org.junit.Test;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import static org.mb4j.controller.ViewTesting.request4Tests;
import static org.mb4j.servlet.sample.ServletSampleTestApplication.inject;
import static org.mb4j.servlet.sample.event.TypicalEvents.fishingEventId;
import org.mb4j.controller.PageResponse;
import org.mb4j.controller.url.ViewUrl;

public class EventEditPageTest {
  private final EventEditPage page = inject(EventEditPage.class);

  @Test
  public void renders_fishing_event() {
    ViewUrl url = EventEditPage.url(fishingEventId());
    PageResponse response = page.handle(request4Tests(url));
    System.out.println(renderToString4Development(response.brick));
  }
}

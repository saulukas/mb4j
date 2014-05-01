package org.mb4j.sample.servlet.event.edit;

import org.junit.Test;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import org.mb4j.controller.page.PageResponse;
import org.mb4j.controller.url.ControllerUrl;
import static org.mb4j.sample.servlet.ServletSampleTestApplication.inject;
import static org.mb4j.sample.servlet.ServletSampleTestApplication.requestFor;
import static org.mb4j.sample.servlet.event.TypicalEvents.fishingEventId;

public class EventEditPageTest {
  private final EventEditPage page = inject(EventEditPage.class);

  @Test
  public void renders_fishing_event() {
    ControllerUrl url = EventEditPage.url(fishingEventId());
    PageResponse response = page.handle(requestFor(url));
    System.out.println(renderToString4Development(response.brick));
    System.out.println("\n\n" + page.componentTreeToString("    ") + "\n");
  }
}

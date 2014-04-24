package org.mb4j.servlet.sample.event.edit;

import org.junit.Test;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import org.mb4j.controller.page.PageResponse;
import static org.mb4j.controller.test.ControllerTesting.request4Tests;
import org.mb4j.controller.url.ControllerUrl;
import static org.mb4j.servlet.sample.ServletSampleTestApplication.inject;
import static org.mb4j.servlet.sample.event.TypicalEvents.fishingEventId;

public class EventEditPageTest {
  private final EventEditPage page = inject(EventEditPage.class);

  @Test
  public void renders_fishing_event() {
    ControllerUrl url = EventEditPage.url(fishingEventId());
    PageResponse response = page.handle(request4Tests(url));
    System.out.println(renderToString4Development(response.brick));
    System.out.println("children=" + page.getChildren());
//    System.out.println("forms=" + page.getForms());
  }
}

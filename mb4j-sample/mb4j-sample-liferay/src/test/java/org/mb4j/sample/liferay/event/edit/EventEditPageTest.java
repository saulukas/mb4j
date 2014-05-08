package org.mb4j.sample.liferay.event.edit;

import org.junit.Test;
import org.mb4j.brick.Brick;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import org.mb4j.controller.url.ControllerUrl;
import static org.mb4j.sample.liferay.LiferaySampleTestApplication.inject;
import static org.mb4j.sample.liferay.LiferaySampleTestApplication.requestFor;
import org.mb4j.sample.liferay.event.EventListPortlet.Pages;
import static org.mb4j.sample.liferay.event.TypicalEvents.fishingEventId;

public class EventEditPageTest {
  private final EventEditPage page = inject(EventEditPage.class);

  @Test
  public void renders_fishing_event() {
    ControllerUrl url = EventEditPage.url(fishingEventId());
    Brick brick = page.bakeBrickFrom(requestFor(Pages.class, url));
    System.out.println(renderToString4Development(brick));
  }
}

package org.mb4j.liferay.sample.event.edit;

import org.junit.Test;
import org.mb4j.brick.Brick;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import static org.mb4j.controller.test.ControllerTesting.request4Tests;
import org.mb4j.controller.url.ControllerUrl;
import static org.mb4j.liferay.sample.LiferaySampleTestApplication.inject;
import static org.mb4j.liferay.sample.event.TypicalEvents.fishingEventId;

public class EventEditPageTest {
  private final EventEditPage panel = inject(EventEditPage.class);

  @Test
  public void renders_fishing_event() {
    ControllerUrl url = EventEditPage.url(fishingEventId());
    Brick brick = panel.bakeBrickFrom(request4Tests(url));
    System.out.println(renderToString4Development(brick));
  }
}

package org.mb4j.sample.liferay.event.edit;

import org.junit.Test;
import org.mb4j.brick.MustacheBrick;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import org.mb4j.controller.url.ControllerUrl;
import static org.mb4j.sample.liferay.LiferaySampleTestApplication.inject;
import static org.mb4j.sample.liferay.LiferaySampleTestApplication.requestFor;
import org.mb4j.sample.liferay.event.EventListPortlet.Views;
import static org.mb4j.sample.liferay.event.TypicalEvents.fishingEventId;

public class EventEditViewTest {
  private final EventEditView page = inject(EventEditView.class);

  @Test
  public void renders_fishing_event() {
    ControllerUrl url = EventEditView.url(fishingEventId());
    MustacheBrick brick = page.bakeBrickFrom(requestFor(Views.class, url));
    System.out.println(renderToString4Development(brick));
  }
}

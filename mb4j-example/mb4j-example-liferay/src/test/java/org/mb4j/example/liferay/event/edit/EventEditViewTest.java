package org.mb4j.example.liferay.event.edit;

import org.junit.Test;
import org.mb4j.brick.MustacheBrick;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import org.mb4j.component.ControllerUrl;
import static org.mb4j.example.liferay.LiferaySampleTestApplication.inject;
import static org.mb4j.example.liferay.LiferaySampleTestApplication.requestFor;
import org.mb4j.example.liferay.event.EventPortlet.Views;
import static org.mb4j.example.liferay.event.TypicalEvents.fishingEventId;

public class EventEditViewTest {
  private final EventEditView page = inject(EventEditView.class);

  @Test
  public void renders_fishing_event() {
    ControllerUrl url = EventEditView.url(fishingEventId());
    MustacheBrick brick = page.bakeBrick(requestFor(Views.class, url));
    System.out.println(renderToString4Development(brick));
  }
}

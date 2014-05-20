package org.mb4j.example.liferay.event.edit;

import org.mb4j.example.liferay.event.edit.EventEditView;
import org.junit.Test;
import org.mb4j.brick.MustacheBrick;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import org.mb4j.component.view.ViewUrl;
import static org.mb4j.example.liferay.LiferaySampleTestApplication.inject;
import static org.mb4j.example.liferay.LiferaySampleTestApplication.requestFor;
import org.mb4j.example.liferay.event.EventListPortlet.Views;
import static org.mb4j.example.liferay.event.TypicalEvents.fishingEventId;

public class EventEditViewTest {
  private final EventEditView page = inject(EventEditView.class);

  @Test
  public void renders_fishing_event() {
    ViewUrl url = EventEditView.url(fishingEventId());
    MustacheBrick brick = page.bakeBrickFrom(requestFor(Views.class, url));
    System.out.println(renderToString4Development(brick));
  }
}

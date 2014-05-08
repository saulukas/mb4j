package org.mb4j.sample.liferay.event.list;

import org.junit.Test;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import org.mb4j.controller.page.PageResponse;
import static org.mb4j.sample.liferay.LiferaySampleTestApplication.inject;
import static org.mb4j.sample.liferay.LiferaySampleTestApplication.requestFor;
import org.mb4j.sample.liferay.event.EventListPortlet.Pages;

public class EventListPageTest {
  private final EventListPage page = inject(EventListPage.class);

  @Test
  public void renders_with_zero_events() {
    int eventCount = 0;
    PageResponse response = page.handle(requestFor(Pages.class, EventListPage.url(eventCount)));
    System.out.println(renderToString4Development(response.brick));
  }

  @Test
  public void renders_with_one_event() {
    int eventCount = 1;
    PageResponse response = page.handle(requestFor(Pages.class, EventListPage.url(eventCount)));
    System.out.println(renderToString4Development(response.brick));
  }

  @Test
  public void renders_with_all_events() {
    PageResponse response = page.handle(requestFor(Pages.class, EventListPage.url()));
    System.out.println(renderToString4Development(response.brick));
  }
}

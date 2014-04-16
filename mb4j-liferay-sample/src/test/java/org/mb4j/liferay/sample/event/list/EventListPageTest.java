package org.mb4j.liferay.sample.event.list;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import org.mb4j.controller.ViewResponse;
import static org.mb4j.controller.ViewTesting.request4Tests;
import static org.mb4j.liferay.sample.LiferaySampleTestApplication.inject;

public class EventListPageTest {
  private final EventListPage page = inject(EventListPage.class);

  @Test
  public void renders_with_zero_events() {
    int eventCount = 0;
    ViewResponse response = page.handle(request4Tests(EventListPage.url(eventCount)));
    assertThat(response.type, is(ViewResponse.Type.BRICK));
    System.out.println(renderToString4Development(response.brick));
  }

  @Test
  public void renders_with_one_event() {
    int eventCount = 1;
    ViewResponse response = page.handle(request4Tests(EventListPage.url(eventCount)));
    assertThat(response.type, is(ViewResponse.Type.BRICK));
    System.out.println(renderToString4Development(response.brick));
  }

  @Test
  public void renders_with_all_events() {
    ViewResponse response = page.handle(request4Tests(EventListPage.url()));
    assertThat(response.type, is(ViewResponse.Type.BRICK));
    System.out.println(renderToString4Development(response.brick));
  }
}

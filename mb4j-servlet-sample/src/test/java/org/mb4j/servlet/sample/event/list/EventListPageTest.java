package org.mb4j.servlet.sample.event.list;

import org.mb4j.servlet.sample.event.list.EventListPage;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.renderer.RendererUtils.renderToString4Development;
import static org.mb4j.view.ViewTesting.request4Tests;
import static org.mb4j.servlet.sample.ServletSampleTestApplication.inject;
import org.mb4j.view.ViewResponse;

public class EventListPageTest {
  private final EventListPage.View view = inject(EventListPage.View.class);

  @Test
  public void renders_with_zero_events() {
    int eventCount = 0;
    ViewResponse response = view.handle(request4Tests(EventListPage.url(eventCount)));
    assertThat(response.type, is(ViewResponse.Type.BRICK));
    System.out.println(renderToString4Development(response.brick));
  }

  @Test
  public void renders_with_one_event() {
    int eventCount = 1;
    ViewResponse response = view.handle(request4Tests(EventListPage.url(eventCount)));
    assertThat(response.type, is(ViewResponse.Type.BRICK));
    System.out.println(renderToString4Development(response.brick));
  }

  @Test
  public void renders_with_all_events() {
    ViewResponse response = view.handle(request4Tests(EventListPage.url()));
    assertThat(response.type, is(ViewResponse.Type.BRICK));
    System.out.println(renderToString4Development(response.brick));
  }
}

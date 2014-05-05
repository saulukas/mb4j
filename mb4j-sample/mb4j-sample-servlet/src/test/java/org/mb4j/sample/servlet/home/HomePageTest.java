package org.mb4j.sample.servlet.home;

import org.junit.Test;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import org.mb4j.controller.page.PageResponse;
import static org.mb4j.sample.servlet.ServletSampleTestApplication.inject;
import static org.mb4j.sample.servlet.ServletSampleTestApplication.requestFor;

public class HomePageTest {
  private final HomePage page = inject(HomePage.class);

  @Test
  public void renders_successfully() {
    PageResponse response = page.handle(requestFor(HomePage.url()));
    System.out.println(renderToString4Development(response.brick));
  }
}

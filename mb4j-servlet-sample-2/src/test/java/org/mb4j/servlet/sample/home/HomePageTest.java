package org.mb4j.servlet.sample.home;

import org.junit.Test;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import static org.mb4j.controller.ViewTesting.request4Tests;
import static org.mb4j.servlet.sample.ServletSampleTestApplication.inject;
import org.mb4j.controller.PageResponse;

public class HomePageTest {
  private final HomePage page = inject(HomePage.class);

  @Test
  public void renders_successfully() {
    PageResponse response = page.handle(request4Tests(HomePage.url()));
    System.out.println(renderToString4Development(response.brick));
  }
}

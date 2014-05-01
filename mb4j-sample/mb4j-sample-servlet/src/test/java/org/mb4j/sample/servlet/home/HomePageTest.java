package org.mb4j.sample.servlet.home;

import org.junit.Test;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import static org.mb4j.controller.test.ControllerTesting.request4Tests;
import static org.mb4j.sample.servlet.ServletSampleTestApplication.inject;
import org.mb4j.controller.page.PageResponse;

public class HomePageTest {
  private final HomePage page = inject(HomePage.class);

  @Test
  public void renders_successfully() {
    PageResponse response = page.handle(request4Tests(HomePage.url()));
    System.out.println(renderToString4Development(response.brick));
  }
}

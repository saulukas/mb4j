package org.mb4j.servlet.sample.home;

import org.junit.Test;
import static org.mb4j.renderer.RendererUtils.renderToString4Development;
import static org.mb4j.view.ViewTesting.request4Tests;
import static org.mb4j.servlet.sample.ServletSampleTestApplication.inject;
import org.mb4j.view.PageResponse;

public class HomePageTest {
  private final HomePage page = inject(HomePage.class);

  @Test
  public void renders_successfully() {
    PageResponse response = page.handle(request4Tests(HomePage.url()));
    System.out.println(renderToString4Development(response.brick));
  }
}

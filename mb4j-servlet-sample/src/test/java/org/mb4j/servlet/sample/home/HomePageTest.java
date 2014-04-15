package org.mb4j.servlet.sample.home;

import org.mb4j.servlet.sample.home.HomePage;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.renderer.RendererUtils.renderToString4Development;
import static org.mb4j.view.ViewTesting.request4Tests;
import static org.mb4j.servlet.sample.ServletSampleTestApplication.inject;
import org.mb4j.view.ViewResponse;

public class HomePageTest {
  private final HomePage.View view = inject(HomePage.View.class);

  @Test
  public void renders_successfully() {
    ViewResponse response = view.handle(request4Tests(HomePage.url()));
    assertThat(response.type, is(ViewResponse.Type.BRICK));
    System.out.println(renderToString4Development(response.brick));
  }
}

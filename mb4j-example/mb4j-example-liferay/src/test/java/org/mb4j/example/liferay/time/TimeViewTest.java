package org.mb4j.example.liferay.time;

import org.junit.Test;
import org.mb4j.brick.MustacheBrick;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import org.mb4j.component.view.ViewUrl;
import static org.mb4j.example.liferay.LiferaySampleTestApplication.inject;
import static org.mb4j.example.liferay.LiferaySampleTestApplication.singleViewRequestFor;

public class TimeViewTest {
  private final TimeView view = inject(TimeView.class);

  @Test
  public void renders_successfully_offer_wtih_text() {
    MustacheBrick brick = view.bakeBrickFrom(singleViewRequestFor(ViewUrl.of(TimeView.class)));
    System.out.println(renderToString4Development(brick));
  }
}

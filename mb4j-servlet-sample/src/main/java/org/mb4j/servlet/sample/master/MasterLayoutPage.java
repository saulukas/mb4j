package org.mb4j.servlet.sample.master;

import com.google.inject.Singleton;
import org.mb4j.Brick;
import org.mb4j.bricks.RawBrick;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.baker.ViewBrickBaker;

public class MasterLayoutPage extends Brick {
  Brick header;
  Brick content;
  Brick footer;
  String title = "Events";
  String dummy_js;

  @Singleton
  protected static class Baker<P> implements ViewBrickBaker<P> {
    final DefaultHeaderBrick.Baker headerBaker = new DefaultHeaderBrick.Baker();
    final ViewBrickBaker<P> contentBaker;

    protected Baker(ViewBrickBaker<P> contentBaker) {
      this.contentBaker = contentBaker;
    }

    @Override
    public MasterLayoutPage bakeBrick(ViewRequest request, P params) {
      MasterLayoutPage brick = new MasterLayoutPage();
      brick.header = headerBaker.bakeBrick(request, DefaultHeaderBrick.Baker.Params.from(request));
      brick.content = contentBaker.bakeBrick(request, params);
      brick.footer = new RawBrick("default FOOTER");
      brick.dummy_js = request.staticUrl("js/dummy.js");
      return brick;
    }
  }
}

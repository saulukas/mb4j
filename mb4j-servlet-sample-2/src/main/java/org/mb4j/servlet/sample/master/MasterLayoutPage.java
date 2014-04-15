package org.mb4j.servlet.sample.master;

import com.google.inject.Singleton;
import org.mb4j.Brick;
import org.mb4j.bricks.RawBrick;
import org.mb4j.view.Page;
import org.mb4j.view.PageResponse;
import org.mb4j.view.ViewRequest;

@Singleton
public abstract class MasterLayoutPage implements Page {
  final DefaultHeaderPanel headerPanel = new DefaultHeaderPanel();

  @Override
  public PageResponse handle(ViewRequest request) {
    return new PageResponse(bakeBrick(request));
  }

  private MasterLayoutPageBrick bakeBrick(ViewRequest request) {
    MasterLayoutPageBrick brick = new MasterLayoutPageBrick();
    brick.header = headerPanel.brickFrom(request);
    brick.content = bakeContentBrick(request);
    brick.footer = new RawBrick("default FOOTER");
    brick.dummy_js = request.staticUrl("js/dummy.js");
    return brick;
  }

  protected abstract Brick bakeContentBrick(ViewRequest request);
}

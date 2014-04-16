package org.mb4j.servlet.sample.master;

import org.mb4j.servlet.sample.offer.PersonalOfferPanel;
import com.google.inject.Singleton;
import org.mb4j.brick.Brick;
import org.mb4j.brick.RawBrick;
import org.mb4j.controller.Page;
import org.mb4j.controller.PageResponse;
import org.mb4j.controller.ViewRequest;

@Singleton
public abstract class MasterLayoutPage implements Page {
  final PersonalOfferPanel headerPanel = new PersonalOfferPanel();

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

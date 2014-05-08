package org.mb4j.sample.servlet.master;

import com.google.inject.Singleton;
import org.mb4j.brick.Brick;
import org.mb4j.brick.RawBrick;
import org.mb4j.controller.page.BrickBakerPage;
import org.mb4j.controller.Request;
import org.mb4j.sample.servlet.offer.PersonalOfferPanel;

@Singleton
public abstract class MasterLayoutPage extends BrickBakerPage {
  final PersonalOfferPanel headerPanel = new PersonalOfferPanel();

  @Override
  public Brick bakeBrickFrom(Request request) {
    MasterLayoutPageBrick brick = new MasterLayoutPageBrick();
    brick.header = headerPanel.bakeBrickFrom(request);
    brick.content = bakeContentBrick(request);
    brick.footer = new RawBrick("default FOOTER");
    brick.dummy_js = request.resolveUrl("js/dummy.js");
    return brick;
  }

  protected abstract Brick bakeContentBrick(Request request);
}

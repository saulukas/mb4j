package org.mb4j.sample.servlet.master;

import com.google.inject.Singleton;
import org.mb4j.brick.Brick;
import org.mb4j.controller.Request;
import org.mb4j.controller.page.Page;
import org.mb4j.controller.url.AssetUrl4Response;
import org.mb4j.sample.servlet.offer.PersonalOfferPanel;

@Singleton
public abstract class MasterLayoutPage extends Page {
  final PersonalOfferPanel headerPanel = new PersonalOfferPanel();
  final FooterPanel footerPanel = FooterPanel.INSTANCE;

  public static class View extends Brick {
    Brick header;
    Brick content;
    Brick footer;
    String title = "Events";
    AssetUrl4Response jquery_js;
  }

  @Override
  public Brick bakeBrickFrom(Request request) {
    View brick = new View();
    brick.header = headerPanel.bakeBrickFrom(request);
    brick.content = bakeContentBrick(request);
    brick.footer = footerPanel.bakeBrick(request);
    brick.jquery_js = request.resolveUrl("js/jquery.js");
    return brick;
  }

  protected abstract Brick bakeContentBrick(Request request);
}

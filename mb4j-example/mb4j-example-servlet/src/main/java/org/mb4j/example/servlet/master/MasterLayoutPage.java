package org.mb4j.example.servlet.master;

import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.Request;
import org.mb4j.servlet.Page;
import org.mb4j.component.asset.AssetUrl4Response;
import org.mb4j.example.servlet.offer.PersonalOfferPanel;

@Singleton
public abstract class MasterLayoutPage extends Page {
  final PersonalOfferPanel headerPanel = new PersonalOfferPanel();
  final FooterPanel footerPanel = FooterPanel.INSTANCE;

  public static class Brick extends MustacheBrick {
    MustacheBrick header;
    MustacheBrick content;
    MustacheBrick footer;
    String title = "Events";
    AssetUrl4Response jquery_js;
  }

  @Override
  public MustacheBrick bakeBrick(Request request) {
    Brick brick = new Brick();
    brick.header = headerPanel.bakeBrick(request);
    brick.content = bakeContentBrick(request);
    brick.footer = footerPanel.bakeBrick(request);
    brick.jquery_js = request.resolveUrl("js/jquery.js");
    return brick;
  }

  protected abstract MustacheBrick bakeContentBrick(Request request);
}

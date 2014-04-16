package org.mb4j.servlet.sample.master;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.url.ViewUrl;

@Singleton
public class DefaultHeaderPanel {
  public DefaultHeaderPanelBrick brickFrom(ViewRequest request) {
    return brickFrom(request, Params.from(request));
  }

  static class Params {
    static String OFFER_TEXT = "personalOffer";
    String offerText;

    public static Params from(ViewRequest request) {
      Params params = new Params();
      params.offerText = request.url.params.named.valueOf(OFFER_TEXT);
      return params;
    }

    boolean isOfferTextEmpty() {
      return Strings.isNullOrEmpty(offerText);
    }
  }

  private DefaultHeaderPanelBrick brickFrom(ViewRequest request, Params params) {
    DefaultHeaderPanelBrick brick = new DefaultHeaderPanelBrick();
    brick.offerVisible = !params.isOfferTextEmpty();
    brick.offerText = params.offerText;
    brick.offerLinkText = (brick.offerVisible ? "Hide personal offer" : "Show personal offer");
    String newOffer = brick.offerVisible ? "" : "Consider going to fishing event!";
    brick.toggleOfferUrl = request.stringOf(initTogglePersonalOfferUrl(request, newOffer));
    return brick;
  }

  private ViewUrl initTogglePersonalOfferUrl(ViewRequest request, String newOffer) {
    return Strings.isNullOrEmpty(newOffer)
        ? request.url.withDeletedParam(Params.OFFER_TEXT)
        : request.url.withReplacedParam(Params.OFFER_TEXT, newOffer);
  }
}

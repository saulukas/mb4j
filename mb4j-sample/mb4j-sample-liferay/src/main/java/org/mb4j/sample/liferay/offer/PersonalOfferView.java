package org.mb4j.sample.liferay.offer;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.controller.Request;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.liferay.PortletView;

@Singleton
public class PersonalOfferView extends PortletView {
  @Override
  public PersonalOfferViewBrick bakeBrickFrom(Request request) {
    return brickFrom(request, Params.from(request));
  }

  static class Params {
    static String OFFER_TEXT = "personalOffer";
    String offerText;

    public static Params from(Request request) {
      Params params = new Params();
      params.offerText = request.url().params.named.valueOrNullOf(OFFER_TEXT);
      return params;
    }

    boolean isOfferTextEmpty() {
      return Strings.isNullOrEmpty(offerText);
    }
  }

  private PersonalOfferViewBrick brickFrom(Request request, Params params) {
    PersonalOfferViewBrick brick = new PersonalOfferViewBrick();
    brick.offerVisible = !params.isOfferTextEmpty();
    brick.offerText = params.offerText;
    brick.offerLinkText = (brick.offerVisible ? "Hide personal offer" : "Show personal offer");
    String newOffer = brick.offerVisible ? "" : "Consider going to fishing event!";
    brick.toggleOfferUrl = request.resolve(initTogglePersonalOfferUrl(request, newOffer));
    return brick;
  }

  private ControllerUrl initTogglePersonalOfferUrl(Request request, String newOffer) {
    return Strings.isNullOrEmpty(newOffer)
        ? request.url().withDeletedParam(Params.OFFER_TEXT)
        : request.url().withReplacedParam(Params.OFFER_TEXT, newOffer);
  }
}

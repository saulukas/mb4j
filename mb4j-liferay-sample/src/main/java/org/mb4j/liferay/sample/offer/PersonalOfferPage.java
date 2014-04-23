package org.mb4j.liferay.sample.offer;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.controller.page.BrickBakerPage;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.url.ControllerUrl;

@Singleton
public class PersonalOfferPage extends BrickBakerPage {
  @Override
  public PersonalOfferPageBrick bakeBrickFrom(ControllerRequest request) {
    return brickFrom(request, Params.from(request));
  }

  static class Params {
    static String OFFER_TEXT = "personalOffer";
    String offerText;

    public static Params from(ControllerRequest request) {
      Params params = new Params();
      params.offerText = request.url().params.named.valueOf(OFFER_TEXT);
      return params;
    }

    boolean isOfferTextEmpty() {
      return Strings.isNullOrEmpty(offerText);
    }
  }

  private PersonalOfferPageBrick brickFrom(ControllerRequest request, Params params) {
    PersonalOfferPageBrick brick = new PersonalOfferPageBrick();
    brick.offerVisible = !params.isOfferTextEmpty();
    brick.offerText = params.offerText;
    brick.offerLinkText = (brick.offerVisible ? "Hide personal offer" : "Show personal offer");
    String newOffer = brick.offerVisible ? "" : "Consider going to fishing event!";
    brick.toggleOfferUrl = request.resolve(initTogglePersonalOfferUrl(request, newOffer));
    return brick;
  }

  private ControllerUrl initTogglePersonalOfferUrl(ControllerRequest request, String newOffer) {
    return Strings.isNullOrEmpty(newOffer)
        ? request.url().withDeletedParam(Params.OFFER_TEXT)
        : request.url().withReplacedParam(Params.OFFER_TEXT, newOffer);
  }
}

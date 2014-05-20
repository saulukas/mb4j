package org.mb4j.example.liferay.offer;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.url.ControllerUrl;
import org.mb4j.liferay.PortletView;

@Singleton
public class PersonalOfferView extends PortletView {
  @Override
  public PersonalOfferViewBrick bakeBrickFrom(ViewRequest request) {
    return brickFrom(request, Params.from(request));
  }

  static class Params {
    static String OFFER_TEXT = "personalOffer";
    String offerText;

    public static Params from(ViewRequest request) {
      Params params = new Params();
      params.offerText = request.url().params.named.valueOrNullOf(OFFER_TEXT);
      return params;
    }

    boolean isOfferTextEmpty() {
      return Strings.isNullOrEmpty(offerText);
    }
  }

  private PersonalOfferViewBrick brickFrom(ViewRequest request, Params params) {
    PersonalOfferViewBrick brick = new PersonalOfferViewBrick();
    brick.offerVisible = !params.isOfferTextEmpty();
    brick.offerText = params.offerText;
    brick.offerLinkText = (brick.offerVisible ? "Hide personal offer" : "Show personal offer");
    String newOffer = brick.offerVisible ? "" : "Consider going to fishing event!";
    brick.toggleOfferUrl = request.resolve(initTogglePersonalOfferUrl(request, newOffer));
    return brick;
  }

  private ControllerUrl initTogglePersonalOfferUrl(ViewRequest request, String newOffer) {
    return Strings.isNullOrEmpty(newOffer)
        ? request.url().withDeletedParam(Params.OFFER_TEXT)
        : request.url().withReplacedParam(Params.OFFER_TEXT, newOffer);
  }
}

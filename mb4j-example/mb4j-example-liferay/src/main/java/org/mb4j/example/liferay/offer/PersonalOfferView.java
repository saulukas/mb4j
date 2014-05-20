package org.mb4j.example.liferay.offer;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.component.view.ViewUrl4Response;
import org.mb4j.liferay.PortletView;

@Singleton
public class PersonalOfferView extends PortletView {
  @Override
  public MustacheBrick bakeBrickFrom(ViewRequest request) {
    return brickFrom(request, Params.from(request));
  }

  static class Params {
    static String OFFER_TEXT = "personalOffer";
    final String offerText;

    public Params(String offerText) {
      this.offerText = offerText;
    }

    public static Params from(ViewRequest request) {
      return new Params(request.url().params.named.valueOrNullOf(OFFER_TEXT));
    }

    boolean isOfferTextEmpty() {
      return Strings.isNullOrEmpty(offerText);
    }
  }

  static class Brick extends MustacheBrick {
    boolean offerVisible = false;
    String offerText;
    String offerLinkText;
    ViewUrl4Response toggleOfferUrl;
  }

  MustacheBrick brickFrom(ViewRequest request, Params params) {
    Brick brick = new Brick();
    brick.offerVisible = !params.isOfferTextEmpty();
    brick.offerText = params.offerText;
    brick.offerLinkText = brick.offerVisible ? "Hide personal offer" : "Show personal offer";
    String newOffer = brick.offerVisible ? "" : "Consider going to fishing event!";
    brick.toggleOfferUrl = request.resolve(togglePersonalOfferUrl(request, newOffer));
    return brick;
  }

  private ViewUrl togglePersonalOfferUrl(ViewRequest request, String newOffer) {
    return Strings.isNullOrEmpty(newOffer)
        ? request.url().withDeletedParam(Params.OFFER_TEXT)
        : request.url().withReplacedParam(Params.OFFER_TEXT, newOffer);
  }
}

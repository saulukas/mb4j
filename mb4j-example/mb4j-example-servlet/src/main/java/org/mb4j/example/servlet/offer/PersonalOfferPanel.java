package org.mb4j.example.servlet.offer;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.Component;
import org.mb4j.component.view.BrickBaker;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.component.view.ViewUrl4Response;

@Singleton
public class PersonalOfferPanel extends Component implements BrickBaker {
  static class Params {
    static String OFFER_TEXT = "personalOffer";
    String offerText;
  }

  public static class Brick extends MustacheBrick {
    boolean offerVisible = false;
    String offerText;
    String offerLinkText;
    ViewUrl4Response toggleOfferUrl;
  }

  @Override
  public Brick bakeBrickFrom(ViewRequest request) {
    Params params = paramsFrom(request);
    Brick brick = new Brick();
    brick.offerVisible = !isOfferTextEmpty(params);
    brick.offerText = params.offerText;
    brick.offerLinkText = (brick.offerVisible ? "Hide personal offer" : "Show personal offer");
    String newOffer = brick.offerVisible ? "" : "Consider going to fishing event!";
    brick.toggleOfferUrl = request.resolve(initTogglePersonalOfferUrl(request, newOffer));
    return brick;
  }

  static Params paramsFrom(ViewRequest request) {
    Params params = new Params();
    params.offerText = request.url().params.named.valueOrNullOf(Params.OFFER_TEXT);
    return params;
  }

  static boolean isOfferTextEmpty(Params params) {
    return Strings.isNullOrEmpty(params.offerText);
  }

  private ViewUrl initTogglePersonalOfferUrl(ViewRequest request, String newOffer) {
    return Strings.isNullOrEmpty(newOffer)
        ? request.url().withDeletedParam(Params.OFFER_TEXT)
        : request.url().withReplacedParam(Params.OFFER_TEXT, newOffer);
  }
}

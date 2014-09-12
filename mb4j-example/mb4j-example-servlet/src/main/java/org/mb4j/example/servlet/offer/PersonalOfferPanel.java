package org.mb4j.example.servlet.offer;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ComponentUsingReflection;
import org.mb4j.component.BrickBaker;
import org.mb4j.component.Request;
import org.mb4j.component.ControllerUrl;
import org.mb4j.component.ControllerUrl4Response;

@Singleton
public class PersonalOfferPanel extends ComponentUsingReflection implements BrickBaker {
  static class Params {
    static String OFFER_TEXT = "personalOffer";
    String offerText;
  }

  public static class Brick extends MustacheBrick {
    boolean offerVisible = false;
    String offerText;
    String offerLinkText;
    ControllerUrl4Response toggleOfferUrl;
  }

  @Override
  public Brick bakeBrick(Request request) {
    Params params = paramsFrom(request);
    Brick brick = new Brick();
    brick.offerVisible = !isOfferTextEmpty(params);
    brick.offerText = params.offerText;
    brick.offerLinkText = (brick.offerVisible ? "Hide personal offer" : "Show personal offer");
    String newOffer = brick.offerVisible ? "" : "Consider going to fishing event!";
    brick.toggleOfferUrl = request.resolve(initTogglePersonalOfferUrl(request, newOffer));
    return brick;
  }

  static Params paramsFrom(Request request) {
    Params params = new Params();
    params.offerText = request.viewUrl().params.named.valueOrNullOf(Params.OFFER_TEXT);
    return params;
  }

  static boolean isOfferTextEmpty(Params params) {
    return Strings.isNullOrEmpty(params.offerText);
  }

  private ControllerUrl initTogglePersonalOfferUrl(Request request, String newOffer) {
    return Strings.isNullOrEmpty(newOffer)
        ? request.viewUrl().withDeletedParam(Params.OFFER_TEXT)
        : request.viewUrl().withReplacedParam(Params.OFFER_TEXT, newOffer);
  }
}

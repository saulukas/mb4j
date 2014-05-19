package org.mb4j.sample.servlet.offer;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.Request;
import org.mb4j.controller.page.BrickBaker;
import org.mb4j.controller.page.Panel;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Response;

@Singleton
public class PersonalOfferPanel extends Panel implements BrickBaker {
  static class Params {
    static String OFFER_TEXT = "personalOffer";
    String offerText;
  }

  public static class View extends MustacheBrick {
    boolean offerVisible = false;
    String offerText;
    String offerLinkText;
    ControllerUrl4Response toggleOfferUrl;
  }

  @Override
  public View bakeBrickFrom(Request request) {
    Params params = paramsFrom(request);
    View brick = new View();
    brick.offerVisible = !isOfferTextEmpty(params);
    brick.offerText = params.offerText;
    brick.offerLinkText = (brick.offerVisible ? "Hide personal offer" : "Show personal offer");
    String newOffer = brick.offerVisible ? "" : "Consider going to fishing event!";
    brick.toggleOfferUrl = request.resolve(initTogglePersonalOfferUrl(request, newOffer));
    return brick;
  }

  static Params paramsFrom(Request request) {
    Params params = new Params();
    params.offerText = request.url().params.named.valueOrNullOf(Params.OFFER_TEXT);
    return params;
  }

  static boolean isOfferTextEmpty(Params params) {
    return Strings.isNullOrEmpty(params.offerText);
  }

  private ControllerUrl initTogglePersonalOfferUrl(Request request, String newOffer) {
    return Strings.isNullOrEmpty(newOffer)
        ? request.url().withDeletedParam(Params.OFFER_TEXT)
        : request.url().withReplacedParam(Params.OFFER_TEXT, newOffer);
  }
}

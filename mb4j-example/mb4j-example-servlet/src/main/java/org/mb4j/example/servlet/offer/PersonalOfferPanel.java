package org.mb4j.example.servlet.offer;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.component.BrickBaker;
import org.mb4j.component.ReflectiveComponent;
import org.mb4j.component.ViewUrl;
import org.mb4j.component.Request;

@Singleton
public class PersonalOfferPanel extends ReflectiveComponent implements BrickBaker {

    @Override
    public PersonalOfferPanelBrick bakeBrick(Request request) {
        PersonalOfferPanelParams params = paramsFrom(request);
        PersonalOfferPanelBrick brick = new PersonalOfferPanelBrick();
        brick.offerVisible = !isOfferTextEmpty(params);
        brick.offerText = params.offerText;
        brick.offerLinkText = (brick.offerVisible ? "Hide personal offer" : "Show personal offer");
        String newOffer = brick.offerVisible ? "" : "Consider going to fishing event!";
        brick.toggleOfferUrl = request.resolve(initTogglePersonalOfferUrl(request, newOffer));
        return brick;
    }

    static PersonalOfferPanelParams paramsFrom(Request request) {
        PersonalOfferPanelParams params = new PersonalOfferPanelParams();
        params.offerText = request.viewUrl().params.named.valueOrNullOf(PersonalOfferPanelParams.OFFER_TEXT);
        return params;
    }

    static boolean isOfferTextEmpty(PersonalOfferPanelParams params) {
        return Strings.isNullOrEmpty(params.offerText);
    }

    private ViewUrl initTogglePersonalOfferUrl(Request request, String newOffer) {
        return Strings.isNullOrEmpty(newOffer)
                ? request.viewUrl().withDeletedParam(PersonalOfferPanelParams.OFFER_TEXT)
                : request.viewUrl().withReplacedParam(PersonalOfferPanelParams.OFFER_TEXT, newOffer);
    }
}

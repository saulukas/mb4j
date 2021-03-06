package org.mb4j.example.liferay.offer;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewLocator;
import org.mb4j.component.ViewUrl;
import org.mb4j.component.Request;
import org.mb4j.liferay.PortletView;

@Singleton
public class PersonalOfferView extends PortletView {

    public static ViewLocator url(String offerText) {
        return new Params(offerText).toUrl();
    }

    static class Brick extends MustacheBrick {

        boolean offerVisible = false;
        String offerText;
        String offerLinkText;
        ViewUrl toggleOfferUrl;
    }

    @Override
    public MustacheBrick bakeBrick(Request request) {
        Params params = Params.from(request);
        Brick brick = new Brick();
        brick.offerVisible = !params.isOfferTextEmpty();
        brick.offerText = params.offerText;
        brick.offerLinkText = brick.offerVisible ? "Hide personal offer" : "Show personal offer";
        Params newParams = new Params(brick.offerVisible ? "" : "Consider going to fishing event!");
        brick.toggleOfferUrl = request.resolve(newParams.urlMergedWith(request.viewLocator()));
        return brick;
    }

    static class Params {

        static String OFFER_TEXT = "personalOffer";
        final String offerText;

        public Params(String offerText) {
            this.offerText = offerText;
        }

        public static Params from(Request request) {
            return new Params(request.viewLocator().params.named.valueOrNullOf(OFFER_TEXT));
        }

        boolean isOfferTextEmpty() {
            return Strings.isNullOrEmpty(offerText);
        }

        ViewLocator toUrl() {
            return urlMergedWith(ViewLocator.of(PersonalOfferView.class));
        }

        ViewLocator urlMergedWith(ViewLocator currentUrl) {
            return isOfferTextEmpty()
                    ? currentUrl.withDeletedParam(OFFER_TEXT)
                    : currentUrl.withReplacedParam(OFFER_TEXT, offerText);
        }
    }
}

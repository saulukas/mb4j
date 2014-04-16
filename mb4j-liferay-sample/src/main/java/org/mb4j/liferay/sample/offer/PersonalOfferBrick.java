package org.mb4j.liferay.sample.offer;

import com.google.common.base.Strings;
import com.google.inject.Singleton;
import org.mb4j.brick.Brick;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.baker.ViewBrickBaker;
import org.mb4j.controller.url.ViewUrl;

public class PersonalOfferBrick extends Brick {
    boolean offerVisible = false;
    String offerText;
    String offerLinkText;
    String toggleOfferUrl;

    @Singleton
    public static class Baker implements ViewBrickBaker<Baker.Params> {
        @Override
        public PersonalOfferBrick bakeBrick(ViewRequest request, Params params) {
            PersonalOfferBrick brick = new PersonalOfferBrick();
            brick.offerVisible = !params.isOfferTextEmpty();
            brick.offerText = params.offerText;
            brick.offerLinkText = (brick.offerVisible
                    ? "Hide personal offer"
                    : "Show personal offer");
            String newOffer = (brick.offerVisible
                    ? ""
                    : "Consider going to fishing event!");
            brick.toggleOfferUrl = request.stringOf(initTogglePersonalOfferUrl(request, newOffer));
            return brick;
        }

        private ViewUrl initTogglePersonalOfferUrl(ViewRequest request, String newOffer) {
            return Strings.isNullOrEmpty(newOffer)
                    ? request.url.withDeletedParam(Params.OFFER_TEXT_PARAM)
                    : request.url.withReplacedParam(Params.OFFER_TEXT_PARAM, newOffer);
        }

        public static class Params {
            static String OFFER_TEXT_PARAM = "personalOffer";
            final String offerText;

            public Params(String offerText) {
                this.offerText = offerText;
            }

            boolean isOfferTextEmpty() {
                return Strings.isNullOrEmpty(offerText);
            }

            public static Params from(ViewRequest request) {
                return new Params(request.url.params.named.valueOf(OFFER_TEXT_PARAM));
            }
        }
    }
}

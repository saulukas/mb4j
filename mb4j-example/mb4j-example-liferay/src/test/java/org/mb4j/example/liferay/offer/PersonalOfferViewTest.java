package org.mb4j.example.liferay.offer;

import org.junit.Test;
import org.mb4j.brick.MustacheBrick;
import static org.mb4j.brick.renderer.RendererUtils.renderToString4Development;
import static org.mb4j.example.liferay.LiferaySampleTestApplication.inject;
import static org.mb4j.example.liferay.LiferaySampleTestApplication.singleViewRequestFor;

public class PersonalOfferViewTest {

    private final PersonalOfferView view = inject(PersonalOfferView.class);

    @Test
    public void renders_successfully_offer_wtih_text() {
        String offer = "nice offer";
        MustacheBrick brick = view.bakeBrick(singleViewRequestFor(PersonalOfferView.url(offer)));
        System.out.println(renderToString4Development(brick));
    }

    @Test
    public void renders_successfully_empty_offer() {
        String offer = "";
        MustacheBrick brick = view.bakeBrick(singleViewRequestFor(PersonalOfferView.url(offer)));
        System.out.println(renderToString4Development(brick));
    }
}

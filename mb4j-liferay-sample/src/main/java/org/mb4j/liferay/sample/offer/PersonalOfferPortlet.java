package org.mb4j.liferay.sample.offer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.liferay.sample.SampleBasePortlet;
import org.mb4j.view.ViewMap;
import org.mb4j.view.mount.ViewMounter;

public class PersonalOfferPortlet extends SampleBasePortlet {
    @Singleton
    public static class Views extends ViewMap {
        @Inject
        public Views(PersonalOfferBrickView offer) {
            super(ViewMounter.withDefaultHomeView(offer));
        }
    }

    public PersonalOfferPortlet() {
        super(Views.class);
    }
}

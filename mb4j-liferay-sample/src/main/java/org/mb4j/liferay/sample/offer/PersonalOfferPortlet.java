package org.mb4j.liferay.sample.offer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ViewMap;
import org.mb4j.controller.mount.ViewMounter;
import org.mb4j.liferay.sample.SampleBasePortlet;

public class PersonalOfferPortlet extends SampleBasePortlet {
  @Singleton
  public static class Views extends ViewMap {
    @Inject
    public Views(PersonalOfferPage offer) {
      super(ViewMounter.withDefaultHomeView(offer));
    }
  }

  public PersonalOfferPortlet() {
    super(Views.class);
  }
}

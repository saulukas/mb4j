package org.mb4j.liferay.sample.offer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.ControllerMounter;
import org.mb4j.liferay.sample.SampleBasePortlet;

public class PersonalOfferPortlet extends SampleBasePortlet {
  @Singleton
  public static class Views extends ControllerMappings {
    @Inject
    public Views(PersonalOfferPage offer) {
      super(null, ControllerMounter.withDefaultHomeController(offer));
    }
  }

  public PersonalOfferPortlet() {
    super(Views.class);
  }
}

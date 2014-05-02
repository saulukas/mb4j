package org.mb4j.sample.liferay.offer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.mapping.ControllerMappings;
import static org.mb4j.controller.mapping.ControllerMounter.withDefaultHomeController;
import org.mb4j.sample.liferay.SampleBasePortlet;

public class PersonalOfferPortlet extends SampleBasePortlet {
  @Singleton
  public static class Mappings extends ControllerMappings {
    @Inject
    public Mappings(PersonalOfferPage offer) {
      super(withDefaultHomeController(offer));
    }
  }

  public PersonalOfferPortlet() {
    super(Mappings.class);
  }
}

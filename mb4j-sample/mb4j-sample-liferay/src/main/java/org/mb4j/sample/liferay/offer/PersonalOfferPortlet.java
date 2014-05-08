package org.mb4j.sample.liferay.offer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.sitemap.SiteMap;
import static org.mb4j.controller.sitemap.SiteMapBuilder.withDefaultHomeController;
import org.mb4j.sample.liferay.SampleBasePortlet;

public class PersonalOfferPortlet extends SampleBasePortlet {
  @Singleton
  public static class Pages extends SiteMap {
    @Inject
    public Pages(PersonalOfferPage offer) {
      super(withDefaultHomeController(offer));
    }
  }

  public PersonalOfferPortlet() {
    super("offer", Pages.class);
  }
}

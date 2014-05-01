package org.mb4j.sample.liferay.offer;

import com.google.inject.AbstractModule;

public class PersonalOfferPortletModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PersonalOfferPage.class);
    bind(PersonalOfferPortlet.Views.class);
  }
}

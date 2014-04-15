package org.mb4j.liferay.sample.offer;

import com.google.inject.AbstractModule;

public class PersonalOfferPortletModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PersonalOfferBrick.Baker.class);
        bind(PersonalOfferBrickView.class);
        bind(PersonalOfferPortlet.Views.class);
    }
}

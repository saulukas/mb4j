package org.mb4j.example.liferay.offer;

import com.google.inject.AbstractModule;
import org.mb4j.example.liferay.SampleSingleViewPortlet;

public class PersonalOfferPortlet extends SampleSingleViewPortlet {

    public static class Module extends AbstractModule {

        @Override
        protected void configure() {
            bind(PersonalOfferView.class);
        }
    }

    public PersonalOfferPortlet() {
        super("offer", PersonalOfferView.class);
    }
}

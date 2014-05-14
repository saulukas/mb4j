package org.mb4j.sample.liferay.offer;

import com.google.inject.AbstractModule;
import org.mb4j.sample.liferay.SampleSingleViewPortlet;

public class PersonalOfferPortlet extends SampleSingleViewPortlet {
  public PersonalOfferPortlet() {
    super("offer", PersonalOfferView.class);
  }

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(PersonalOfferView.class);
    }
  }
}

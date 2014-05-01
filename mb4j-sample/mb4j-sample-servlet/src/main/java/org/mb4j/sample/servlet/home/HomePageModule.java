package org.mb4j.sample.servlet.home;

import com.google.inject.AbstractModule;

public class HomePageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(HomeContentPanel.class);
    bind(HomePage.class);
  }
}

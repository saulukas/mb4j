package org.mb4j.servlet.sample.home;

import com.google.inject.AbstractModule;

public class HomePageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(HomeContentBrick.Baker.class);
    bind(HomePage.Baker.class);
    bind(HomePage.View.class);
  }
}

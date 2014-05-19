package org.mb4j.sample.servlet.home;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.Request;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.sample.servlet.master.MasterLayoutPage;

public class HomePage extends MasterLayoutPage {
  @Inject
  HomeContentPanel contentPanel;

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(HomeContentPanel.class);
      bind(HomePage.class);
    }
  }

  public static ControllerUrl url() {
    return ControllerUrl.of(HomePage.class);
  }

  @Override
  protected MustacheBrick bakeContentBrick(Request request) {
    return contentPanel.bakeBrick(request);
  }
}

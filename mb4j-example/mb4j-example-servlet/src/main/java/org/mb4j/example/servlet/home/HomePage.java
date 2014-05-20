package org.mb4j.example.servlet.home;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.url.ControllerUrl;
import org.mb4j.example.servlet.master.MasterLayoutPage;

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
  protected MustacheBrick bakeContentBrick(ViewRequest request) {
    return contentPanel.bakeBrick(request);
  }
}

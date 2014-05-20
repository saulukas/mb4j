package org.mb4j.example.servlet.home;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
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

  public static ControViewUrl {
    return ControllerUrViewUrl.class);
  }

  @Override
  protected MustacheBrick bakeContentBrick(ViewRequest request) {
    return contentPanel.bakeBrick(request);
  }
}

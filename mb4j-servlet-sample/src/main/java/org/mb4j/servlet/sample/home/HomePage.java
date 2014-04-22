package org.mb4j.servlet.sample.home;

import com.google.inject.Inject;
import org.mb4j.brick.Brick;
import org.mb4j.servlet.sample.master.MasterLayoutPage;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.url.ControllerUrl;

public class HomePage extends MasterLayoutPage {
  @Inject
  HomeContentPanel contentPanel;

  public static ControllerUrl url() {
    return ControllerUrl.of(HomePage.class);
  }

  @Override
  protected Brick bakeContentBrick(ViewRequest request) {
    return contentPanel.bakeBrick(request);
  }
}

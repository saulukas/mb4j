package org.mb4j.servlet.sample.home;

import com.google.inject.Inject;
import org.mb4j.Brick;
import org.mb4j.servlet.sample.master.MasterLayoutPage;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.url.ViewUrl;

public class HomePage extends MasterLayoutPage {
  @Inject
  HomeContentPanel contentPanel;

  public static ViewUrl url() {
    return ViewUrl.of(HomePage.class);
  }

  @Override
  protected Brick bakeContentBrick(ViewRequest request) {
    return contentPanel.bakeBrick(request);
  }
}

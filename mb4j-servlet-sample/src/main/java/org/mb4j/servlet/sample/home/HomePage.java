package org.mb4j.servlet.sample.home;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.servlet.sample.master.MasterLayoutPage;
import org.mb4j.view.url.ViewUrl;
import org.mb4j.view.baker.ParameterlessBakerView;

public class HomePage extends MasterLayoutPage {
  public static ViewUrl url() {
    return ViewUrl.of(View.class);
  }

  @Singleton
  public static class Baker extends MasterLayoutPage.Baker<Void> {
    @Inject
    public Baker(HomeContentBrick.Baker contentBaker) {
      super(contentBaker);
    }
  }

  @Singleton
  public static class View extends ParameterlessBakerView<Baker> {
    @Inject
    public View(Baker baker) {
      super(baker);
    }
  }
}

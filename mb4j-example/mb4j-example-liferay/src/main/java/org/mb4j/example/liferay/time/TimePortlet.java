package org.mb4j.example.liferay.time;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.component.sitemap.SiteMap;
import static org.mb4j.component.sitemap.SiteMapBuilder.withDefaultHomeController;
import org.mb4j.example.liferay.SampleBasePortlet;

public class TimePortlet extends SampleBasePortlet {
  @Singleton
  public static class Views extends SiteMap {
    @Inject
    public Views(TimeView timeView) {
      super(withDefaultHomeController(timeView));
    }
  }

  public TimePortlet() {
    super("time", Views.class);
  }

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(TimeView.class);
      bind(Views.class);
    }
  }
}

package org.mb4j.example.liferay.time;

import com.google.inject.AbstractModule;
import org.mb4j.example.liferay.SampleSingleViewPortlet;

public class TimePortlet extends SampleSingleViewPortlet {
  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(TimeView.class);
    }
  }

  public TimePortlet() {
    super("time", TimeView.class);
  }
}

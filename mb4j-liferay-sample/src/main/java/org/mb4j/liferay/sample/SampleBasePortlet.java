package org.mb4j.liferay.sample;

import com.google.inject.Injector;
import org.mb4j.brick.BrickRenderer;
import org.mb4j.liferay.BrickPortlet;
import org.mb4j.controller.ViewMap;

public class SampleBasePortlet extends BrickPortlet {
  protected SampleBasePortlet(Class<? extends ViewMap> viewsClass) {
    this(LiferaySampleModule.injector(), viewsClass);
  }

  private SampleBasePortlet(Injector injector, Class<? extends ViewMap> viewsClass) {
    super(injector.getInstance(BrickRenderer.class), injector.getInstance(viewsClass));
  }
}

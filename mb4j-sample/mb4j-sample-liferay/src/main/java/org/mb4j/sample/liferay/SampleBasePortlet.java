package org.mb4j.sample.liferay;

import com.google.inject.Injector;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.liferay.BrickPortlet;
import org.mb4j.controller.mapping.ControllerMappings;

public class SampleBasePortlet extends BrickPortlet {
  protected SampleBasePortlet(Class<? extends ControllerMappings> viewsClass) {
    this(LiferaySampleModule.injector(), viewsClass);
  }

  private SampleBasePortlet(Injector injector, Class<? extends ControllerMappings> viewsClass) {
    super(injector.getInstance(BrickRenderer.class), injector.getInstance(viewsClass));
  }
}

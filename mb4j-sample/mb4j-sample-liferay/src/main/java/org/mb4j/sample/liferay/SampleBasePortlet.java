package org.mb4j.sample.liferay;

import com.google.inject.Injector;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.liferay.BrickPortlet;

public class SampleBasePortlet extends BrickPortlet {
  protected SampleBasePortlet(Class<? extends ControllerMappings> controllersClass) {
    this(LiferaySampleModule.injector(), controllersClass);
  }

  private SampleBasePortlet(Injector injector, Class<? extends ControllerMappings> controllersClass) {
    super(injector.getInstance(BrickRenderer.class), injector.getInstance(controllersClass));
  }
}

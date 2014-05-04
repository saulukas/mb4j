package org.mb4j.sample.liferay;

import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.liferay.BrickPortlet;

public class SampleBasePortlet extends BrickPortlet {
  protected SampleBasePortlet(String friendlyUrlMapping, Class<? extends ControllerMappings> mappingsClass) {
    super(
        friendlyUrlMapping,
        LiferaySampleModule.injector().getInstance(BrickRenderer.class),
        LiferaySampleModule.injector().getInstance(mappingsClass)
    );
  }
}

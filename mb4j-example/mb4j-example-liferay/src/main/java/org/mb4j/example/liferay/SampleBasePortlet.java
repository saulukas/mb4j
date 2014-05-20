package org.mb4j.example.liferay;

import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.component.viewmap.SiteMap;
import org.mb4j.liferay.BrickPortlet;

public class SampleBasePortlet extends BrickPortlet {
  protected SampleBasePortlet(String friendlyUrlMapping, Class<? extends SiteMap> viewMapClass) {
    this(friendlyUrlMapping, LiferaySampleModule.injector().getInstance(viewMapClass));
  }

  protected SampleBasePortlet(String friendlyUrlMapping, SiteMap viewMap) {
    super(
        friendlyUrlMapping,
        LiferaySampleModule.injector().getInstance(BrickRenderer.class),
        viewMap
    );
    System.out.println("Created portlet with friendly URL: " + friendlyUrlMapping
        + "\n" + viewMap);
  }
}

package org.mb4j.example.liferay;

import org.mb4j.component.viewmap.SiteMap;
import static org.mb4j.component.viewmap.SiteMapBuilder.withDefaultHomeController;
import org.mb4j.liferay.PortletView;

public class SampleSingleViewPortlet extends SampleBasePortlet {
  protected SampleSingleViewPortlet(String friendlyUrl, Class<? extends PortletView> viewClass) {
    super(friendlyUrl, singleViewMapFor(viewClass));
  }

  public static SiteMap singleViewMapFor(Class<? extends PortletView> viewClass) {
    return new SiteMap(withDefaultHomeController(
        LiferaySampleModule.injector().getInstance(viewClass)));
  }
}

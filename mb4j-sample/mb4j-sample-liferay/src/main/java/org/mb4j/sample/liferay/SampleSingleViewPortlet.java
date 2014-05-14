package org.mb4j.sample.liferay;

import org.mb4j.controller.sitemap.SiteMap;
import static org.mb4j.controller.sitemap.SiteMapBuilder.withDefaultHomeController;
import org.mb4j.liferay.PortletView;

public class SampleSingleViewPortlet extends SampleBasePortlet {
  protected <T extends PortletView> SampleSingleViewPortlet(String friendlyUrlMapping, Class<T> viewClass) {
    super(friendlyUrlMapping, new SiteMap(withDefaultHomeController(
        LiferaySampleModule.injector().getInstance(viewClass))));
  }
}

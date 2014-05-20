package org.mb4j.example.liferay;

import org.mb4j.component.sitemap.SiteMap;
import static org.mb4j.component.sitemap.SiteMapBuilder.withDefaultHomeController;
import org.mb4j.liferay.PortletView;

public class SampleSingleViewPortlet extends SampleBasePortlet {
  protected <T extends PortletView> SampleSingleViewPortlet(String friendlyUrlMapping, Class<T> viewClass) {
    super(friendlyUrlMapping, new SiteMap(withDefaultHomeController(
        LiferaySampleModule.injector().getInstance(viewClass))));
  }
}

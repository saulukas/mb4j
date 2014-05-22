package org.mb4j.example.liferay;

import org.mb4j.component.viewmap.ViewMap;
import static org.mb4j.component.viewmap.ViewMapBuilder.withDefaultHomeView;
import org.mb4j.liferay.PortletView;

public class SampleSingleViewPortlet extends SampleBasePortlet {
  protected SampleSingleViewPortlet(String friendlyUrl, Class<? extends PortletView> viewClass) {
    super(friendlyUrl, singleViewMapFor(viewClass));
  }

  public static ViewMap singleViewMapFor(Class<? extends PortletView> viewClass) {
    return new ViewMap(withDefaultHomeView(
        LiferaySampleModule.injector().getInstance(viewClass)));
  }
}

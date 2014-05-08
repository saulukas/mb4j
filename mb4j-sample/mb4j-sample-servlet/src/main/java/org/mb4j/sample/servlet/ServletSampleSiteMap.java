package org.mb4j.sample.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.sitemap.SiteMap;
import static org.mb4j.controller.sitemap.SiteMapBuilder.withHomeController;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;
import org.mb4j.sample.servlet.event.edit.EventEditPage;
import org.mb4j.sample.servlet.event.list.EventListPage;
import org.mb4j.sample.servlet.home.HomePage;

@Singleton
public class ServletSampleSiteMap extends SiteMap {
  @Inject
  public ServletSampleSiteMap(
      HomePage home,
      EventListPage eventList,
      EventEditPage eventEdit) {
    super(withHomeController(home)
        .mount(urlPathOf("event/*"), eventList)
        .mount(urlPathOf("event/edit/*"), eventEdit));
  }
}

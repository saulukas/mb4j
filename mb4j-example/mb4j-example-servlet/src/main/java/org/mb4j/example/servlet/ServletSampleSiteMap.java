package org.mb4j.example.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.component.sitemap.SiteMap;
import static org.mb4j.component.sitemap.SiteMapBuilder.withHomeController;
import static org.mb4j.component.url.UrlPathString.urlPathOf;
import org.mb4j.example.servlet.event.edit.EventEditPage;
import org.mb4j.example.servlet.event.list.EventListPage;
import org.mb4j.example.servlet.home.HomePage;
import org.mb4j.example.servlet.services.TimeService;

@Singleton
public class ServletSampleSiteMap extends SiteMap {
  @Inject
  public ServletSampleSiteMap(
      HomePage home,
      EventListPage eventList,
      EventEditPage eventEdit) {
    super(withHomeController(home)
        .mount(urlPathOf("event/*"), eventList)
        .mount(urlPathOf("event/edit/*"), eventEdit)
        .mount(urlPathOf("time/*"), new TimeService())
    );
  }
}

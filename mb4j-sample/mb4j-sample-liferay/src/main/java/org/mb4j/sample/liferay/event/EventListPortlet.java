package org.mb4j.sample.liferay.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.sitemap.SiteMap;
import static org.mb4j.controller.sitemap.SiteMapBuilder.withDefaultHomeController;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;
import org.mb4j.sample.liferay.SampleBasePortlet;
import org.mb4j.sample.liferay.event.edit.EventEditPage;
import org.mb4j.sample.liferay.event.list.EventListPage;

public class EventListPortlet extends SampleBasePortlet {
  @Singleton
  public static class Pages extends SiteMap {
    @Inject
    public Pages(
        EventListPage eventList,
        EventEditPage eventEdit) {
      super(withDefaultHomeController(eventList)
          .mount(urlPathOf("edit/*"), eventEdit)
      );
    }
  }

  public EventListPortlet() {
    super("events", Pages.class);
  }
}

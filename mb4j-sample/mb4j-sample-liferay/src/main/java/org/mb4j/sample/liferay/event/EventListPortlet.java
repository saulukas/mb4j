package org.mb4j.sample.liferay.event;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.sitemap.SiteMap;
import static org.mb4j.controller.sitemap.SiteMapBuilder.withDefaultHomeController;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;
import org.mb4j.sample.liferay.SampleBasePortlet;
import org.mb4j.sample.liferay.event.edit.EventEditView;
import org.mb4j.sample.liferay.event.edit.EventEditViewModule;
import org.mb4j.sample.liferay.event.list.EventListView;
import org.mb4j.sample.liferay.event.list.EventListViewModule;

public class EventListPortlet extends SampleBasePortlet {
  @Singleton
  public static class Views extends SiteMap {
    @Inject
    public Views(
        EventListView eventList,
        EventEditView eventEdit) {
      super(withDefaultHomeController(eventList)
          .mount(urlPathOf("edit/*"), eventEdit)
      );
    }
  }

  public EventListPortlet() {
    super("events", Views.class);
  }

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      install(new EventListViewModule());
      install(new EventEditViewModule());
      bind(Views.class);
    }
  }
}

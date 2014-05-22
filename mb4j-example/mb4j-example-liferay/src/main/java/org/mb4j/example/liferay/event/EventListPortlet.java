package org.mb4j.example.liferay.event;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import static org.mb4j.component.url.UrlPathString.urlPathOf;
import org.mb4j.component.viewmap.SiteMap;
import static org.mb4j.component.viewmap.SiteMapBuilder.withDefaultHomeController;
import org.mb4j.example.liferay.SampleBasePortlet;
import org.mb4j.example.liferay.event.edit.EventEditView;
import org.mb4j.example.liferay.event.list.EventListView;

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
      install(new EventListView.Module());
      install(new EventEditView.Module());
      bind(Views.class);
    }
  }
}

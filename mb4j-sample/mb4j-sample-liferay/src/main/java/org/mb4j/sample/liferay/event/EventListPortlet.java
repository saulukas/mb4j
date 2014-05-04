package org.mb4j.sample.liferay.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.mapping.ControllerMappings;
import static org.mb4j.controller.mapping.ControllerMounter.withDefaultHomeController;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;
import org.mb4j.sample.liferay.SampleBasePortlet;
import org.mb4j.sample.liferay.event.edit.EventEditPage;
import org.mb4j.sample.liferay.event.list.EventListPage;

public class EventListPortlet extends SampleBasePortlet {
  @Singleton
  public static class Mappings extends ControllerMappings {
    @Inject
    public Mappings(
        EventListPage eventList,
        EventEditPage eventEdit) {
      super(withDefaultHomeController(eventList)
          .mount(urlPathOf("edit/*"), eventEdit)
      );
    }
  }

  public EventListPortlet() {
    super("events", Mappings.class);
  }
}

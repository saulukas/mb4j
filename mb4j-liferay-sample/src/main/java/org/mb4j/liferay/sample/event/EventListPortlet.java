package org.mb4j.liferay.sample.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.ControllerMounter;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;
import org.mb4j.liferay.sample.SampleBasePortlet;
import org.mb4j.liferay.sample.event.edit.EventEditForm;
import org.mb4j.liferay.sample.event.edit.EventEditPage;
import org.mb4j.liferay.sample.event.list.EventListPage;

public class EventListPortlet extends SampleBasePortlet {
  @Singleton
  public static class Views extends ControllerMappings {
    @Inject
    public Views(
        EventListPage eventList,
        EventEditPage eventEdit,
        EventEditForm.SaveAction eventSave) {
      super(null, ControllerMounter.withDefaultHomeController(eventList)
          .mount(urlPathOf("edit/*"), eventEdit)
          .mount(urlPathOf("save"), eventSave));
    }
  }

  public EventListPortlet() {
    super(Views.class);
  }
}

package org.mb4j.liferay.sample.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ViewMap;
import org.mb4j.controller.mount.ViewMounter;
import static org.mb4j.controller.path.UrlPathString.urlPath;
import org.mb4j.liferay.sample.SampleBasePortlet;
import org.mb4j.liferay.sample.event.edit.EventEditForm;
import org.mb4j.liferay.sample.event.edit.EventEditPage;
import org.mb4j.liferay.sample.event.list.EventListPage;

public class EventListPortlet extends SampleBasePortlet {
  @Singleton
  public static class Views extends ViewMap {
    @Inject
    public Views(
        EventListPage eventList,
        EventEditPage eventEdit,
        EventEditForm.SaveAction eventSave) {
      super(ViewMounter.withDefaultHomeView(eventList)
          .mount(urlPath("edit/*"), eventEdit)
          .mount(urlPath("save"), eventSave));
    }
  }

  public EventListPortlet() {
    super(Views.class);
  }
}

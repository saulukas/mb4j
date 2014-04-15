package org.mb4j.liferay.sample.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.liferay.sample.event.edit.EventEditBrickView;
import org.mb4j.liferay.sample.event.edit.EventEditForm;
import org.mb4j.liferay.sample.event.list.EventListBrickView;
import org.mb4j.liferay.sample.SampleBasePortlet;
import org.mb4j.view.ViewMap;
import org.mb4j.view.mount.ViewMounter;
import static org.mb4j.view.path.ViewPathString.viewPath;

public class EventListPortlet extends SampleBasePortlet {
  @Singleton
  public static class Views extends ViewMap {
    @Inject
    public Views(
        EventListBrickView eventList,
        EventEditBrickView eventEdit,
        EventEditForm.SaveAction eventSave) {
      super(ViewMounter.withDefaultHomeView(eventList)
          .mount(viewPath("edit/*"), eventEdit)
          .mount(viewPath("save"), eventSave));
    }
  }

  public EventListPortlet() {
    super(Views.class);
  }
}

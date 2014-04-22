package org.mb4j.servlet.sample;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.mapping.ViewMap;
import org.mb4j.controller.mapping.ViewMounter;
import static org.mb4j.controller.path.UrlPathString.urlPath;
import org.mb4j.servlet.sample.event.edit.EventEditForm;
import org.mb4j.servlet.sample.event.edit.EventEditPage;
import org.mb4j.servlet.sample.event.list.EventListPage;
import org.mb4j.servlet.sample.home.HomePage;

@Singleton
public class ServletSampleViewMap extends ViewMap {
  @Inject
  public ServletSampleViewMap(
      HomePage home,
      EventListPage eventList,
      EventEditPage eventEdit,
      EventEditForm.SaveAction eventSave) {
    super(ViewMounter.withHomeView(home)
        .mount(urlPath("event/*"), eventList)
        .mount(urlPath("event/edit/*"), eventEdit)
        .mount(urlPath("event/save"), eventSave));
  }
}

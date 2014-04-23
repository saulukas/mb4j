package org.mb4j.servlet.sample;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.ControllerMounter;
import org.mb4j.controller.mapping.InstanceProviderByClass;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;
import org.mb4j.servlet.sample.event.edit.EventEditForm1;
import org.mb4j.servlet.sample.event.edit.EventEditPage;
import org.mb4j.servlet.sample.event.list.EventListPage;
import org.mb4j.servlet.sample.home.HomePage;

@Singleton
public class ServletSampleViewMap extends ControllerMappings {
  @Inject
  public ServletSampleViewMap(
      InstanceProviderByClass instanceProvider,
      HomePage home,
      EventListPage eventList,
      EventEditPage eventEdit,
      EventEditForm1.SaveAction eventSave) {
    super(instanceProvider, ControllerMounter.withHomeController(home)
        .mount(urlPathOf("event/*"), eventList)
        .mount(urlPathOf("event/edit/*"), eventEdit)
        .mount(urlPathOf("event/save"), eventSave));
  }
}

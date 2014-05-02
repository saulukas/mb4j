package org.mb4j.sample.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.mapping.ControllerMappings;
import static org.mb4j.controller.mapping.ControllerMounter.withHomeController;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;
import org.mb4j.sample.servlet.event.edit.EventEditPage;
import org.mb4j.sample.servlet.event.list.EventListPage;
import org.mb4j.sample.servlet.home.HomePage;

@Singleton
public class ServletSampleMappings extends ControllerMappings {
  @Inject
  public ServletSampleMappings(
      HomePage home,
      EventListPage eventList,
      EventEditPage eventEdit) {
    super(withHomeController(home)
        .mount(urlPathOf("event/*"), eventList)
        .mount(urlPathOf("event/edit/*"), eventEdit));
  }
}

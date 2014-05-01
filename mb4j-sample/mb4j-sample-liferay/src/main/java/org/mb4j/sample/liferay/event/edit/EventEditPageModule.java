package org.mb4j.sample.liferay.event.edit;

import com.google.inject.AbstractModule;

public class EventEditPageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventEditPage.class);
    bind(EventEditForm.class);
  }
}

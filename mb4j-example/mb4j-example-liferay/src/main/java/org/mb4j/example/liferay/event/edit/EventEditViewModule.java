package org.mb4j.example.liferay.event.edit;

import com.google.inject.AbstractModule;

public class EventEditViewModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventEditView.class);
    bind(EventEditFormHandler.class);
  }
}

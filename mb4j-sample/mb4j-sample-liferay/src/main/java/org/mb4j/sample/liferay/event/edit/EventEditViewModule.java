package org.mb4j.sample.liferay.event.edit;

import com.google.inject.AbstractModule;

public class EventEditViewModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventEditView.class);
    bind(EventEditForm.class);
  }
}

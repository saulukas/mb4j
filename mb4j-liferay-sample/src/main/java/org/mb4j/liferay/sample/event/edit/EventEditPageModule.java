package org.mb4j.liferay.sample.event.edit;

import com.google.inject.AbstractModule;

public class EventEditPageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventEditPage.class);
    bind(EventEditForm.Filler.class);
    bind(EventEditForm.SaveAction.class);
  }
}

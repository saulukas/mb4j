package org.mb4j.servlet.sample.event.edit;

import com.google.inject.AbstractModule;

public class EventEditPageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventEditPage.class);
    bind(EventEditPanel.class);
    bind(EventEditForm.class);
  }
}

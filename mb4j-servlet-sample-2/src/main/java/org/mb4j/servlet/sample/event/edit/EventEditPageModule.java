package org.mb4j.servlet.sample.event.edit;

import com.google.inject.AbstractModule;

public class EventEditPageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventEditPanel.class);
    bind(EventEditPage.class);
    bind(EventEditForm.Filler.class);
    bind(EventEditForm.SaveAction.class);
  }
}
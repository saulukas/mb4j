package org.mb4j.servlet.sample.event.edit;

import com.google.inject.AbstractModule;

public class EventEditPageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventEditBrick.Baker.class);
    bind(EventEditPage.Baker.class);
    bind(EventEditPage.View.class);
    bind(EventEditForm.Filler.class);
    bind(EventEditForm.SaveAction.class);
  }
}

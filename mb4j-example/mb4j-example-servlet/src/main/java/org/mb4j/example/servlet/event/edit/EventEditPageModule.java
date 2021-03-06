package org.mb4j.example.servlet.event.edit;

import com.google.inject.AbstractModule;

public class EventEditPageModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventEditPage.class);
        bind(EventEditPanel.class);
        bind(EventEditFormHandler.class);
    }

}

package org.mb4j.liferay.sample.event.edit;

import com.google.inject.AbstractModule;

public class EventEditBrickModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EventEditBrick.Baker.class);
        bind(EventEditBrickView.class);
        bind(EventEditForm.Filler.class);
        bind(EventEditForm.SaveAction.class);
    }
}

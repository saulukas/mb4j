package org.mb4j.liferay.sample.event;

import com.google.inject.AbstractModule;
import org.mb4j.liferay.sample.event.edit.EventEditBrickModule;
import org.mb4j.liferay.sample.event.list.EventListBrickModule;

public class EventPortletModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new EventListBrickModule());
        install(new EventEditBrickModule());
        bind(EventListPortlet.Views.class);
    }
}

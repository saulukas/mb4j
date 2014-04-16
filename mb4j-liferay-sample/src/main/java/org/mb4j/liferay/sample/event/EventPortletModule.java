package org.mb4j.liferay.sample.event;

import com.google.inject.AbstractModule;
import org.mb4j.liferay.sample.event.edit.EventEditPageModule;
import org.mb4j.liferay.sample.event.list.EventListPageModule;

public class EventPortletModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new EventListPageModule());
        install(new EventEditPageModule());
        bind(EventListPortlet.Views.class);
    }
}

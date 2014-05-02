package org.mb4j.sample.liferay.event;

import com.google.inject.AbstractModule;
import org.mb4j.sample.liferay.event.edit.EventEditPageModule;
import org.mb4j.sample.liferay.event.list.EventListPageModule;

public class EventPortletModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new EventListPageModule());
        install(new EventEditPageModule());
        bind(EventListPortlet.Mappings.class);
    }
}

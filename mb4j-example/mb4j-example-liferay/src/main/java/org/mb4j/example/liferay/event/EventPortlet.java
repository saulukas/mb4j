package org.mb4j.example.liferay.event;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import static org.mb4j.component.url.UrlPathString.urlPathOf;
import org.mb4j.component.viewmap.ViewMap;
import static org.mb4j.component.viewmap.ViewMapBuilder.routeDefaultHomeTo;
import org.mb4j.example.liferay.SampleBasePortlet;
import org.mb4j.example.liferay.event.edit.EventEditView;
import org.mb4j.example.liferay.event.edit.EventEditViewModule;
import org.mb4j.example.liferay.event.list.EventListView;
import org.mb4j.example.liferay.event.list.EventListViewModule;

public class EventPortlet extends SampleBasePortlet {

    @Singleton
    public static class Views extends ViewMap {

        @Inject
        public Views(
                EventListView eventList,
                EventEditView eventEdit) {
            super(
                    routeDefaultHomeTo(eventList)
                    .route(urlPathOf("edit/*"), eventEdit)
            );
        }
    }

    public EventPortlet() {
        super("events", Views.class);
    }

    public static class Module extends AbstractModule {

        @Override
        protected void configure() {
            install(new EventListViewModule());
            install(new EventEditViewModule());
            bind(Views.class);
        }
    }
}

package org.mb4j.example.servlet.event.list;

import com.google.inject.AbstractModule;

public class EventListPageModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventListPage.class);
        bind(EventListPanel.class);
        bind(EventListItemPanel.class);
    }

}

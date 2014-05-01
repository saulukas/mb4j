package org.mb4j.sample.liferay.domain;

import com.google.inject.AbstractModule;

public class EventDomainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EventListQuery.class);
        bind(EventQuery.class);
        bind(EventSaveCommand.class);
    }
}
